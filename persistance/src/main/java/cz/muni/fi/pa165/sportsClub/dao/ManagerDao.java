package cz.muni.fi.pa165.sportsClub.dao;

import cz.muni.fi.pa165.sportsClub.pojo.Manager;

/**
 * @author Jan Tomasek
 * 
 * Interface for database operations over Manager entity.
 */
public interface ManagerDao {

	/**
	 * Stores manager entity into database.
	 * 
	 * @param manager
	 */
	void createManager(Manager manager);

	/**
	 * Retrieves manager object by its unique id. Uses lazy loading so "team"
	 * set is not populated.
	 * 
	 * @param id
	 * @return manager object with specified id if exists, null otherwise
	 */
	Manager getManagerById(Long id);

	/**
	 * Retrieves manager object by its unique email. Uses lazy loading so "team"
	 * set is not populated.
	 * 
	 * @param email
	 * @return manager object with specified email if exists, null otherwise
	 */
	Manager getManagerByEmail(String email);

	/**
	 * Updates manager entity in database.
	 * 
	 * @param manager
	 */
	void updateManager(Manager manager);

	/**
	 * Removes manager entity from database.
	 * 
	 * @param manager
	 */
	void deleteManager(Manager manager);
}
