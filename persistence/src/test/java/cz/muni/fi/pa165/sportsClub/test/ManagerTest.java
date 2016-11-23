package cz.muni.fi.pa165.sportsClub.test;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dao.ManagerDao;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import javax.inject.Inject;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test methods for ManagerDao
 *
 * @author Andrej 410433
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class ManagerTest {

    @Inject
    private ManagerDao managerDao;

    private static Manager managerTest;
    private static Club clubTest;
    
    @Before
    public void beforeTest() {
        
        managerTest = new Manager();
        managerTest.setEmail("gretzky99@gmail.com");
        managerTest.setPassword("TheBest99");
        managerTest.setFirstName("Wayne");
        managerTest.setLastName("Gretzky");
        
        clubTest = new Club();
        clubTest.setName("HK Nitra");
        clubTest.setManager(managerTest);
        
        managerTest.setClub(clubTest);
    }
    
    @After
    public void AfterTest() {
        managerDao.deleteManager(managerTest);
    }

    @Test
    public void testCreateManager() {
        assertNull(managerTest.getId());
        
        managerDao.createManager(managerTest);
        assertNotNull(managerTest.getId());
        
        Manager createdManager = managerDao.getManagerById(managerTest.getId());
        assertEquals(managerTest, createdManager);
    }

    @Test
    public void testUpdateManager() {
        managerDao.createManager(managerTest);
        
        managerTest.setMobile("555123456");
        managerDao.updateManager(managerTest);
        Manager updatedManager = managerDao.getManagerById(managerTest.getId());
       
        assertEquals(managerTest, updatedManager);
    }

    @Test
    public void testDeleteManager() {
        
        managerDao.createManager(managerTest);
        assertNotNull(managerDao.getManagerById(managerTest.getId()));
        
        managerDao.deleteManager(managerTest);
        Manager deletedManager = managerDao.getManagerById(managerTest.getId());
        
        assertNull(deletedManager);
    }

    @Test
    public void testGetManagerById() {
        managerDao.createManager(managerTest);
        
        Manager foundManager = managerDao.getManagerById(managerTest.getId());
        assertEquals(managerTest, foundManager);
    }

    @Test
    public void testGetManagerByEmail() {
        managerDao.createManager(managerTest);
        
        Manager foundManager = managerDao.getManagerByEmail(managerTest.getEmail());
        assertEquals(managerTest, foundManager);
    }

}
