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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class Fetch
 */
@WebServlet("/Fetch")
public class Fetch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException {
		    response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    String fetchId = request.getParameter("fetch_id");

		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/register_user", "root", "root");

		        
		        String query = "SELECT * FROM REGISTER WHERE USER_ID= '"+fetchId+"'"; 
		        Statement stmt = connection.createStatement();
		        ResultSet rs = stmt.executeQuery(query);

		        out.println("<table border='1'>");
		        out.println("<tr><th>User ID</th><th>Name</th><th>Email</th><th>Country</th><th>Password</th></tr>");

		        while (rs.next()) {
		            String id = rs.getString("user_id");
		            String name = rs.getString("name");
		            String email = rs.getString("email");
		            String country = rs.getString("country");
		            String password = rs.getString("password");

		            // Display fetched data in HTML table
		            out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" + email + "</td><td>" + country + "</td><td>" + password + "</td></tr>");
		        }

		        out.println("</table>");

		        rs.close();
		        stmt.close();
		        connection.close();

		    } catch (ClassNotFoundException | SQLException e) {
		        out.println("Something went wrong!");
		        e.printStackTrace();
		    }
		}
	}

	


