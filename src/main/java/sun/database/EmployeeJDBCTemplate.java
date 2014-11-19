package sun.database;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import sun.database.Interface.EmployeeDAO;
import sun.exception.IncorrectEmailException;
import sun.model.Employee;
import sun.repository.EmployeeMapper;

public class EmployeeJDBCTemplate implements EmployeeDAO {
	private List<Employee> listEmployee = new ArrayList<>();

	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	private int idEmployee;
	
	public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
		this.jdbcTemplateObject = jdbcTemplateObject;
	}
	
	public void setDataSource(DataSource dataSource) {
		//this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void addDataToDb(Employee employee) throws Exception {
		
		if (employee.getName().equals("") || employee.getSurname().equals("")
				|| employee.getMail().equals("")) {
			throw new ServletException("Заполните все поля");
		}

		if (!employee.getName().equals("") & !employee.getSurname().equals("")
				& !employee.getMail().equals("")) {
			this.listEmployee = this.getDataFromDb();
			for (Employee employeeElement : this.listEmployee) {
				if (employeeElement.getMail().equals(employee.getMail())) {
					throw new IncorrectEmailException(
							"Этот электронный адрес уже существует.");
				}
			}
		}
		
		String selectIdSQL = "SELECT max(EMPLOYEE_ID) from EMPLOYEES";
		@SuppressWarnings("deprecation")
		int id = jdbcTemplateObject.queryForInt(selectIdSQL);
		this.idEmployee = id + 1;
		
		String insertData = "INSERT INTO EMPLOYEES "
				+ "(EMPLOYEE_ID, NAME, SURNAME, EMAIL)" + "VALUES" + "("
				+ this.idEmployee + ",'" + employee.getName() + "','"
				+ employee.getSurname() + "','" + employee.getMail()
				+ "')";
		jdbcTemplateObject.execute(insertData);
				
	}

	@Override
	public List<Employee> getDataFromDb() {
		String selectTableSQL = "SELECT EMPLOYEE_ID, NAME, SURNAME, EMAIL from EMPLOYEES order by EMPLOYEE_ID";
		return jdbcTemplateObject.query(selectTableSQL, new EmployeeMapper());		
	}

}
