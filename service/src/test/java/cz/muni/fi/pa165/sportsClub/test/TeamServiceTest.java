package cz.muni.fi.pa165.sportsClub.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.ServiceApplicationContext;
import cz.muni.fi.pa165.sportsClub.dao.PlayerInfoDao;
import cz.muni.fi.pa165.sportsClub.dao.TeamDao;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;
import cz.muni.fi.pa165.sportsClub.service.TeamService;

/**
 * @author Simon Sudora 461460
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceApplicationContext.class)
@Transactional
public class TeamServiceTest {
    @Mock
    TeamDao teamDao;

    @Mock
    Team mockTeam;

    @Mock
    Manager mockManager;

    @Mock
    Club mockClub;

    @Mock
    Player mockPlayer;

    @Mock
    PlayerInfoDao playerInfoDao;

    @Inject
    @InjectMocks
    private TeamService teamService;

    private Team team1;
    private Team team2;
    private PlayerInfo playerInfo1;
    private PlayerInfo playerInfo2;
    private Player player1;
    private Player player2;



    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        team1 = new Team();
        team1.setCategory(Category.U13);
        team2 = new Team();
        team2.setCategory(Category.MEN);

        playerInfo1 = new PlayerInfo();
        playerInfo2 = new PlayerInfo();

        player1 = new Player();
        player2 = new Player();
    }

    @Test(expected = RuntimeException.class)
    public void createTeamTest(){
        doThrow(new RuntimeException()).when(teamDao).createTeam(mockTeam);
        teamService.createTeam(mockTeam);
    }

    @Test(expected = RuntimeException.class)
    public void deleteTeamTest(){
        doThrow(new RuntimeException()).when(teamDao).deleteTeam(mockTeam);
        teamService.deleteTeam(mockTeam);
    }

    @Test(expected = RuntimeException.class)
    public void updateTeamTest(){
        doThrow(new RuntimeException()).when(teamDao).updateTeam(mockTeam);
        teamService.updateTeam(mockTeam);
    }

    @Test
    public void getTeamOfClubByCategoryTest(){

        List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);

        when(mockClub.getManager()).thenReturn(mockManager);
		when(mockClub.getTeams()).thenReturn(teams);

        Team returnedTeam = teamService.getTeamOfClubByCategory(Category.U13, mockClub);
        assertNotNull(returnedTeam.getCategory());
        assertEquals(returnedTeam.getCategory(),Category.U13);
    }

    @Test
    public void getAllTeamsOfClubTest(){
        List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);

        when(mockClub.getManager()).thenReturn(mockManager);
		when(mockClub.getTeams()).thenReturn(teams);

        List<Team> returnedTeams = teamService.getAllTeamsOfClub( mockClub);
        assertNotNull(returnedTeams);
        assertEquals(returnedTeams, teams);
    }

    @Test(expected = RuntimeException.class)
    public void changeJerseyNumberTest(){
        player1.setId(1L);
        playerInfo1.setJerseyNumber(11);
        playerInfo1.setTeam(mockTeam);
        playerInfo1.setPlayer(player1);

        player2.setId(2L);
        playerInfo2.setJerseyNumber(15);
        playerInfo2.setTeam(mockTeam);
        playerInfo2.setPlayer(player2);

        List<PlayerInfo> playersInfos = new ArrayList<PlayerInfo>();
        playersInfos.add(playerInfo1);
        playersInfos.add(playerInfo2);

        doThrow(new RuntimeException()).when(playerInfoDao).changeJerseyNumber(mockTeam, player1, 20);
        teamService.changeJerseyNumber(player1, mockTeam, 20);

        assertEquals(playerInfo1.getJerseyNumber(),20);
    }

    @Test(expected = RuntimeException.class)
    public void removePlayerFromTeamTest(){
        mockTeam.setId(1L);

        playerInfo1.setJerseyNumber(11);
        playerInfo1.setTeam(mockTeam);
        playerInfo1.setPlayer(mockPlayer);

        List<PlayerInfo> playersInfos = new ArrayList<PlayerInfo>();
        playersInfos.add(playerInfo1);
        playersInfos.add(playerInfo2);

        when(mockTeam.getId()).thenReturn(1l);
        when(mockPlayer.getPlayerInfos()).thenReturn(playersInfos);
        doThrow(new RuntimeException()).when(playerInfoDao).deletePlayerInfoByTeamAndPlayer(mockTeam, mockPlayer);

        teamService.removePlayerFromTeam(mockPlayer, mockTeam);
    }

    @Test(expected = IllegalArgumentException.class)
    public void assignExistingPlayerToTeamTest(){
        player1.setDateOfBirth(LocalDate.parse("2000-06-15"));
        team1.setCategory(Category.U13);
        teamService.assignExistingPlayerToTeam(player1,team1,10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void assignNewPlayerToTeamTest(){
        player1.setDateOfBirth(LocalDate.parse("2000-06-15"));
        team1.setCategory(Category.U13);
        teamService.assignNewPlayerToTeam(player1,team1,10);
    }

}
