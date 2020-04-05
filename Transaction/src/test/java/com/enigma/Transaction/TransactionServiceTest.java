package com.enigma.Transaction;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.enigma.Transaction.Dto.TransactionEntityDto;
import com.enigma.Transaction.Entity.TransactionEntity;
import com.enigma.Transaction.Repository.TransactionRepository;
import com.enigma.Transaction.Service.TransactionService;


@RunWith(SpringRunner.class)
public class TransactionServiceTest {
	@TestConfiguration
	static class TransactionServiceContextConfiguration{
		@Bean
		public TransactionService transactionService() {
			return new TransactionService();
		}
	}
	@Autowired
	private TransactionService service;
	
	@MockBean
	private TransactionRepository repo;
	
	@Before
	public void setUp() {
		
		TransactionEntity trans=new TransactionEntity(1, java.sql.Date.valueOf("2020-03-19"), "balance", "123456", "123457", new BigDecimal(100000), "Pulsa");
		
		List<TransactionEntity>list=new ArrayList<TransactionEntity>();
		list.add(trans);
		Mockito.when(repo.findBytransactionDate(java.sql.Date.valueOf("2020-03-19"))).thenReturn(list);
		Mockito.when(repo.findByFrom("123456")).thenReturn(list);
		Mockito.when(repo.findAll()).thenReturn(list);
		
	}
	@Test
	public void whenFindAll_ThenReturnList() {
		List<TransactionEntityDto>list=service.findAll();
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void whenFindByFrom_ThenReturnListDto() {
		List<TransactionEntityDto>list=service.findByFrom("123456");
		assertEquals(list.size(), 1);
	}
	@Test
	public void whenFindByTransactionDate_ThenReturnListDto() {
	
		List<TransactionEntityDto>list=service.findByDate(java.sql.Date.valueOf("2020-03-19"));
		assertEquals(list.size(), 1);
	}
	@Test
	public void whenFindByTransactionTransDate_ThenReturnListDto() {
	
		List<TransactionEntityDto>list=service.findByTransactionDate(java.sql.Date.valueOf("2020-03-18"));
		assertEquals(list.size(), 1);
	}
		
}
