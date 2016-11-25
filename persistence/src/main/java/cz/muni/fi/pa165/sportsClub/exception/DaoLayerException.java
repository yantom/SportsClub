package cz.muni.fi.pa165.sportsClub.exception;

import org.springframework.dao.DataAccessException;

/**
 * @author Simon Sudora 461460
 * */

public class DaoLayerException extends DataAccessException {

    public DaoLayerException(String msg) {
        super(msg);
    }

    public DaoLayerException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
