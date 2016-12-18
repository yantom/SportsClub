package cz.muni.fi.pa165.sportsClub.rest.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.muni.fi.pa165.sportsClub.dto.ManagerCreateDto;
import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.dto.TeamDto;
import cz.muni.fi.pa165.sportsClub.facade.ManagerFacade;


@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Inject
    private ManagerFacade managerFacade;

    @RequestMapping(path = "{managerId}/teams", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TeamDto> getTeams(@PathVariable long managerId){
		return managerFacade.getManagerById(managerId).getTeams();
    }

    @RequestMapping(path = "{managerId}/freePlayers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlayerDto> getFreePlayers(@PathVariable long managerId){
		ManagerDto managerDto = managerFacade.getManagerById(managerId);
		return managerFacade.getFreePlayers(managerDto.getId());
    }

	@RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ManagerDto create(@Valid @RequestBody ManagerCreateDto manager) {
		managerFacade.createManager(manager);
		return manager;
    }

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ManagerDto update(@Valid @RequestBody ManagerDto manager) {
		managerFacade.updateManager(manager);
		return manager;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) {
		managerFacade.deleteManager(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ManagerDto findById(@PathVariable("id") long id) {
		return managerFacade.getManagerById(id);
    }

    @RequestMapping(path = "/findall", method = RequestMethod.GET)
	public List<ManagerDto> findAll() {
		return managerFacade.getAllManagers();
    }
}
