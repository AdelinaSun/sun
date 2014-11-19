package sun.database.Interface;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import sun.model.Employee;

public interface EmployeeDAO {

	 public void addDataToDb(Employee employee) throws Exception;
	 public List<Employee> getDataFromDb();
	//void setDataSource(DataSource dataSource);
	
}
