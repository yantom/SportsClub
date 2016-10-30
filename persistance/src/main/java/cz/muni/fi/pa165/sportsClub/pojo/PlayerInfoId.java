package cz.muni.fi.pa165.sportsClub.pojo;

/**
 * @author Simon Sudora 461460
 */
public class PlayerInfoId {

    private long playerId;

    private long teamId;

    public long getPlayerId() { return playerId; }

    public void setPlayerId(long playerId) { this.playerId = playerId; }

    public long getTeamId() { return teamId; }

    public void setTeamId(long teamId) { this.teamId = teamId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerInfoId)) return false;

        PlayerInfoId that = (PlayerInfoId) o;

        if (getPlayerId() != that.getPlayerId()) return false;
        return getTeamId() == that.getTeamId();

    }

    @Override
    public int hashCode() {
        int result = (int) (getPlayerId() ^ (getPlayerId() >>> 32));
        result = 31 * result + (int) (getTeamId() ^ (getTeamId() >>> 32));
        return result;
    }

}
