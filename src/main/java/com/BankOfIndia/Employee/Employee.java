package com.BankOfIndia.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.BankOfIndia.DBConnection.DBConnectionSingleton;

public class Employee {
	
	/*
	 * public static void main(String[] args) { //approveAccounts();
	 * checkCustomerBalance(); employeeLogin(); }
	 */
	 
	public static void employeeLogin() {
		Scanner sc= new Scanner(System.in);
		System.out.println("enter name");
		String name=sc.nextLine();
		System.out.println("enter password");
		String password=sc.next();
		validate(name,password);
		}
	public static void validate(String name, String password){
		Scanner sc= new Scanner(System.in);
		try {
			PreparedStatement  pstmt=(DBConnectionSingleton.getInstance()).prepareStatement("select  name,password from employee");
			ResultSet r=pstmt.executeQuery();
			r.next();
		if(	r.getString("name").equals(name)!=true||r.getString("password").equals(password)!=true)
			{System.out.println("invalid password or username try again");
		   
			  employeeLogin();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int n=0;
		do {
	    System.out.println("choose option ");
	    System.out.println();
		System.out.println("1.check customer balance");
		System.out.println("2.verfiy applicants");
		n=sc.nextInt();
		switch(n) {
		case 1:checkCustomerBalance();break;
		case 2:approveAccounts();break;
		default:System.out.println("invalid option try again");
		}
		}while(n<0||n>2||n>0&&n<3);
	}
	public static void checkCustomerBalance() {
		Scanner sc= new Scanner(System.in);
		System.out.println("enter customer account no");
		long accountNo=sc.nextLong();
		long balance=0;
		try {
			PreparedStatement  pstmt=(DBConnectionSingleton.getInstance()).prepareStatement("select  balance from customer_details where accountno=?");
			pstmt.setLong(1, accountNo);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()==true)
			{	System.out.println(" available balance is : "+rs.getLong("balance"));
			balance=rs.getLong("balance");
			}
			else
				System.out.println("cutomer with that account number not found");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void approveAccounts() {
		try {
			PreparedStatement  pstmt=(DBConnectionSingleton.getInstance()).prepareStatement("select * from customer_details");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
			 long bal= rs.getLong("balance");
			 if(bal>5000) { 
				 pstmt=(DBConnectionSingleton.getInstance()).prepareStatement("update customer_details set status = ? where accountno=?");
				 pstmt.setString(1,"approved");
				 pstmt.setLong(2, rs.getLong("accountno"));
				 pstmt.executeUpdate();
				 System.out.println("Bank application is approved  with applicant name:"+rs.getString("name"));
			 }
			 else {
				 System.out.println("Bank application is rejected with applicant name:"+rs.getString("name"));
			 }
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}

