package com.wallet.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.model.Transaction;
import com.wallet.repository.TransactionRepository;


@Service
public class TransactionService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public List<Transaction> saveWalletData(Transaction req) throws Exception {
		req.setTransactionType(req.getTransactionType().toUpperCase());
		req.setTimeStamp((new Timestamp(System.currentTimeMillis())));
		req.setStatus("ACTIVE");

		transactionRepository.save(req);
		logger.info("Saved Transaction: "+req);
		return transactionRepository.findByTransactionId(req.getTransactionId());
	}
	
	public List<Transaction> findAll() throws Exception{
		return transactionRepository.findAll();
	}
	
	public Boolean delete(long transactionId) throws Exception {
		 return transactionRepository.delete(transactionId);
	}
	
	public Double sumBal(int customerId) throws Exception {
		return transactionRepository.sumAmountByCustomerId(customerId);
	}
	
	public List<Transaction> viewPassbook(int customerId) throws Exception {
		return transactionRepository.findByCustomerId(customerId);
	}
	
	public int cancelTransaction(Transaction transaction) throws Exception {
		 return transactionRepository.cancelTransaction("CANCELLED", transaction.getTransactionId(), transaction.getTransactionType());
	}
	
}
