package com.BankOfIndia;

import org.junit.Test;

import junit.framework.Assert;

public class SystemCheckTest {
           
	SystemCheck s= new SystemCheck();
	@Test
	public void testWithDrawP() {
		Assert.assertEquals(true,SystemCheck.depositValidate(867312, 5000));
	}
	@Test
	public void testWithDrawN() {
		Assert.assertEquals(false,SystemCheck.depositValidate(867312,-5000));
	}
	@Test
	public void testTransferP() {
		Assert.assertEquals(true,SystemCheck.transferMoneyValidate(867310, 1000));
	}
	@Test
	public void testTransferN() {
		Assert.assertEquals(true,SystemCheck.transferMoneyValidate(867310,- 1000));
	}
	@Test
	public void testTransfer() {
		Assert.assertEquals(true,SystemCheck.transferMoneyValidate(867310, 1000));
	}
	
	@Test
	public void testDepositP() {
		Assert.assertEquals(true,SystemCheck.transferMoneyValidate(867310, 1000));
	}
	@Test
	public void testDepositN() {
		Assert.assertEquals(true,SystemCheck.transferMoneyValidate(867310, -1000));
	}
	
}
