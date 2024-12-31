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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.oracle.wls.shaded.java_cup.runtime.Scanner;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        

	        String updateId = request.getParameter("update_id");
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String country = request.getParameter("country");
	        String password = request.getParameter("password");

	        try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/register_user?user=root&password=root");
	        
	             PreparedStatement statement = connection.prepareStatement("UPDATE REGISTER SET name=?, email=?, country=?, password=? WHERE user_id=?"); 

	            statement.setString(1, name);
	            statement.setString(2, email);
	            statement.setString(3, country);
	            statement.setString(4, password);
	            statement.setString(5, updateId);

	            int rowsUpdated = statement.executeUpdate();

	            if (rowsUpdated > 0) {
	                out.println("Record updated successfully!");
	            } else {
	                out.println("Record update failed!");
	            }

	        }
	             catch (SQLException | ClassNotFoundException e) {
	            out.println("Something went wrong!");
	            e.printStackTrace();
	        }
	    }
	
	 }

