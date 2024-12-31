package com.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String id = request.getParameter("user_id");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/register_user?user=root&password=root");
			String query = "INSERT INTO REGISTER (user_id, name, email, country, password) VALUES ('" + id + "', '" + name + "', '" + email + "', '" + country + "', '" + password + "')";
			Statement stmt = connection.createStatement();
			int status= stmt.executeUpdate(query);
			if(status!=0) {
				out.print("<h1>Registration done Succesfully</h1>");
			}else {
				out.print("<h1>Registration failures</h1>");
			}
			
			stmt.close();
			connection.close();
		} catch (ClassNotFoundException |SQLException e) {
			out.print("Something went wrong");
		
			e.printStackTrace();
			
		} 
		
		
		
	}

	
}
