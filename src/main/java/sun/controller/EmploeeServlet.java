package sun.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.database.DataBase;
import sun.exception.IncorrectEmailException;
import sun.repository.EmployeeRepositorySingleton;

@WebServlet("/another-servlet.html")
public class EmploeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,
				response);

		try {
		request.setAttribute("employees", DataBase.getDatabase().getDataFromDb());}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// DataBase.getDatabase().getDataFromDb()
		// EmployeeRepositorySingleton.getRepository().getAllEmployees()
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// if (EmployeeRepositorySingleton.getRepository().)

		// doGet(request, response);
		try {
			
			EmployeeRepositorySingleton.getRepository().addEmployee(
					request.getParameter("name"),
					request.getParameter("surname"),
					request.getParameter("mail"));
			request.getServletContext().setAttribute("employees",
					DataBase.getDatabase().getDataFromDb());
		} catch (ServletException | IncorrectEmailException | SQLException e) {
			request.setAttribute("errMsg", e);
		}
		doGet(request, response);
	}

}
