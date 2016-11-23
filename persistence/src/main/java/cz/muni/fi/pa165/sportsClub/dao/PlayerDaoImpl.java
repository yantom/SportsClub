package cz.muni.fi.pa165.sportsClub.dao;

import cz.muni.fi.pa165.sportsClub.pojo.Player;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is base implementation of {@link PlayerDao}
 *
 * @author Simon Sudora 461460
 */

@Repository
@Transactional
public class PlayerDaoImpl implements PlayerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createPlayer(Player player) {em.persist(player); }

    @Override
    public void updatePlayer(Player player) { em.merge(player); }

    @Override
    public void deletePlayer(Player player) {em.remove(em.merge(player)); }

    @Override
    public Player getPlayerById(Long id)  {
        return em.find(Player.class, id);
    }

    @Override
    public List<Player> getAllPlayers() {
        return Collections.unmodifiableList(
                em.createQuery("SELECT p FROM Player p", Player.class).getResultList());
    }
}
