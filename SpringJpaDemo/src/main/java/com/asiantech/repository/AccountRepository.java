package com.asiantech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asiantech.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	@Query("select u from Account u where u.name like %?1")
	  List<Account> findByFirstnameEndsWith(String firstname);
}