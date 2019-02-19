package com.cognizant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.entity.Employees;

/**
 * Servlet implementation class JSONServletClass
 */
@WebServlet(name = "JSONServlet", urlPatterns = { "/jsonservlet" })
public class JSONServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSONServletClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection=null;
		try {
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe","system","cognizant");
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("select * from EMPLOYEESCOGNIZANT");
			List<Employees> empList=new ArrayList<Employees>();
			while(resultSet.next())
			{
				Employees employees=new Employees();
				employees.setEmpId(resultSet.getInt("EMP_ID"));
				employees.setEmpName(resultSet.getString("EMP_NAME"));
				employees.setEmpSalary(resultSet.getDouble("EMP_SALARY"));
				employees.setEmpDesignation(resultSet.getString("EMP_DESIGNATION"));
				empList.add(employees);
			}
			PrintWriter out=response.getWriter();
			empList.forEach((e)->{
				
				out.println(e);
			});
			
			/*String jsonArray=JSONObject.valueToString(empList);
			 * response.setContentType("application/json");
			 * ServletOutputStream sos=response.getOutputStream();
			 * sos.write(jsonArray.getBytes());
			 * sos.close();
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
