package ADMIN;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AdminLogin {

	Scanner din;
	public void Login()
	{
		din=new Scanner(System.in);
		String username,password;
		System.out.println("Enter UserName");
		username = din.next();
		System.out.println("Enter password");
		password = din.next();
		try
		{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinebank","root","1234");
			String sql="select*from admin";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				if(username.equals(rs.getString(2))&& password.equals(rs.getString(3)))
				{
					System.out.println("welcome !! Admin");
					process();
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void process()
	{
		String option="y";
		int choice;
		while(option.equalsIgnoreCase("y"))
		{
			System.out.println("1.view customer application");
			System.out.println("2.view customer History");
			System.out.println("3.LOgout");
			System.out.println("select u r option");
			choice=din.nextInt();
			switch(choice)
			{
			case 1:customer_application();
				break;
			case 2:
				break;
			case 3:System.out.println("Admin logout Success");
			}
			din=new Scanner(System.in);
			System.out.println("Do u want to continue Press y or For Exit Press N ");
			option=din.next();
		}
	}
	void customer_application()
	{
		int cid;
		String status;
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinebank","root","1234");
			String sql="select * from customer";
			System.out.println("List of customer application");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			System.out.println("Cid\tCname\tCuser\tCemail\t\tCmobile\t\tStatus");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7));
			}
			System.out.println("hello admin ..please select any customerto approve");
			System.out.println("Enter customer id");
			cid = din.nextInt();
			String sql1="select * from customer where cid='"+cid+"'";
			Statement st1=con.createStatement();
			ResultSet rs1=st1.executeQuery(sql1);
			if(rs1.next())
			{
				System.out.println("Customer id is found sucess for action");
				System.out.println("for approve press Y or N for reject");
				status=din.next();
				if(status.equalsIgnoreCase("y"))
				{
					status="Yes";
				}
				else
				{
					status="No";
				}
				String sql2="update customer set status='"+status+"'where cid='"+cid+"'";
				PreparedStatement ps =con.prepareStatement(sql2);
				int i=ps.executeUpdate();
				if(i==1)
				{
					System.out.println("customer application approved");
				}
				else
				{
					System.out.println("customer application is rejected");
				}
			}
			else
			{
				System.out.println("sorry ### customer id is not found!!!");
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
}
