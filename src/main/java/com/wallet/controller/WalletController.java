package com.wallet.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wallet.model.Transaction;
import com.wallet.service.TransactionService;

@Controller
public class WalletController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAll(Model model) {
		List<Transaction> transactions = null;
		try {
			transactions = transactionService.findAll();
		} catch (Exception e) {
			logger.error("Exception occurred: ", e);
		}
		
		model.addAttribute("transactions",transactions);
		
		return "index";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid Transaction transaction, BindingResult bindingResult)  {
		if (bindingResult.hasErrors()) {
            return "addTransaction";
        }
		try {
			transactionService.saveWalletData(transaction);
		} catch (Exception e) {
			logger.error("Exception occurred: ", e);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/createTransaction")
	public String createTransaction(Model model) throws Exception  {
		model.addAttribute("transaction", new Transaction());
		return "addTransaction";
	}

	@RequestMapping(value = "/delete/{transactionId}", method = RequestMethod.GET)
	public String deleteTransaction(@PathVariable long transactionId, Model model) {
		 try {
			 logger.info("Deleteing transaction with Transaction Id: "+transactionId);
			transactionService.delete(transactionId);
		} catch (Exception e) {
			logger.error("Exception occurred: ", e);
		}
		 return "redirect:/";
	}
	
	@RequestMapping(value = "/cancelTransaction")
	public String cancelTransaction(Model model)  {
		List<Transaction> transactionsList = null;;
		try {
			transactionsList = transactionService.findAll();
		} catch (Exception e) {
			logger.error("Exception occurred: ", e);
		}
		model.addAttribute("transactionsList",transactionsList);
		model.addAttribute("transaction", new Transaction());
		return "cancelTransaction";
	}

	@RequestMapping(value = "cancel", method = RequestMethod.POST)
	public String cancel(Model model,Transaction transaction) {
		try {
			transactionService.cancelTransaction(transaction);
			logger.warn("Cancelled transaction with Transaction Id: "+transaction.getTransactionId());
		} catch (Exception e) {
			logger.error("Exception occurred: ", e);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/calTotalBal")
	public String totalBalance(Model model) throws Exception  {
		model.addAttribute("transaction", new Transaction());
		return "totalBalance";
	}

	@RequestMapping(value = "balance", method = RequestMethod.POST)
	public String getCurrBal(@Valid Transaction transaction, BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
            return "totalBalance";
        }
		Double data = null;
		try {
			data = transactionService.sumBal(transaction.getCustomerId()) != null ? transactionService.sumBal(transaction.getCustomerId()) : 0;
		} catch (Exception e) {
			logger.error("Exception occurred: ", e);
		}
		model.addAttribute("data",data);
		return "totalBalance";
	}
	
	@RequestMapping(value = "/viewPassbook")
	public String viewPassbook(Model model) throws Exception {
		model.addAttribute("transaction", new Transaction());
		return "passbook";
	}

	@RequestMapping(value = "passbook", method = RequestMethod.POST)
	public String viewPassbook(@Valid Transaction transaction, BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
            return "passbook";
        }
		List<Transaction> transactions = null;
		try {
			transactions = transactionService.viewPassbook(transaction.getCustomerId());
		} catch (Exception e) {
			logger.error("Exception occurred: ", e);
		}
		model.addAttribute("transactions",transactions);
		return "passbook";
	}
}
