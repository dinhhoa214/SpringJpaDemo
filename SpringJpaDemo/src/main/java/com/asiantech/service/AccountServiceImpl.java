package com.asiantech.service;
   

import java.util.List;

import javax.annotation.Resource;  

import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable;   
import org.springframework.stereotype.Service;

import com.asiantech.entity.Account;
import com.asiantech.repository.AccountRepository;

@Service(AccountService.NAME)
public class AccountServiceImpl implements AccountService{ 
	@Resource
	private AccountRepository accRepository;

	@Override
	public List<Account> getAll() {
		 
		return accRepository.findAll();
	}
	
	@Override
	public Page<Account> getAllUserAndPagination(Pageable pageable) {
		return accRepository.findAll(pageable);
	}

	@Override
	public Account saveOrUpdate(Account account) {
		 
		return accRepository.save(account);
	}

	@Override
	public Account getById(int id) {
		 
		return accRepository.findOne(id);
	}

	@Override
	public void deleteById(int id) {
		accRepository.delete(id);
		
	}

	@Override
	public List<Account> findByFirstName(String firstname) {
		 
		return accRepository.findByFirstnameEndsWith(firstname);
	}
	

	 

	 
	
}
