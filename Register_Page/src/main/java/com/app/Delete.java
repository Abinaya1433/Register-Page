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
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();

	    String deleteId = request.getParameter("delete_id"); // Get the ID of the record to delete

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/register_user?user=root&password=root");

	        // Write SQL query to delete data from the table
	        String query = "DELETE FROM REGISTER WHERE user_id='" + deleteId + "'";

	        Statement stmt = connection.createStatement();
	        int status = stmt.executeUpdate(query);

	        if (status > 0) {
	            out.println("<h1>Record deleted successfully!</h1>");
	        } else {
	            out.println("<h1>Record deletion failed!</h1>");
	        }

	        stmt.close();
	        connection.close();

	    } catch (ClassNotFoundException | SQLException e) {
	        out.println("Something went wrong!");
	        e.printStackTrace();
	    }
	}
}
