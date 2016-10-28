package cz.muni.fi.pa165.sportsClub.test;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;

/**
 * 
 * @author Jan Tomasek
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class TeamTest {
	
	@PersistenceUnit
	private EntityManagerFactory emf;

	@Inject
	private TeamDao teamDao;
	
	@BeforeClass
	public static void setup(){
	}
	
	@Test
	public void test(){
	}
}
