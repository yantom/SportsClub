package cz.muni.fi.pa165.sportsClub.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfo;
import cz.muni.fi.pa165.sportsClub.pojo.PlayerInfoId;

/**
 *
 * @author Andrej 410433
 */
@Repository
@Transactional
public class PlayerInfoDaoImpl implements PlayerInfoDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createPlayerInfo(PlayerInfo pi) {
        if(pi == null){
            throw new IllegalArgumentException("Argument can not be null");
        }
        em.persist(pi);
    }

    @Override
    public void deletePlayerInfo(PlayerInfo pi) {
        if(pi == null){
            throw new IllegalArgumentException("Argument can not be null");
        }
        em.remove(em.merge(pi));
    }

    @Override
    public void updatePlayerInfo(PlayerInfo pi) {
        if(pi == null){
            throw new IllegalArgumentException("Argument can not be null");
        }
        em.merge(pi);
    }

    @Override
    public PlayerInfo getPlayerInfoById(PlayerInfoId piId) {
        return em.find(PlayerInfo.class, piId);
    }
}
