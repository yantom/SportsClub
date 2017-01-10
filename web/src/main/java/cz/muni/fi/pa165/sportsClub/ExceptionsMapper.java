package cz.muni.fi.pa165.sportsClub;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cz.muni.fi.pa165.sportsClub.exception.SportClubsRuntimeException;
import cz.muni.fi.pa165.sportsClub.exception.TokenValidationException;

@ControllerAdvice
class ExceptionsMapper {
	
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SportClubsRuntimeException.class)
    public void unexpectedErrorHandle() {
    }
    
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenValidationException.class)
    public void tokenValidationHandle() {
    }
    
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolationHandle() {
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void noResultHandle() {
    }
}
