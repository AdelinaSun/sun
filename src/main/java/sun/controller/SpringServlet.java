package sun.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sun.exception.ServletException;
import sun.repository.EmployeeRepositorySingleton;

@Controller
@RequestMapping("/Spring.html")
public class SpringServlet {
	public static final int DEFAULT_SPRITTLES_PER_PAGE = 25;
	private EmployeeRepositorySingleton employeeRepositorySingleton;

	@RequestMapping(method = RequestMethod.GET)
	public String getEmployees(ModelMap model) throws SQLException {
		model.addAttribute("employees",
				employeeRepositorySingleton.getAllEmployees());
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String postEmployees(@RequestParam("name") String name,
			@RequestParam("surname") String surname,
			@RequestParam("email") String email, BindingResult result,
			ModelMap model) throws SQLException,
			javax.servlet.ServletException, IOException {
		if (!result.hasErrors()) {
			try {
				employeeRepositorySingleton.addEmployee(name, surname, email);

			} catch (ServletException e) {
				model.addAttribute("errMsg", e);
			}
		}
		return this.postEmployees(name, surname, email, result, model);
	}
}
