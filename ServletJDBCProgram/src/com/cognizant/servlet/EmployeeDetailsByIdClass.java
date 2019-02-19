package com.cognizant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeDetailsByIdClass
 */
@WebServlet(
		name = "EmployeeDetailsById", 
		urlPatterns = { "/getEmployeeById" }, 
		initParams = { 
				@WebInitParam(name = "ConfigInfo", value = "DBInfo")
		})
public class EmployeeDetailsByIdClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private String driver;
       private String url;
       private String username;
       private String password;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeDetailsByIdClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String baseName=config.getInitParameter("ConfigInfo");
		ResourceBundle bundle=ResourceBundle.getBundle(baseName);
		this.driver=bundle.getString("driver");
		this.url=bundle.getString("url");
		this.username=bundle.getString("username");
		this.password=bundle.getString("password");
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			int empId=Integer.parseInt(request.getParameter("empId"));
			
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try(
					Connection connection=DriverManager.getConnection(url,username,password);
					PrintWriter out=response.getWriter();
					
					)
			{
				PreparedStatement statement=connection.prepareStatement("select * from EMPLOYEESCOGNIZANT where EMP_ID=?");
				statement.setInt(1, empId);
				ResultSet resultSet=statement.executeQuery();
				
				out.println("<html><head><title>All Employee Details</title></head>");
				out.println("<body><table cellspacing='2' cellpadding='2' border='2'>");
				out.println("<tr><th>Emp ID</th><th>EmpNAme</th><th>EMP SAL</th><th> EMP DES</th></tr>");
				while(resultSet.next())
				{
					out.println("<tr>");
					out.println("<td>" + resultSet.getInt("EMP_ID") + "</td>");
					out.println("<td>" + resultSet.getString("EMP_NAME") + "</td>");
					out.println("<td>" + resultSet.getInt("EMP_SALARY") + "</td>");
					out.println("<td>" + resultSet.getString("EMP_DESIGNATION") + "</td>");
					out.println("</tr>");
					
					
				}
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
	}

}
