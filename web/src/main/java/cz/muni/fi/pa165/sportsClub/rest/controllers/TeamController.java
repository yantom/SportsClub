package cz.muni.fi.pa165.sportsClub.rest.controllers;

import cz.muni.fi.pa165.sportsClub.dto.ClubDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerOfTeamDto;
import cz.muni.fi.pa165.sportsClub.facade.TeamFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;


@RestController
@RequestMapping("/team")
public class TeamController {

    @Inject
    private TeamFacade teamFacade;

    @RequestMapping(path = "{teamId}/players", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlayerOfTeamDto> getFreePlayers(@PathVariable long teamId){
        return teamFacade.getPlayersOfTeam(teamId);
    }

    @RequestMapping(value = "/{teamId}", method = RequestMethod.DELETE)
    public final ResponseEntity deleteTeam(@PathVariable long teamId){
        try {
            teamFacade.deleteTeam(teamId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Team was successfully deleted");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{teamId}/{playerId}", method = RequestMethod.DELETE)
    public final ResponseEntity removePlayerFromRoster(@PathVariable long teamId,@PathVariable long playerId){
        try {
            teamFacade.removePlayerFromTeam(teamId, playerId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Player was successfully removed from team roster");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
