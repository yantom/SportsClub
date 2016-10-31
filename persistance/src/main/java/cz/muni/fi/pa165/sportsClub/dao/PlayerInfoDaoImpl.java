/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsClub.dao;

import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.Team;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bono
 */
public class PlayerInfoDaoImpl implements PlayerInfoDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void createPlayerInfo(PlayerInfo pi) {
        em.persist(pi);
    }

    @Override
    public void deletePlayerInfo(PlayerInfo pi) {
        em.remove(pi);
    }

    @Override
    public void updatePlayerInfo(PlayerInfo pi) {
        em.merge(pi);
    }

    @Override
    public PlayerInfo getPlayerInfoByTeamId(Long teamId) {
        return em.find(PlayerInfo.class, teamId);
    }
    
    @Override
    public PlayerInfo getPlayerInfoByPlayerId(Long playerId) {
        return em.find(PlayerInfo.class, playerId);
    }
    
}
