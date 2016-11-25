package cz.muni.fi.pa165.sportsClub.test;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dao.TeamDao;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Team;
import cz.muni.fi.pa165.sportsClub.service.ClubService;
import cz.muni.fi.pa165.sportsClub.service.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @author Simon Sudora 461460
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@Transactional
public class TeamServiceTest {
    @Mock
    TeamDao teamDao;

    @Mock
    Team mockTeam;

    @Inject
    @InjectMocks
    private TeamService teamService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = RuntimeException.class)
    public void createClubTest(){
        doThrow(new RuntimeException()).when(teamDao).createTeam(mockTeam);
        teamService.createTeam(mockTeam);
    }

    @Test(expected = RuntimeException.class)
    public void deleteClubTest(){
        doThrow(new RuntimeException()).when(teamDao).deleteTeam(mockTeam);
        teamService.deleteTeam(mockTeam);
    }

    @Test(expected = RuntimeException.class)
    public void updateClubTest(){
        doThrow(new RuntimeException()).when(teamDao).updateTeam(mockTeam);
        teamService.updateTeam(mockTeam);
    }

    @Test
    public void getClubByIdTest(){
        when(teamDao.getTeamById(1L)).thenReturn(mockTeam);
        Team returnedTeam = teamService.getTeamById(1L);
        assertEquals(mockTeam,returnedTeam);
    }
}
