package cz.muni.fi.pa165.sportsClub.test;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dao.ManagerDao;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import javax.inject.Inject;
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
@Transactional
public class ManagerTest {

    @Inject
    private ManagerDao managerDao;

    private static Manager managerTest;

    @Before
    public void beforeTest() {
        managerTest = new Manager();
        managerTest.setEmail("gretzky99@gmail.com");
        managerTest.setPassword("TheBest99");
        managerTest.setFirstName("Wayne");
        managerTest.setLastName("Gretzky");
        Club club = new Club();
        club.setManager(managerTest);
        managerTest.setClub(club);
    }

    @Test
    public void testCreateManager() {
        managerDao.createManager(managerTest);
        assertEquals(managerTest, managerDao.getManagerById(managerTest.getId()));
    }

    @Test
    public void testUpdateManager() {
        managerDao.createManager(managerTest);
        managerTest.setMobile("555-123-456");
        managerDao.updateManager(managerTest);
        assertEquals(managerTest, managerDao.getManagerById(managerTest.getId()));
    }

    @Test
    public void testDeleteManager() {
        managerDao.createManager(managerTest);
        assertNotNull(managerDao.getManagerById(managerTest.getId()));
        managerDao.deleteManager(managerTest);
        assertNull(managerDao.getManagerById(managerTest.getId()));
    }

    @Test
    public void testGetManagerById() {
        managerDao.createManager(managerTest);
        Manager retrieved = managerDao.getManagerById(managerTest.getId());
        assertEquals(managerTest, retrieved);
    }

    @Test
    public void testGetManagerByEmail() {
        managerDao.createManager(managerTest);
        Manager retrieved = managerDao.getManagerByEmail(managerTest.getEmail());
        assertEquals(managerTest, retrieved);
    }

}
