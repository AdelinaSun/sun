package sun.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.Exception.IncorrectEmailException;
import sun.Repository.EmployeeRepositorySingleton;

@WebServlet("/another-servlet.html")
public class EmploeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//EmployeeRepositorySingleton employeeRepositorySingleton = new EmployeeRepositorySingleton();
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		request.setAttribute("employees", EmployeeRepositorySingleton.getRepository().getAllEmployees());
		
		//TODO implement logic to process GET requests
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		try {
			EmployeeRepositorySingleton.getRepository().addEmployee(request.getParameter("name"), request.getParameter("surname"), request.getParameter("mail"));
		} catch (IncorrectEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
		
		//TODO implement logic to process data that client sent to server with POST method.
		//It could include adding employee to repository,
		//validating email, redirecting client to a page where employee list is displayed etc.
	}
}
