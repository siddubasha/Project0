package com.BankOfIndia;
import java.sql.*;
import java.util.Scanner;
import com.BankOfIndia.DBConnection.DBConnectionSingleton;
import com.BankOfIndia.Employee.Employee;
import com.BankOfIndia.DBConnection.*;
public class Customer {
   	static long accountNo;
	/*
	 * public static void main(String[] args) { //applicationForm();
	 * loginCustomer(); }
	 */
	public static void loginCustomer() {
		Scanner sc= new Scanner(System.in);
		System.out.println("enter user name");
		String name=sc.nextLine();
		System.out.println("enter password");
		String password=sc.nextLine();
		try {
			PreparedStatement  pstmt= (DBConnectionSingleton.getInstance()).prepareStatement("select name,accountno  from  customer_details where  password=?");
			pstmt.setString(1,password);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			if(rs==null||rs.getString("name").equals(name)==false) {
				System.out.println("invalid name or invalid password");
			}
			accountNo=rs.getLong("accountno");
			int n=0;
			  do {
		      System.out.println();
		    System.out.println("choose  option shown below");
		    System.out.println();
			System.out.println("   1. application status");
			System.out.println("   2. check balance");
			System.out.println("   3. withdrawing money");
			System.out.println("   4. depositing money");
			System.out.println("   5. transfereing money");
			System.out.println("   6. receiving money");
			 n=sc.nextInt();
			 
			switch(n) {
			case 1:checkStatus();break;
			case 2:checkBalance();break;
			case 3: withDraw();break;
			case 4:depositMoney();break;
			case 5:transferMoney();break;
			case 6:receiveMoney();break;
			default :System.out.println("invalid option");
			}
			  }while(n<0||n>6||n>0&&n<7);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void receiveMoney() {
		Scanner sc= new Scanner(System.in);
		System.out.println("enter account no othethan you");
		long account=sc.nextLong();
		System.out.println("enter money to receive");
		long receive=sc.nextLong();
		try {
			PreparedStatement  pstmt=(DBConnectionSingleton.getInstance()).prepareStatement("select balance from  customer_details where accountno=?");
			pstmt.setLong(1,accountNo);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			Long bal= rs.getLong("balance")+receive;
			pstmt=(DBConnectionSingleton.getInstance()).prepareStatement("update  customer_details set balance = ? where accountno=?");
			pstmt.setLong(1, bal);
			pstmt.setLong(2, accountNo);
			int status=pstmt.executeUpdate();
			if(status !=0)
				System.out.println("money received sucessfully");
			else
				System.out.println("failed to receive money ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	private static void transferMoney() {
		Scanner sc= new Scanner(System.in);
		System.out.println("enter account no whom you want to transfer");
		Long acn=sc.nextLong();
		System.out.println("enter money to tranfer");
		Long transfer=sc.nextLong();
		boolean b=SystemCheck.transferMoneyValidate(accountNo,transfer);
		if(b==true) 
		{	
			System.out.println("Money transfered sucessfully");
			long bal=SystemCheck.mainValidate(accountNo,transfer);
			bal=bal-transfer;
			try {
				PreparedStatement  pstmt=(DBConnectionSingleton.getInstance()).prepareStatement("update  customer_details set balance = ? where accountno=?");
				pstmt.setLong(1, bal);
				pstmt.setLong(2, accountNo);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("invalid transfer money");
		}


	}

	private static void depositMoney() {
		Scanner sc= new Scanner(System.in);
		System.out.println("enter money to deposit");
		long deposit=sc.nextLong();
		boolean b= SystemCheck.depositValidate(accountNo,deposit);
		if(b==true) {
			System.out.println("Money deposited");
			try {
				long bal=SystemCheck.mainValidate(accountNo,deposit);
				bal=bal+deposit;
				PreparedStatement  pstmt=(DBConnectionSingleton.getInstance()).prepareStatement("update  customer_details set balance = ? where accountno=?");
				pstmt.setLong(1, bal);
				pstmt.setLong(2, accountNo);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("invalid deposit");
		}

	}
	private static void withDraw(){
		Scanner sc= new Scanner(System.in);
		System.out.println("enter money to with draw");
		Long money=sc.nextLong();
		boolean b=SystemCheck.withDrawValidate(accountNo,money);
		if(b) {
			System.out.println("take the cash");
			try {
				long bal=SystemCheck.mainValidate(accountNo,money);
				bal=bal-money;
				PreparedStatement  pstmt=(DBConnectionSingleton.getInstance()).prepareStatement("update  customer_details set balance = ? where accountno=?");
				pstmt.setLong(1, bal);
				pstmt.setLong(2, accountNo);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("insufficien balanace");
		}
	}

	private static void checkBalance() {
		try {
			PreparedStatement  pstmt=(DBConnectionSingleton.getInstance()).prepareStatement("select  balance from  customer_details where accountno=?");
			pstmt.setLong(1, accountNo);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			System.out.println(" available balance is : "+rs.getLong("balance"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void checkStatus() {
		try {
			PreparedStatement  pstmt=(DBConnectionSingleton.getInstance()).prepareStatement("select  * from  customer_details where accountno=?");
			pstmt.setLong(1,accountNo);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			String state="approved";
			if(rs.getString("status").equals(state))
			{	System.out.println("your account is activated A/C NO :"+rs.getLong("accountno"));
			accountNo=rs.getLong("accountno");
			}
			else
				System.out.println("your account is in not approved");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void applicationForm() {
		Scanner sc= new Scanner(System.in);
		System.out.println("******** welcome to bank of india *********");
		System.out.println("enter your name");
		String name=sc.nextLine();
		System.out.println("enter mobileno");
		long mobileNo=sc.nextLong();
		System.out.println("enter adhar no");
		long adhaarNo=sc.nextLong();
		System.out.println("enter address");
		sc.nextLine();
		String address=sc.nextLine();
		System.out.println("enter money to deposit");
		long deposit=sc.nextLong();
		System.out.println("enter password");sc.nextLine();
		String password=sc.nextLine();

		try {
			PreparedStatement  pstmt=(DBConnectionSingleton.getInstance()).prepareStatement("insert into  customer_details (name,adhaarno,mobile,balance,address,password) values(?,?,?,?,?,?)");
			 pstmt.setString(1,name); pstmt.setLong(2,adhaarNo);
			 pstmt.setLong(3,mobileNo); pstmt.setLong(4,deposit);
			 pstmt.setString(5,address); pstmt.setString(6,password);
			int check=pstmt.executeUpdate();
			if(check!=0)
				System.out.println("your details are registered and your application is in process");
			else
				System.out.println("failed to register ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
