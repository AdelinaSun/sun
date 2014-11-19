//package sun.listener;
//
//import java.sql.SQLException;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import sun.database.DataBase;
//import sun.database.EmployeeJDBCTemplate;
//
///**
// * Application Lifecycle Listener implementation class ContextListener
// *
// */
//@WebListener
//public class ContextListener implements ServletContextListener {
//
//	/**
//	 * Default constructor.
//	 */
//	public ContextListener() {
//	}
//
//	/**
//	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
//	 */
//	public void contextInitialized(ServletContextEvent sce) {
//		/*sce.getServletContext().setAttribute("employees",
//				EmployeeJDBCTemplate.getEmployeeJDBCTemplate().getDataFromDb());*/
//
//		/*
//		 * try { sce.getServletContext().setAttribute("employees",
//		 * DataBase.getDatabase().getDataFromDb()); } catch (SQLException e) {
//		 * // TODO Auto-generated catch block e.printStackTrace(); }
//		 */
//
//		// sce.getServletContext().setAttribute("employees",
//		// EmployeeRepositorySingleton.getRepository().getAllEmployees());
//
//	}
//
//	/**
//	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
//	 */
//	public void contextDestroyed(ServletContextEvent sce) {
//	}
//
//}
