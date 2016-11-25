package cz.muni.fi.pa165.sportsClub.pojo;

import java.io.Serializable;

/**
 * @author Simon Sudora 461460
 */
public class PlayerInfoId implements Serializable {

    private static final long serialVersionUID = 1L;

    private long playerId;

    private long teamId;

    public long getPlayerId() {
        return playerId;
    }

    public long getTeamId() {
        return teamId;
    }

    public PlayerInfoId() {
    }

    public PlayerInfoId(long playerId, long teamId) {
        this.playerId = playerId;
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerInfoId)) {
            return false;
        }

        PlayerInfoId that = (PlayerInfoId) o;

        if (getPlayerId() != that.getPlayerId()) {
            return false;
        }
        return getTeamId() == that.getTeamId();

    }

    @Override
    public int hashCode() {
        int result = (int) (getPlayerId() ^ (getPlayerId() >>> 32));
        result = 31 * result + (int) (getTeamId() ^ (getTeamId() >>> 32));
        return result;
    }

}
