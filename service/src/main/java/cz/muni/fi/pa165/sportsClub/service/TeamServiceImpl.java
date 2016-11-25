package cz.muni.fi.pa165.sportsClub.service;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.sportsClub.exception.DaoLayerException;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.sportsClub.dao.PlayerInfoDao;
import cz.muni.fi.pa165.sportsClub.dao.TeamDao;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Manager;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

@Service
public class TeamServiceImpl implements TeamService {
	@Inject
	private TeamDao teamDao;
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
			Manager manager = c.getManager();
			List<Team> teams = manager.getTeams();
			for(Team team: teams){
				if (team.getCategory().equals(category)){
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
			Manager manager = c.getManager();
			return manager.getTeams();
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

	public void assignPlayerToTeam(Player p, Team t, int jerseyNumber) {
            PlayerInfo playerInfo = new PlayerInfo();
            playerInfo.setPlayer(p);
            playerInfo.setPlayerId(p.getId());
            playerInfo.setTeam(t);
            playerInfo.setTeamId(t.getId());
            playerInfo.setJerseyNumber(jerseyNumber);
		try {
			playerInfoDao.createPlayerInfo(playerInfo);
		} catch (Exception e) {
			throw new DaoLayerException("can not assign player to team", e);
		}
		t.addPlayerInfo(playerInfo);
            p.addPlayerInfo(playerInfo);
	}

	public void changeJerseyNumber(Player p, Team t, int jerseyNumber) {
		List<PlayerInfo> playerInfos = t.getPlayerInfos();
                for(PlayerInfo info: playerInfos){
                    if(info.getPlayerId() == p.getId()){
                        info.setJerseyNumber(jerseyNumber);
                    }
                }

	}

	public void removePlayerFromTeam(Player p, Team t) {

		List<PlayerInfo> playerInfos = p.getPlayerInfos();
                for(PlayerInfo info: playerInfos){
                    if(info.getTeamId() == t.getId()){
						try {
							playerInfoDao.deletePlayerInfo(info);
						} catch (Exception e) {
							throw new DaoLayerException("can not remove player from team", e);
						}
                    }
		}
	}
}
