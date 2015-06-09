package com.asiantech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asiantech.entity.Account;
import com.asiantech.service.AccountService;

@Controller
@RequestMapping("/")
public class ThymeleafController {

	@Autowired
	@Qualifier(AccountService.NAME)
	AccountService accountSv;
	
	 
	 
	@RequestMapping(value="/getJPA", method=RequestMethod.GET)
	public String getAccountJPA(@RequestParam(required = false) Integer record, 
			@RequestParam(required = false) Integer maxRows,
			@ModelAttribute("account") Account account , Model model) {
		
		int recordDefault = (record != null) ? record - 1 : 0;
		int sizeRecordDefault = (maxRows != null) ? maxRows : 5; 
		Page<Account> accountPage = accountSv.getAllUserAndPagination(new PageRequest(
				recordDefault, sizeRecordDefault, new Sort(new Order(Direction.ASC,"id"))));  
		
		
		model.addAttribute("Accounts", accountPage);
		model.addAttribute(account);
		model.addAttribute("maxRow", sizeRecordDefault);
		model.addAttribute("numCountRows", accountPage.getTotalElements());
		model.addAttribute("numPages", accountPage.getTotalPages() );
		model.addAttribute("numOfPage", recordDefault + 1 );
		// redirectAttrs.addAttribute(account);
		return "AccountJPA";
	}
	@RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
	public String saveAccount(@RequestParam(required = false) Integer record,
			@RequestParam(required = false) Integer maxRows,
			@ModelAttribute("account") Account account,
			RedirectAttributes redirectAttrs) {
		accountSv.saveOrUpdate(account);
		redirectAttrs.addFlashAttribute(account);
		return "redirect:/getJPA?record=" + record +"&maxRows=" + maxRows;
	}
	
	@RequestMapping(value ="/delete", method = RequestMethod.POST) 
	public String deleteAccount(@RequestParam int id) { 
		accountSv.deleteById(id); 
		return "Deleted account ID = "+id;
	}

	@RequestMapping("/edit")
	public String editAccount(@RequestParam int id,
			@RequestParam(required = false) Integer record,
			@RequestParam(required = false) Integer maxRows,
			RedirectAttributes redirectAttrs) {

		redirectAttrs.addFlashAttribute(accountSv.getById(id));
		// redirectAttrs.addAttribute(account);
		return "redirect:/getJPA?record=" + record +"&maxRows=" + maxRows;
	}
}


 
