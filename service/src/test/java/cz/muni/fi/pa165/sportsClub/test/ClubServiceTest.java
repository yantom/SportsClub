package cz.muni.fi.pa165.sportsClub.test;

import cz.muni.fi.pa165.sportsClub.dao.ClubDao;
import cz.muni.fi.pa165.sportsClub.service.ClubService;
import cz.muni.fi.pa165.sportsClub.service.ServiceApplicationContext;
import org.hibernate.service.spi.ServiceException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import javax.inject.Inject;

/**
 * @author Simon Sudora 461460
 */
@ContextConfiguration(classes = ServiceApplicationContext.class)
public class ClubServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    ClubDao clubDao;

    @Inject
    @InjectMocks
    private ClubService clubService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFreePlayersTest(){

    }
}
