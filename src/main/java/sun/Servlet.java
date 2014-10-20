package sun;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/another-servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
		
		//TODO implement logic to process GET requests
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//TODO implement logic to process data that client sent to server with POST method.
		//It could include adding employee to repository,
		//validating email, redirecting client to a page where employee list is displayed etc.
		
		
	}
}
