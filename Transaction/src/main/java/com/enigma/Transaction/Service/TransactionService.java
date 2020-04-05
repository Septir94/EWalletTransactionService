package com.enigma.Transaction.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enigma.Transaction.Dto.PostAddBalanceDto;
import com.enigma.Transaction.Dto.PostPointDto;
import com.enigma.Transaction.Dto.PostPulsaDto;
import com.enigma.Transaction.Dto.PostTransferDto;
import com.enigma.Transaction.Dto.TransactionEntityDto;
import com.enigma.Transaction.Dto.TransactionRespon;
import com.enigma.Transaction.Entity.TransactionEntity;
import com.enigma.Transaction.Repository.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	TransactionRepository repo;
	
	java.util.Date date = new java.util.Date();
	java.sql.Date uDate = new java.sql.Date(date.getTime());
	
	public List<TransactionEntityDto> findAll(){
		List<TransactionEntityDto> list=new ArrayList<TransactionEntityDto>();
		repo.findAll().forEach(trans -> {
			ModelMapper model=new ModelMapper();
			TransactionEntityDto dto=model.map(trans, TransactionEntityDto.class);
			list.add(dto);
		});
		return list;
		
	}
	public List<TransactionEntityDto> findByTransactionDate(Date date){
		List<TransactionEntityDto> list=new ArrayList<TransactionEntityDto>();
		repo.findByTransactionDate(date).forEach(trans ->{
			ModelMapper model=new ModelMapper();
			TransactionEntityDto dto=model.map(trans, TransactionEntityDto.class);
			list.add(dto);
		});
		return list;
	}
	
	public TransactionEntityDto addBalance(PostAddBalanceDto dto){
		TransactionEntity trans=new TransactionEntity(null,uDate,"balance", dto.getAccount(), "", new BigDecimal(dto.getAmount()), "add balance");
		repo.save(trans);
		ModelMapper model=new ModelMapper();
		TransactionEntityDto dt=model.map(trans, TransactionEntityDto.class);
		return dt;
	}
	
	public TransactionEntityDto transfer(PostTransferDto dto){
		TransactionEntity trans=new TransactionEntity(null, uDate, "transfer", dto.getAccount(), dto.getTo(), new BigDecimal(dto.getAmount()),dto.getDescription());
		repo.save(trans);
		ModelMapper model=new ModelMapper();
		TransactionEntityDto dtoModel=model.map(trans, TransactionEntityDto.class);
		return dtoModel;
	}
	
	public TransactionEntityDto point(TransactionRespon dto) {
		TransactionEntity trans=new TransactionEntity(null, uDate, "point",dto.getAccount(), dto.getAccount(), BigDecimal.valueOf(dto.getPoint()),"Point to balance");
		repo.save(trans);
		ModelMapper model=new ModelMapper();
		TransactionEntityDto dtoModel=model.map(trans, TransactionEntityDto.class);
		return dtoModel;
	}
	public TransactionEntityDto pulsa(PostPulsaDto dto) {
		TransactionEntity trans=new TransactionEntity(null, uDate, "point",dto.getAccount(),dto.getHp() , new BigDecimal(dto.getAmount()),dto.getHp());
		repo.save(trans);
		ModelMapper model=new ModelMapper();
		TransactionEntityDto dtoModel=model.map(trans, TransactionEntityDto.class);
		return dtoModel;
	}
	public List<TransactionEntityDto> findByFrom (String From){
		List<TransactionEntityDto> list=new ArrayList<TransactionEntityDto>();
		repo.findByFrom(From).forEach(trans ->{
			ModelMapper model=new ModelMapper();
			TransactionEntityDto dto=model.map(trans, TransactionEntityDto.class);
			list.add(dto);
		});
		return list;
	}
	public  List<TransactionEntityDto> findByFromAndTo (String From,String to) {
		List<TransactionEntityDto> list=new ArrayList<TransactionEntityDto>();
		repo.findByFromAndTo(From, to).forEach(trans -> {
			ModelMapper model=new ModelMapper();
			TransactionEntityDto dto=model.map(trans, TransactionEntityDto.class);
			list.add(dto);
		});
		
		return list;
	}
	public List<TransactionEntityDto> findByDate(Date date){
		List<TransactionEntityDto> list=new ArrayList<TransactionEntityDto>();
		repo.findBytransactionDate(date).forEach(trans -> {
			ModelMapper model=new ModelMapper();
			TransactionEntityDto dto=model.map(trans, TransactionEntityDto.class);
			list.add(dto);
		});
		return list;
	}
	
	public List<TransactionEntityDto> findBytransactionDateAndType (Date date,String type){
		List<TransactionEntityDto> list=new ArrayList<TransactionEntityDto>();
		repo.findBytransactionDateAndType(date, type).forEach(trans -> {
			ModelMapper model=new ModelMapper();
			TransactionEntityDto dto=model.map(trans, TransactionEntityDto.class);
			list.add(dto);
		});
		return list;
	}
	
	public List<TransactionEntityDto> findByFromAndTransactionDate (String account,String date) throws ParseException{
		List<TransactionEntityDto> list=new ArrayList<TransactionEntityDto>();
		SimpleDateFormat format = new SimpleDateFormat("ddmmyyyy");
		Date date2 = format.parse(date);
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-mm-dd");
		repo.findByFromAndTransactionDate(account, java.sql.Date.valueOf(formats.format(date2))).forEach(trans -> {
			ModelMapper model=new ModelMapper();
			TransactionEntityDto dto=model.map(trans, TransactionEntityDto.class);
			list.add(dto);
		});
		return list;
		
	}
	
}
