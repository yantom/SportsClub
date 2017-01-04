package cz.muni.fi.pa165.sportsClub.rest.controllers;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.exception.TokenValidationException;
import cz.muni.fi.pa165.sportsClub.facade.TeamFacade;
import cz.muni.fi.pa165.sportsClub.service.AuthUtils;

@RestController
@RequestMapping("/playerInfo")
public class PlayerInfoController {
	@Inject
	private TeamFacade teamFacade;

	private static final String[] AUTHORIZED_ROLES = new String[] { "admin", "manager" };

	@RequestMapping(value = "/{playerInfoId}", method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
	public final ResponseEntity removePlayerFromRoster(@PathVariable("playerInfoId") long playerInfoId,
			HttpServletRequest hsr) throws TokenValidationException {
		String token = (hsr.getHeader("Authorization")).split(" ")[1];
		AuthUtils.authorizeRestCall(token, AUTHORIZED_ROLES);
		try {
			teamFacade.removePlayerFromTeam(playerInfoId);
			return ResponseEntity.status(HttpStatus.OK).body("Player was successfully removed from team roster");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{teamId}/{playerId}/{jerseyNumber}", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	public final ResponseEntity addPlayerOnRoster(@PathVariable("teamId") long teamId,
			@PathVariable("playerId") long playerId, @PathVariable("jerseyNumber") int jerseyNumber,
			HttpServletRequest hsr) throws TokenValidationException {
		String token = (hsr.getHeader("Authorization")).split(" ")[1];
		AuthUtils.authorizeRestCall(token, AUTHORIZED_ROLES);
		try {
			teamFacade.assignExistingPlayerToTeam(playerId, teamId, jerseyNumber);
			return ResponseEntity.status(HttpStatus.CREATED).body("Player was successfully added on team roster");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{teamId}/{jerseyNumber}", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	public final ResponseEntity addNewPlayerOnRoster(@PathVariable("teamId") long teamId,
			@PathVariable("jerseyNumber") int jerseyNumber, @Valid @RequestBody PlayerDto player,
			HttpServletRequest hsr) throws TokenValidationException {
		String token = (hsr.getHeader("Authorization")).split(" ")[1];
		AuthUtils.authorizeRestCall(token, AUTHORIZED_ROLES);
		try {
			teamFacade.assignNewPlayerToTeam(player, teamId, jerseyNumber);
			return ResponseEntity.status(HttpStatus.CREATED).body("New player was successfully added on team roster");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
