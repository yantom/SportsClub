package cz.muni.fi.pa165.sportsClub.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.sportsClub.dao.PlayerInfoDao;
import cz.muni.fi.pa165.sportsClub.dao.TeamDao;
import cz.muni.fi.pa165.sportsClub.enums.Category;
import cz.muni.fi.pa165.sportsClub.pojo.Club;
import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;

@Service
public class TeamServiceImpl implements TeamService {
	@Inject
	TeamDao teamDao;
	@Inject
	PlayerInfoDao playerInfoDao;

	public void createTeam(Team t) {
		teamDao.createTeam(t);

	}

	public void updateTeam(Team t) {
		teamDao.updateTeam(t);

	}

	public void deleteTeam(Team t) {
		teamDao.deleteTeam(t);

	}

	public Team getTeamById(Long teamId) {
		return teamDao.getTeamById(teamId);
	}

	public Team getTeamOfClubByCategory(Category category) {
		return null;
	}

	public List<Team> getAllTeamsOfClub(Club c) {
                return null;
	}

	public List<PlayerInfo> getPlayerInfos(Team t) {
                return t.getPlayerInfos();
	}

	public void assignPlayerToTeam(Player p, Team t, int jerseyNumber) {
            PlayerInfo playerInfo = new PlayerInfo();
            playerInfo.setPlayer(p);
            playerInfo.setPlayerId(p.getId());
            playerInfo.setTeam(t);
            playerInfo.setTeamId(t.getId());
            playerInfoDao.createPlayerInfo(playerInfo);
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
                        t.removePlayerInfo(info);
                    }
                }
                
	}
}
