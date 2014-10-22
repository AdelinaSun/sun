package sun.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import sun.exception.IncorrectEmailException;
import sun.model.Employee;

public class EmployeeRepositorySingleton {
	private static EmployeeRepositorySingleton instance;
	private List<Employee> listEmployee = new ArrayList<>();

	/*
	 * public EmployeeRepositorySingleton() { Employee employee = new
	 * Employee(); employee.setName("Name"); employee.setSurname("Surname");
	 * employee.setEmail("E-mail"); this.listEmployee.add(employee); }
	 */

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

	public void addEmployee(String fname, String lname, String email)
			throws IncorrectEmailException, ServletException, IOException {
		Employee employee = new Employee();
		employee.setName(fname);
		employee.setSurname(lname);
		employee.setEmail(email);

		if (employee.getName().equals("") || employee.getSurname().equals("")
				|| employee.getEmail().equals("")) {
			throw new ServletException("Заполните все поля");
		}

		if (!employee.getName().equals("") & !employee.getSurname().equals("")
				& !employee.getEmail().equals("")) {
			for (Employee employeeElement : this.listEmployee) {
				if (employeeElement.getEmail().equals(employee.getEmail())) {
					throw new IncorrectEmailException(
							"Этот электронный адрес уже существует.");
				}
			}
		}

		this.listEmployee.add(employee);

	}

	public List<Employee> getAllEmployees() {
		return this.listEmployee;
	}
}
