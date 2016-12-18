package cz.muni.fi.pa165.sportsClub.rest.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.muni.fi.pa165.sportsClub.dto.ManagerDto;
import cz.muni.fi.pa165.sportsClub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsClub.dto.TeamDto;
import cz.muni.fi.pa165.sportsClub.facade.ManagerFacade;


@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Inject
    private ManagerFacade managerFacade;
    @RequestMapping(path = "/{managerId}/teams", method = RequestMethod.GET)
    public final List<TeamDto> getTeamsOfManager(@PathVariable("managerId") long managerId){
        return managerFacade.getTeamsOfManager(managerId);
    }

    @RequestMapping(path = "/{managerId}/freePlayers", method = RequestMethod.GET)
    public final List<PlayerDto> getFreePlayers(@PathVariable("managerId") long managerId){
		ManagerDto managerDto = managerFacade.getManagerById(managerId);
		return managerFacade.getFreePlayers(managerDto.getId());
    }

	@RequestMapping(method = RequestMethod.POST)
	public ManagerDto create(@Valid @RequestBody ManagerDto manager) {
		managerFacade.createManager(manager);
		return manager;
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
	public void update(@Valid @RequestBody ManagerDto manager) {
		managerFacade.updateManager(manager);
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
