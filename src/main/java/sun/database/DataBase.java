package sun.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sun.model.Employee;

public class DataBase {
	private int idEmployee;
	private static DataBase instance = null;
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String DB_USER = "Inna";
	private static final String DB_PASSWORD = "inna02"; // login,password
														// removed
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";

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

	public Connection getConnection() throws SQLException {

		Connection connection = null;

		try {
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

		return connection;

	}

	public void addDataToDb(Employee employee) throws SQLException {
		// TODO Auto-generated method stub

		Connection connection = null;
		Statement statement = null;

		try {
			connection = getConnection();
			statement = connection.createStatement();
			
			String selectIdSQL = "SELECT max(EMPLOYEE_ID) from EMPLOYEES";
			ResultSet resultSet = statement.executeQuery(selectIdSQL);
			resultSet.next();
			this.idEmployee = resultSet.getInt("max(EMPLOYEE_ID)") + 1;

			// SQL запрос
			String insertData = "INSERT INTO EMPLOYEES "
					+ "(EMPLOYEE_ID, NAME, SURNAME, EMAIL) " + "VALUES" + "("
					+ this.idEmployee + ",'" + employee.getName() + "','"
					+ employee.getSurname() + "','" + employee.getEmail()
					+ "')";
			statement.executeUpdate(insertData);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public ArrayList<Employee> getDataFromDb() throws SQLException {
		ArrayList<Employee> dbEmployee = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		String selectTableSQL = "SELECT EMPLOYEE_ID, NAME, SURNAME, EMAIL from EMPLOYEES order by EMPLOYEE_ID";

		try {
			connection = getConnection();
			statement = connection.createStatement();

			// выбираем данные с БД
			ResultSet resultSet = statement.executeQuery(selectTableSQL);

			// выполнить SQL запрос
			// И если что то было получено то цикл while сработает
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setIdEmployee(resultSet.getInt("Employee_ID"));
				employee.setName(resultSet.getString("Name"));
				employee.setSurname(resultSet.getString("SURNAME"));
				employee.setEmail(resultSet.getString("EMAIL"));
				dbEmployee.add(employee);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
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
