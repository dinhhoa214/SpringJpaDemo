package com.asiantech.service;
 

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.asiantech.entity.Account;

public interface AccountService {
	public static String NAME = "accountService";

	// CRUD operations
	
		// Save or Update
		public Account saveOrUpdate(Account account);

		// Read
		public Account getById(int id);

		// Delete
		public void deleteById(int id);

		// Get All
		public List<Account> getAll();   
		
		// Get All and Paging
		Page<Account> getAllUserAndPagination(Pageable pageable);
		
		// Get All
				public List<Account> findByFirstName(String firstname);
}
