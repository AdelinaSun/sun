package sun.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import sun.model.Employee;

public class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setIdEmployee(resultSet.getInt("Employee_ID"));
		employee.setName(resultSet.getString("Name"));
		employee.setSurname(resultSet.getString("SURNAME"));
		employee.setMail(resultSet.getString("EMAIL"));
		return employee;
	}
	
	

	/*public class StudentMapper implements RowMapper<Student> {
	   public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
	      Student student = new Student();
	      student.setId(rs.getInt("id"));
	      student.setName(rs.getString("name"));
	      student.setAge(rs.getInt("age"));
	      return student;
	   }
	}*/

	
	
}
