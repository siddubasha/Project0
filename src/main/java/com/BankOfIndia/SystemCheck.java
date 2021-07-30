package com.BankOfIndia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.BankOfIndia.DBConnection.DBConnectionSingleton;

public class SystemCheck {
	static long bal=0L;
	public static long mainValidate(long accountNo,long money) {
		try {
			PreparedStatement  pstmt=(DBConnectionSingleton.getInstance()).prepareStatement("select balance from customer_details where accountno=?");
			pstmt.setLong(1, accountNo);
		    ResultSet rs=pstmt.executeQuery();
		    rs.next();
		   bal= rs.getLong("balance");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bal;
	}
public static boolean withDrawValidate(long accountNo,long money){
		     long bal=mainValidate(accountNo,money);
		   if(money > bal||money<0)
		   {
			   return false;
		   }
		   else
			   return true;
	  }
   public static boolean depositValidate(long accountNo,long deposit) {
	   if(deposit<0)
	   {
		   return false;
	   }
	   else
		   return true;
   }
   public static boolean transferMoneyValidate(long accountNo,long transfer) {
	   long bal=mainValidate(accountNo,transfer);
	   if(transfer<bal) {
		   return true;
	   }
	   else
		   return false;
   }
}
