package cz.muni.fi.pa165.sportsClub.test;

import cz.muni.fi.pa165.sportsClub.dao.ManagerDao;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.service.ManagerService;
import cz.muni.fi.pa165.sportsClub.service.ServiceApplicationContext;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andrej 410433
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceApplicationContext.class)
@Transactional
public class ManagerServiceTest {

    @Inject
    @InjectMocks
    private ManagerService managerService;

    @Mock
    private ManagerDao managerDao;

    @Mock
    private Manager manager;

    @Mock
    private Club club;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        manager = new Manager() {
            {
                setId(1L);
                setFirstName("Andrej");
                setLastName("Bonis");
                setEmail("andrej@gmail.com");
                setPassword("123456789");
                setClub(club);
            }
        };

        club = new Club() {
            {
                setName("FC Nitra");
                setManager(manager);
            }
        };
    }

    @Test(expected = RuntimeException.class)
    public void updateManagerTest() {
        doThrow(new RuntimeException()).when(managerDao).updateManager(manager);
        managerService.updateManager(manager);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void updateNullManagerTest() {
        doThrow(new IllegalArgumentException()).when(managerDao).updateManager(null);
        managerService.updateManager(null);
    }

    @Test(expected = RuntimeException.class)
    public void deleteManagerTest() {
        doThrow(new RuntimeException()).when(managerDao).deleteManager(manager);
        managerService.deleteManager(manager);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNullManagerTest() {
        doThrow(new IllegalArgumentException()).when(managerDao).deleteManager(null);
        managerService.deleteManager(null);
    }
    
   @Test
   public void getManagerByIdTest() {
       when(managerDao.getManagerById(1L)).thenReturn(manager);
       Manager found = managerService.getManagerById(1L);
       assertEquals(manager, found);
   }
   
   @Test 
   public void getManagerByNonExistingIdTest() {
       assertNull(managerService.getManagerById(10L));
   }
   
   @Test
   public void getManagerByEmaliTest() {
       when(managerDao.getManagerByEmail("andrej@gmail.com")).thenReturn(manager);
       Manager found = managerService.getManagerByEmail("andrej@gmail.com");
       assertEquals(manager, found);
   }
   
   @Test
   public void getManagerByNonExistingEmailTest() {
       assertNull(managerService.getManagerByEmail("bonis@gmail.com"));
   }
}
