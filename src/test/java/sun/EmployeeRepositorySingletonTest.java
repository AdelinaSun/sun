package sun;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import sun.exception.IncorrectEmailException;
import sun.repository.EmployeeRepositorySingleton;

public class EmployeeRepositorySingletonTest {

	@BeforeClass
	public static void beforeClass() throws Exception {
		EmployeeRepositorySingleton.getRepository().addEmployee("Иван",
				"Иванов", "ivanov@mail.ru");
	}

	@Test
	public void testGetRepositoryReturnOneInstance() {
		EmployeeRepositorySingleton first = EmployeeRepositorySingleton
				.getRepository();
		EmployeeRepositorySingleton second = EmployeeRepositorySingleton
				.getRepository();
		Assert.assertEquals(first, second);
	}

	@Test(expected = sun.exception.IncorrectEmailException.class)
	public void testAddEmployeWithIncorrectEmail()
			throws IncorrectEmailException, ServletException, IOException {
//		EmployeeRepositorySingleton.getRepository().addEmployee("Иван",
//				"Иванов", "ivanov@mail.ru");
	}

}
