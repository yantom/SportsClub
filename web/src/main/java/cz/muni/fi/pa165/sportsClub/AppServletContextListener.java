package cz.muni.fi.pa165.sportsClub;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.muni.fi.pa165.sportsClub.testUtils.ScriptRunner;

/****
 * 
 * @author Jan Tomasek
 *
 */
public class AppServletContextListener implements ServletContextListener {

	@Inject
	DataSource ds;

	final static Logger logger = LoggerFactory.getLogger(AppServletContextListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {
		String baseMessage = "Problem to start the app: ";
		String pathToSQLScript = "src/main/resources/appInit.sql";
		try (Connection conn = ds.getConnection()) {
			ScriptRunner runner = new ScriptRunner(conn, false, false);
			runner.runScript(new BufferedReader(new FileReader(pathToSQLScript)));
		} catch (SQLException e) {
			System.out.println(baseMessage + "SQL script execution problem, path: " + pathToSQLScript);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println(baseMessage + "File not found on path: " + pathToSQLScript);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(baseMessage + "Error reading file on path: " + pathToSQLScript);
			e.printStackTrace();
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {

	}

}