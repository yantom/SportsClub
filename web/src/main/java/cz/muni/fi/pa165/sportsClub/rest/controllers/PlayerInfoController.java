package cz.muni.fi.pa165.sportsClub.rest.controllers;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.muni.fi.pa165.sportsClub.facade.TeamFacade;

@RestController
@RequestMapping("/playerInfo")
public class PlayerInfoController {
	@Inject
	private TeamFacade teamFacade;

	@RequestMapping(value = "/{playerInfoId}", method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
	public final ResponseEntity<String> removePlayerFromRoster(@PathVariable("playerInfoId") long playerInfoId) {
		try {
			teamFacade.removePlayerFromTeam(playerInfoId);
			return ResponseEntity.status(HttpStatus.OK).body("Player was successfully removed from team roster");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
