package sun.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;

import sun.database.DataBase;
import sun.database.EmployeeJDBCTemplate;
import sun.exception.IncorrectEmailException;
import sun.model.Employee;

public class EmployeeRepositorySingleton {
	private static EmployeeRepositorySingleton instance;
	private List<Employee> listEmployee = new ArrayList<>();
	@Autowired
	private EmployeeJDBCTemplate employeeJDBCTemplate;
	//@Autowired
	//private Employee employee;
	
	public static EmployeeRepositorySingleton getRepository() {

		if (instance == null) {
			synchronized (EmployeeRepositorySingleton.class) {
				if (instance == null) {
					instance = new EmployeeRepositorySingleton();
				}
			}
		}
		return instance;
	}

	//public void addEmployee(Employee employee)
	public void addEmployee(Employee employee)
			throws IncorrectEmailException, ServletException, IOException,
			SQLException {
		
		
		/*Employee employee = new Employee();
		employee.setName(fname);
		employee.setSurname(lname);
		employee.setEmail(email);*/

		if (employee.getName().equals("") || employee.getSurname().equals("")
				|| employee.getMail().equals("")) {
			throw new ServletException("Заполните все поля");
		}

		if (!employee.getName().equals("") & !employee.getSurname().equals("")
				& !employee.getMail().equals("")) {
			for (Employee employeeElement : this.listEmployee) {
				if (employeeElement.getMail().equals(employee.getMail())) {
					throw new IncorrectEmailException(
							"Этот электронный адрес уже существует.");
				}
			}
		}
		//employeeJDBCTemplate.addDataToDb(employee);
		//DataBase.getDatabase().addDataToDb(employee);
		// this.listEmployee.add(employee);

	}

	/*public void addEmployee2(String fname, String lname, String email)
			throws IncorrectEmailException, ServletException, IOException,
			SQLException {
		if (this.listEmployee.size() == 0) {
			listEmployee.addAll(DataBase.getDatabase().getDataFromDb());
			this.addEmployee(fname, lname, email);
		} else {
			this.addEmployee(fname, lname, email);
		}
	}*/

	public List<Employee> getAllEmployees() throws SQLException {
		return this.listEmployee;
	}
}
