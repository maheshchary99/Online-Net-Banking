package HOME;
import java.sql.SQLException;
import java.util.Scanner;

import ADMIN.AdminLogin;
import CUSTOMER.CustomerLogin;
import DBconnection.DBconnect;

public class HOME {

	public static void main(String[] args) throws SQLException {
		int choice;
		boolean connection = DBconnect.getConnection();
		if(connection==true)
		{
			System.out.println("connection Sucess");
			
		}
			System.out.println("*********************************");
			System.out.println("welcome to SBI Netbanking System");
			System.out.println("*********************************");
			System.out.println("please choose u r login");
			Scanner din = new Scanner(System.in);
			System.out.println("1.Admin login");
			System.out.println("2.Customer login");
			choice=din.nextInt();
			switch(choice)
			{
			case 1: System.out.println("Admin login Here");
					AdminLogin al = new AdminLogin();
					al.Login();
			break;
			case 2:System.out.println("Customer login or Register Here");
					CustomerLogin cl = new CustomerLogin();
					cl.LogType();
			break;
			
			}

	}

}
