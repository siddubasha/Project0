package com.BankOfIndia.Main;

import java.util.Scanner;

import com.BankOfIndia.Customer;
import com.BankOfIndia.Employee.Employee;

public class MainRun {
	
	
	 public static void main(String[] args) { Scanner sc= new Scanner(System.in);
	 System.out.println("#####----welcome to BOI smartapp----#####");
	 int n; do {
	 System.out.println("choose option shown below");
	 System.out.println();
	 System.out.println("1.applying bank account");
	 System.out.println("2.customer login");
	 System.out.println("3.employee login"); 
	 n=sc.nextInt();
	 switch(n) { 
	 case 1:Customer.applicationForm();break; 
	 case 2:Customer.loginCustomer();break;
	 case 3:Employee. employeeLogin();break;
	 default: System.out.println("invalid option try again"); 
	 } 
	 }while(n>3||n<0||n>0&&n<4);
	 
	 }
}
