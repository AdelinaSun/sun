package sun.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.exception.IncorrectEmailException;
import sun.repository.EmployeeRepositorySingleton;

@WebServlet("/another-servlet.html")
public class EmploeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,
				response);
		request.setAttribute("employees", EmployeeRepositorySingleton
				.getRepository().getAllEmployees());

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		try {
			EmployeeRepositorySingleton.getRepository().addEmployee(
					request.getParameter("name"),
					request.getParameter("surname"),
					request.getParameter("mail"));
		} catch (ServletException | IncorrectEmailException e) {
			request.setAttribute("errMsg", e);
		}
		doGet(request, response);
	}

	
}
