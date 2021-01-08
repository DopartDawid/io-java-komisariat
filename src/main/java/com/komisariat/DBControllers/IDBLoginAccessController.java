package com.komisariat.DBControllers;

import com.komisariat.BusinessObjects.*;

public interface IDBLoginAccessController {

	static DBLoginAccessController getInstance() {
		return null;
	}

	/**
	 * 
	 * @param login
	 * @param passHash
	 */
	User getUserFromCredentials(String login, String passHash);
	Officer getOfficerFromUser(User user);
}