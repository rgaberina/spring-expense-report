/**
 * 
 */
package com.berina.expensereports.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.berina.expensereports.model.ReceiptModel;

/**
 * @author berina
 *
 */
@Controller
public class UploadController {

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
		ReceiptModel receipt = new ReceiptModel();
		receipt.setCategory(category);
		//receipt.setFile(file);
		receipt.setPayment(payment);
		return "home";
	}
}