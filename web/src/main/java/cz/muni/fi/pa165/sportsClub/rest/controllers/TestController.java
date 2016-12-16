package cz.muni.fi.pa165.sportsClub.rest.controllers;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.muni.fi.pa165.sportsClub.dto.ClubDto;
import cz.muni.fi.pa165.sportsClub.facade.ClubFacade;

@RestController
@RequestMapping("/test")
public class TestController {
	@Inject
	ClubFacade clubFacade;

	@RequestMapping(path = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final ClubDto testEndpoint(@PathVariable("id") long id) {
		return clubFacade.getClubById(id);
	}
}
