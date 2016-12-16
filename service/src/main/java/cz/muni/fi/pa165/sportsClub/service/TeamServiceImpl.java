package cz.muni.fi.pa165.sportsClub.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.sportsClub.dao.ClubDao;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.sportsClub.dao.PlayerInfoDao;
import cz.muni.fi.pa165.sportsClub.dao.TeamDao;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.exception.DaoLayerException;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

@Service
public class TeamServiceImpl implements TeamService {
    @Inject
    private TeamDao teamDao;

    @Inject
    private ClubDao clubDao;

    @Inject
    private PlayerInfoDao playerInfoDao;

    public void createTeam(Team t) {
        try {
            teamDao.createTeam(t);
        } catch (Exception e) {
            throw new DaoLayerException("can not create team", e);
        }
    }

    public void updateTeam(Team t) {
        try {
            teamDao.updateTeam(t);
        } catch (Exception e) {
            throw new DaoLayerException("can not update team", e);
        }
    }

    public void deleteTeam(Team t) {
        try {
            teamDao.deleteTeam(t);
        } catch (Exception e) {
            throw new DaoLayerException("can not delete team", e);
        }
    }

    public Team getTeamById(Long teamId) {
        try {
            return teamDao.getTeamById(teamId);
        } catch (Exception e) {
            throw new DaoLayerException("can not find team by id", e);
        }
    }

    public Team getTeamOfClubByCategory(Category category, Club c) {
        try {
            List<Team> teams = c.getTeams();
            for (Team team : teams) {
                if (team.getCategory().equals(category)) {
                    return team;
                }
            }
            return null;
        } catch (Exception e) {
            throw new DaoLayerException("can not find team of club by category", e);
        }

    }

    public List<Team> getAllTeamsOfClub(Club c) {
        try {
            return c.getTeams();
        } catch (Exception e) {
            throw new DaoLayerException("can not obtain all teams of club", e);
        }

    }

    public List<PlayerInfo> getPlayerInfos(Team t) {
        try {
            return t.getPlayerInfos();
        } catch (Exception e) {
            throw new DaoLayerException("can not obtain players infos", e);
        }
    }

    private boolean playerMeetsAgeLimit(Player player, Team team){
        LocalDate today = LocalDate.now();
        LocalDate birthday = player.getDateOfBirth();

        int age = Period.between(birthday, today).getYears();
        team.getCategory().getAgeLimit();
        return age <= team.getCategory().getAgeLimit();
    }

    public void assignExistingPlayerToTeam(Player p, Team t, int jerseyNumber) {
        if(!playerMeetsAgeLimit(p,t)){
            throw new IllegalArgumentException("Player does not match age criteria of team");
        }
        if(!teamDao.isJerseyNumberUnique(t,jerseyNumber)){
            throw new IllegalArgumentException("Jersey number is not unique in the team");
        }
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setPlayer(p);
        playerInfo.setTeam(t);
        playerInfo.setJerseyNumber(jerseyNumber);
        try {
            playerInfoDao.createPlayerInfo(playerInfo);
        } catch (Exception e) {
            throw new DaoLayerException("can not assign player to team", e);
        }
    }

    public void assignNewPlayerToTeam(Player p, Team t, int jerseyNumber) {
        if(!playerMeetsAgeLimit(p,t)){
            throw new IllegalArgumentException("Player does not match age criteria of team");
        }
        if(!teamDao.isJerseyNumberUnique(t, jerseyNumber)){
            throw new IllegalArgumentException("Jersey number is not unique in the team");
        }
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setPlayer(p);
        playerInfo.setTeam(t);
        playerInfo.setJerseyNumber(jerseyNumber);
        p.setClub(t.getClub());
        try {
            playerInfoDao.createPlayerInfo(playerInfo);
        } catch (Exception e) {
            throw new DaoLayerException("can not assign player to team", e);
        }
    }

    public void changeJerseyNumber(Player p, Team t, int newjerseyNumber) {
        if(!teamDao.isJerseyNumberUnique(t,newjerseyNumber)){
            throw new IllegalArgumentException("Jersey number is not unique in the team");
        }
        try {
            playerInfoDao.changeJerseyNumber(t, p, newjerseyNumber);
        } catch (Exception e) {
            throw new DaoLayerException("can not change jarsey number", e);
        }
    }

    public void removePlayerFromTeam(Player p, Team t) {
        try {
            playerInfoDao.deletePlayerInfoByTeamAndPlayer(t, p);
        } catch (Exception e) {
            throw new DaoLayerException("can not remove player from team", e);
        }
    }

    public List<Player> findSuitablePlayersForTeam(Team team) {
        LocalDate today = LocalDate.now();
        int ageLimit = team.getCategory().getAgeLimit();
        LocalDate ageLimitDate = today.minusYears(ageLimit);
        System.out.println(ageLimitDate);
        try {
            return clubDao.getPlayersWithHigherDobThan(team.getClub(),ageLimitDate);
        } catch (Exception e) {
            throw new DaoLayerException("can not obtain find suitable players for team", e);
        }
    }
}
