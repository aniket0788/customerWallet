package com.wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByTransactionId(Long TransactionId);

	Boolean delete(long transactionId);

	@Query("select (select sum(amount) from Transaction where customerId = ?1 and transactionType='CREDIT') - (select sum(amount) from Transaction where customerId = ?1 and transactionType='DEBIT') from Transaction")
	Double sumAmountByCustomerId(int customerId);

	List<Transaction> findByCustomerId(Integer customerId);

	@Modifying
	@Transactional(readOnly=false)
	@Query("update Transaction t set t.status = ?1 where t.transactionId = ?2 and t.transactionType = ?3")
	int cancelTransaction(String status, long transactionId, String transactionType);

}
