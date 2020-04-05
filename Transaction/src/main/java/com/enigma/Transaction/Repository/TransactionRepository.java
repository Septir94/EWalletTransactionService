package com.enigma.Transaction.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enigma.Transaction.Entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
	public List<TransactionEntity> findByTransactionDate(Date date);
	public List<TransactionEntity> findByFrom (String From);
	public  List<TransactionEntity> findByFromAndTo (String From,String to);
	public List<TransactionEntity> findBytransactionDate (Date date);
	public List<TransactionEntity> findBytransactionDateAndType (Date date,String type);
	public List<TransactionEntity> findByFromAndTransactionDate (String account,Date date);
	
}
