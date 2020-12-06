package com.komisariat.DBControllers;

import com.komisariat.BusinessObjects.*;

public interface IDBLoginAccessController {

	DBLoginAccessController2 getInstance();

	/**
	 * 
	 * @param login
	 * @param passHash
	 */
	User getUserFromCredentials(String login, String passHash);

}