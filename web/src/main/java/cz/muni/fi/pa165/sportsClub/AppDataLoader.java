package cz.muni.fi.pa165.sportsClub;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import cz.muni.fi.pa165.sportsClub.testUtils.ScriptRunner;

@Component
public class AppDataLoader {

	@Inject
	DataSource ds;

	@PostConstruct
	public void contextDestroyed() {
		String baseMessage = "Problem to start the app: ";
		String pathToSQLScript = "src/main/resources/appInit.sql";
		try (Connection conn = ds.getConnection()) {
			ScriptRunner runner = new ScriptRunner(conn, false, false);
			runner.runScript(new BufferedReader(new FileReader(pathToSQLScript)));
		} catch (SQLException e) {
			System.out.println(baseMessage + "SQL script execution problem, path:" + pathToSQLScript);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println(baseMessage + "File not found on path: " + pathToSQLScript);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(baseMessage + "Error reading file on path: " + pathToSQLScript);
			e.printStackTrace();
		}
	}
}
