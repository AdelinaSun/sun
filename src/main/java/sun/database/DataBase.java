package sun.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import sun.model.Employee;

public class DataBase {
	@Autowired
	private EmployeeJDBCTemplate employeeJDBCTemplate;
	
	private int idEmployee;
	private static DataBase instance = null;
	//private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE";
	//private static final String DB_USER = "Inna";
	//private static final String DB_PASSWORD = "inna02"; // login,password
														// removed
	//private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";

	public static DataBase getDatabase() {
		if (instance == null) {
			synchronized (DataBase.class) {
				if (instance == null) {
					instance = new DataBase();
				}
			}
		}
		return instance;
	}
		
	/*public EmployeeJDBCTemplate getConnection() throws SQLException {

		//Connection connection = null;

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext ("Employee-servlet.xml");
		EmployeeJDBCTemplate employeeJDBCTemplate = 
			      (EmployeeJDBCTemplate) applicationContext.getBean("EmployeeJDBCTemplate");
				
		//DataSource ds = (DataSource) ctx.getBean ("dataSource");
		
		/*try {
			Class theDriver = Driver.class.forName(DB_DRIVER);
			try {
				connection = DriverManager.getConnection(DB_CONNECTION,
						DB_USER, DB_PASSWORD);
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}

		catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}

		return employeeJDBCTemplate;

	}*/

	public void addDataToDb(Employee employee) throws SQLException {
		// TODO Auto-generated method stub

		EmployeeJDBCTemplate connection = null;
		Statement statement = null;

		try {
			
			//connection = getConnection();
			//statement = connection.getConnection().createStatement();
			
			String selectIdSQL = "SELECT max(EMPLOYEE_ID) from EMPLOYEES";
			ResultSet resultSet = statement.executeQuery(selectIdSQL);
			resultSet.next();
			this.idEmployee = resultSet.getInt("max(EMPLOYEE_ID)") + 1;

			// SQL Р В·Р В°Р С—РЎР‚Р С•РЎРѓ
			String insertData = "INSERT INTO EMPLOYEES "
					+ "(EMPLOYEE_ID, NAME, SURNAME, EMAIL)" + "VALUES" + "("
					+ this.idEmployee + ",'" + employee.getName() + "','"
					+ employee.getSurname() + "','" + employee.getMail()
					+ "')";
			statement.executeUpdate(insertData);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			/*if (connection != null) {
				connection.close();
			}*/
		}
	}

	public ArrayList<Employee> getDataFromDb() throws SQLException {
		ArrayList<Employee> dbEmployee = new ArrayList<>();
		EmployeeJDBCTemplate connection = null;
		Statement statement = null;
		String selectTableSQL = "SELECT EMPLOYEE_ID, NAME, SURNAME, EMAIL from EMPLOYEES order by EMPLOYEE_ID";

		try {
			
		//	connection = getConnection();
			//statement = connection.getConnection().createStatement();

			// Р Р†РЎвЂ№Р В±Р С‘РЎР‚Р В°Р ВµР С� Р Т‘Р В°Р Р…Р Р…РЎвЂ№Р Вµ РЎРѓ Р вЂ�Р вЂќ
			ResultSet resultSet = statement.executeQuery(selectTableSQL);

			// Р Р†РЎвЂ№Р С—Р С•Р В»Р Р…Р С‘РЎвЂљРЎРЉ SQL Р В·Р В°Р С—РЎР‚Р С•РЎРѓ
			// Р пїЅ Р ВµРЎРѓР В»Р С‘ РЎвЂЎРЎвЂљР С• РЎвЂљР С• Р В±РЎвЂ№Р В»Р С• Р С—Р С•Р В»РЎС“РЎвЂЎР ВµР Р…Р С• РЎвЂљР С• РЎвЂ Р С‘Р С”Р В» while РЎРѓРЎР‚Р В°Р В±Р С•РЎвЂљР В°Р ВµРЎвЂљ
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setIdEmployee(resultSet.getInt("Employee_ID"));
				employee.setName(resultSet.getString("Name"));
				employee.setSurname(resultSet.getString("SURNAME"));
				employee.setMail(resultSet.getString("EMAIL"));
				dbEmployee.add(employee);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			/*if (connection != null) {
				connection.close();
			}*/
		}
		return dbEmployee;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int id) {
		this.idEmployee = id;
	}

}
