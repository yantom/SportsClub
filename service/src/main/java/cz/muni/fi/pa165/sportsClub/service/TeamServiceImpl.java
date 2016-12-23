package cz.muni.fi.pa165.sportsClub.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.sportsClub.dao.ManagerDao;
import cz.muni.fi.pa165.sportsClub.dao.PlayerInfoDao;
import cz.muni.fi.pa165.sportsClub.dao.TeamDao;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.exception.DaoLayerException;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

@Service
public class TeamServiceImpl implements TeamService {
    @Inject
    private TeamDao teamDao;

    @Inject
	private ManagerDao managerDao;

    @Inject
    private PlayerInfoDao playerInfoDao;

	@Override
	public void createTeam(Team t) {
        try {
            teamDao.createTeam(t);
        } catch (Exception e) {
            throw new DaoLayerException("can not create team", e);
        }
    }

	@Override
	public void updateTeam(Team t) {
        try {
            teamDao.updateTeam(t);
        } catch (Exception e) {
            throw new DaoLayerException("can not update team", e);
        }
    }

	@Override
	public void deleteTeam(Team t) {
        try {
            teamDao.deleteTeam(t);
        } catch (Exception e) {
            throw new DaoLayerException("can not delete team", e);
        }
    }

	@Override
	public Team getTeamById(Long teamId) {
        try {
            return teamDao.getTeamById(teamId);
        } catch (Exception e) {
            throw new DaoLayerException("can not find team by id", e);
        }
    }

	@Override
	public Team getTeamOfClubByCategory(Category category, Manager m) {
        try {
			List<Team> teams = m.getTeams();
            for (Team team : teams) {
                if (team.getCategory().equals(category)) {
                    return team;
                }
            }
            return null;
        } catch (Exception e) {
			throw new DaoLayerException("can not find team of manager by category", e);
        }

    }

	@Override
	public List<Team> getAllTeamsOfClub(Manager m) {
        try {
			return m.getTeams();
        } catch (Exception e) {
			throw new DaoLayerException("can not obtain all teams of manager", e);
        }

    }

	@Override
	public List<PlayerInfo> getPlayerInfos(Long teamId) {
        try {
            Team team = teamDao.getTeamById(teamId);
            team.getPlayerInfos().size();
            return team.getPlayerInfos();
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

	@Override
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

	@Override
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
		p.setManager(t.getManager());
        try {
            playerInfoDao.createPlayerInfo(playerInfo);
        } catch (Exception e) {
            throw new DaoLayerException("can not assign player to team", e);
        }
    }

	@Override
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

	@Override
	public void removePlayerFromTeam(PlayerInfo pi) {
        try {
			playerInfoDao.deletePlayerInfo(pi);
        } catch (Exception e) {
            throw new DaoLayerException("can not remove player from team", e);
        }
    }

    @Override
	public List<Player> findSuitablePlayersForTeam(Team team) {
        LocalDate today = LocalDate.now();
        int ageLimit = team.getCategory().getAgeLimit();
        LocalDate ageLimitDate = today.minusYears(ageLimit);
        try {
			return managerDao.getPlayersWithHigherDobThan(team.getManager(), ageLimitDate);
        } catch (Exception e) {
            throw new DaoLayerException("can not obtain find suitable players for team", e);
        }
    }

    @Override
    public boolean isJerseyNumberUnique(Team team, int jerseyNumber) {
        try {
            return teamDao.isJerseyNumberUnique(team, jerseyNumber);
        } catch (Exception e) {
            throw new DaoLayerException("can not tell if team contains this jerseyNumber", e);
        }
    }
}
