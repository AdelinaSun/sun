package sun.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sun.database.EmployeeJDBCTemplate;
import sun.exception.IncorrectEmailException;
import sun.exception.ServletException;
import sun.model.Employee;
import sun.repository.EmployeeRepositorySingleton;

@Controller
@RequestMapping("/Spring")
public class SpringServlet {
	public static final int DEFAULT_SPRITTLES_PER_PAGE = 25;
	private EmployeeRepositorySingleton employeeRepositorySingleton;
	@Autowired
	private EmployeeJDBCTemplate employeeJDBCTemplate;

	// @Autowired
	// private Employee employee;

	@RequestMapping(method = RequestMethod.GET)
	public String getEmployees(ModelMap model) throws SQLException {
		model.addAttribute("employees", employeeJDBCTemplate.getDataFromDb());
		return "index";
	}

	public void setEmployeeJDBCTemplate(
			EmployeeJDBCTemplate employeeJDBCTemplate) {
		this.employeeJDBCTemplate = employeeJDBCTemplate;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String postEmployees(@ModelAttribute Employee employee,
			BindingResult result, ModelMap model) throws SQLException,
			Exception, ServletException {

		if (!result.hasErrors()) {
			try {
				employeeJDBCTemplate.addDataToDb(employee);
			} catch (IncorrectEmailException e) {
				model.addAttribute("errMsg", e);
			} catch (ServletException e) {
				model.addAttribute("errMsg", e);
			} catch (Exception e) {
				model.addAttribute("errMsg", e);
			}
		}
		return this.getEmployees(model);
	}
}
