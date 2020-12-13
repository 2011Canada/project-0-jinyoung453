package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.revature.repositories.AccountImplementationPostgreDAO;

public class AccountLoginTest {
	
	AccountImplementationPostgreDAO aDao = new AccountImplementationPostgreDAO();

	@Test
	public void Test() {
		char active = 'A';
		assertEquals(active, aDao.findCustomerStatusById(1));
		
	}
	
	

}
