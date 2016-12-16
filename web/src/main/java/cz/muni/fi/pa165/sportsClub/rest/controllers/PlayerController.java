package cz.muni.fi.pa165.sportsClub.rest.controllers;

import cz.muni.fi.pa165.sportsClub.facade.PlayerFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;



@RestController
@RequestMapping("/player")
public class PlayerController {

    @Inject
    private PlayerFacade playerFacade;

    @RequestMapping(value = "/{playerId}", method = RequestMethod.DELETE)
    public final ResponseEntity deleteTeam(@PathVariable long playerId){
        try {
            playerFacade.deletePlayer(playerId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Player was successfully deleted");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
