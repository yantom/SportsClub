package cz.muni.fi.pa165.sportsClub.dao;

import cz.muni.fi.pa165.sportsClub.pojo.Player;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfoId;
import cz.muni.fi.pa165.sportsClub.pojo.Team;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andrej 410433
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
    public PlayerInfo getPlayerInfoById(PlayerInfoId piId) {
        return em.find(PlayerInfo.class, piId);   
    }
}
