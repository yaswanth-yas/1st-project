package mypackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@SuppressWarnings("serial")

public class Add extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res)
	throws IOException,ServletException{
	
		String cust_name=req.getParameter("name");
		String cust_residence=req.getParameter("residence");
		String cust_bike=req.getParameter("bike");
		PrintWriter out=res.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/vasanthdb";
			String user="root";
			String pass="Vasanth@23";
			Connection con=DriverManager.getConnection(url,user,pass);
			PreparedStatement ps=con.prepareStatement("insert into customers(customer_name,residence,bike) values(?,?,?)");
			ps.setString(1,cust_name);
			ps.setString(2,cust_residence);
			ps.setString(3,cust_bike);
			ps.executeUpdate();
	
				RequestDispatcher rd=req.getRequestDispatcher("/customer.jsp");
				rd.include(req, res);
				out.println("<center style='color:red'>Record Added</center>");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
