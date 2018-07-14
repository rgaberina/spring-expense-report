/**
 * 
 */
package com.berina.expensereports.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.berina.expensereports.dao.ReceiptDao;
import com.berina.expensereports.model.Receipt;

/**
 * @author berina
 *
 */
@Controller
public class ViewController {
	
	@Autowired
	private ReceiptDao receiptDao;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	@Secured("USER")
	public String index() {
		System.out.println("In index");
		return "home";
	}
	
	/*@Secured("USER")
	@RequestMapping("/view")
	public String viewReports() {
		System.out.println("In view");
		return "/view";
	}*/
	
	@Secured("USER")
	@RequestMapping("/view")
	public String viewReceipts(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    System.out.println(name);
		List<Receipt> receipts = receiptDao.getAllReceiptsByUser(name);
		model.addAttribute("receipts", receipts);
		return "/view";
	}

	@Secured("USER")
	@PostMapping("/view")
	public String viewReceiptsFilter(@RequestParam("filter") String filter, 
			@RequestParam("value") String value, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(filter, value);
		params.put("username", "user1");
		List<Receipt> receipts = receiptDao.getReceiptsByUserFilter("user1", params);
		model.addAttribute("receipts", receipts);
		return "/view";
	}
}
