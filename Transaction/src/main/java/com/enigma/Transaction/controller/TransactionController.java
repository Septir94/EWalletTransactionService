package com.enigma.Transaction.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import com.enigma.Transaction.Dto.PostAddBalanceDto;
import com.enigma.Transaction.Dto.PostPointDto;
import com.enigma.Transaction.Dto.PostPulsaDto;
import com.enigma.Transaction.Dto.PostTransferDto;
import com.enigma.Transaction.Dto.TransactionEntityDto;
import com.enigma.Transaction.Dto.TransactionRespon;
import com.enigma.Transaction.Service.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("transaction")
@Api(value = "E-Wallet Transaction")
public class TransactionController {
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
		
	@Autowired
	TransactionService service;
		
		
		
		@GetMapping("")
		@ApiOperation(value = "View all Account",response = TransactionEntityDto.class)
		public List<TransactionEntityDto> getAllData(){
			
			return service.findAll();
		}
			@PostMapping("/balance")
			@ApiOperation(value = "Transaction for add balance account",response = TransactionEntityDto.class)
			public ResponseEntity<TransactionEntityDto> addBalance(@ApiParam(value = "Post")@RequestBody PostAddBalanceDto dto ){
				String url="http://localhost:8060/accounts/balance";
				Map<String , String> mapVal = new HashMap<String, String>();
				mapVal.put("account", dto.getAccount());
				mapVal.put("balance", dto.getAmount());
				new RestTemplate().put(url,mapVal);
				
				return ResponseEntity.ok().body(service.addBalance(dto));	
			}
		@GetMapping("/{date}")
		public List<TransactionEntityDto> getByDate(@PathVariable Date date){
			
			return service.findByTransactionDate(date);
		}
		
		@PostMapping("/transfer")
		public ResponseEntity<TransactionEntityDto> transfer(@RequestBody PostTransferDto dto ){
			String url="http://localhost:8060/account/transfer";
			Map<String , String> mapVal = new HashMap<String, String>();
			mapVal.put("account", dto.getAccount());
			mapVal.put("to", dto.getTo());
			mapVal.put("amount", dto.getAmount());
			mapVal.put("description", dto.getDescription());
			new RestTemplate().put(url, mapVal);
			
			return ResponseEntity.ok().body(service.transfer(dto));
			
		}
		
		@PostMapping("/transfer/point")
		public ResponseEntity<TransactionEntityDto> point(@RequestBody PostPointDto dto){
			String url="http://localhost:8060/accounts/{account}";
			String url2="http://localhost:8060/account/transfer/point";
			Map<String , String> mapVal = new HashMap<String, String>();
			mapVal.put("account", dto.getAccount());
			ResponseEntity<TransactionRespon> restTemplate= new RestTemplate().getForEntity(url, TransactionRespon.class,mapVal);
			TransactionRespon res=restTemplate.getBody();
			new RestTemplate().put(url2, mapVal);
			return ResponseEntity.ok().body(service.point(res));
			
		}
		
		@PostMapping("/transfer/pulsa")
		public ResponseEntity<TransactionEntityDto> pulsa(@RequestBody PostPulsaDto dto){
			String url="http://localhost:8060/account/transfer/pulsa";
			Map<String , String> mapVal = new HashMap<String, String>();
			mapVal.put("account", dto.getAccount());
			mapVal.put("balance", dto.getAmount());
			new RestTemplate().put(url, mapVal);
			return ResponseEntity.ok().body(service.pulsa(dto));
			
		}
		
		@GetMapping("/from/{from}")
		public List<TransactionEntityDto> getListFrom(@PathVariable String from){
			return service.findByFrom(from);
		}
		
		@GetMapping("/from/{from}/to/{to}")
		public List<TransactionEntityDto> getByFromAndTo(@PathVariable String from,@PathVariable String to) {
			return service.findByFromAndTo(from, to);
		}
		@GetMapping("/Date/{date}")
		public List<TransactionEntityDto> getListFromDate(@PathVariable String date){
			Date dates=java.sql.Date.valueOf(date);
			return service.findByDate(dates);
			
		}
		@GetMapping("/Date/{date}/Type/{type}")
		public List<TransactionEntityDto> findBytransactionDateAndType(@PathVariable String date,@PathVariable String type){
			Date dates=java.sql.Date.valueOf(date);
			return service.findBytransactionDateAndType(dates, type);
		}
		@GetMapping("/search")
		public List<TransactionEntityDto> findByAccountAndDate(@RequestParam String account,@RequestParam String date,WebRequest req) throws ParseException{
			String url="http://localhost:8060/accounts/{account}";
			Map<String , String> mapVal = new HashMap<String, String>();
			mapVal.put("account", account);
			ResponseEntity<TransactionRespon> restTemplate= new RestTemplate().getForEntity(url, TransactionRespon.class,mapVal);
			TransactionRespon res=restTemplate.getBody();
			return service.findByFromAndTransactionDate(account, date);
		}
}
