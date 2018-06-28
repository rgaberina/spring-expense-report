/**
 * 
 */
package com.berina.expensereports.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.berina.expensereports.dao.ReceiptDao;
import com.berina.expensereports.model.Receipt;

/**
 * @author berina
 *
 */
@Controller
public class UploadController {
	
	@Autowired
	private ReceiptDao receiptDao;
	
	@Secured("USER")
	@GetMapping(value="/upload")
	public String uploadReceiptForm() {
		System.out.println("In uploadReceiptForm");
		return "/upload";
	}

	@Secured("USER")
	@PostMapping(value="/upload")
	public String uploadReceipt(@RequestParam("file") MultipartFile file, 
			@RequestParam("category") String category,
			@RequestParam("payment") String payment,
			RedirectAttributes redirectAttributess) {
		System.out.println("In uploadReceiptForm post");
		Receipt receipt = new Receipt();
		receipt.setCategory(category);
		Path path = Paths.get(System.getProperty("user.dir") 
				+ "/src/main/resources/static/Receipts/" + file.getOriginalFilename());
		if(path.toFile().exists()) {
			String fileName = file.getOriginalFilename();
			fileName = fileName.substring(0, fileName.indexOf(".")) + "_1" 
					+ fileName.substring(fileName.indexOf("."));
			path = Paths.get(System.getProperty("user.dir") 
					+ "/src/main/resources/static/Receipts/" + fileName);
		}
		try {
			byte[] bytes = file.getBytes();
			System.out.println(path.toString());
			path.toFile().createNewFile();
			path = Files.write(path, bytes);
			String pathString = path.toString();
			String filePath = pathString.substring(pathString.indexOf("/Receipts/"), pathString.length());
			receipt.setFile(filePath);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		//System.out.println(path);
		receipt.setPayment(payment);
		receipt.setUsername("user1");
		receiptDao.save(receipt);
		return "home";
	}
}