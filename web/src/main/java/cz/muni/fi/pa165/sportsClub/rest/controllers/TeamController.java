package cz.muni.fi.pa165.sportsClub.rest.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.muni.fi.pa165.sportsClub.dto.PlayerOfTeamDto;
import cz.muni.fi.pa165.sportsClub.facade.TeamFacade;


@RestController
@RequestMapping("/team")
public class TeamController {

    @Inject
    private TeamFacade teamFacade;

    @RequestMapping(path = "/{teamId}/players", method = RequestMethod.GET)
    public final List<PlayerOfTeamDto> getplayers(@PathVariable("teamId") long teamId){
        System.out.println(teamFacade.getPlayersOfTeam(teamId).size());
        return teamFacade.getPlayersOfTeam(teamId);
    }

	@RequestMapping(value = "/{teamId}", method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
	public final ResponseEntity<String> deleteTeam(@PathVariable("teamId") long teamId) {
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
}
