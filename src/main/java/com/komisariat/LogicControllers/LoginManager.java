package com.komisariat.LogicControllers;

import com.komisariat.BusinessObjects.*;
import com.komisariat.DBControllers.DBLoginAccessController;
import com.komisariat.DBControllers.IDBLoginAccessController;

public class LoginManager {

	public OfficerManager om;
	public CommissionerManager cm;
	public AdminManager am;

	private User loggedUser;
	private IDBLoginAccessController dblac;

	public LoginManager() {
		om = null;
		cm = null;
		am = null;
		loggedUser = null;
		dblac = DBLoginAccessController.getInstance();
	}
	/**
	 * 
	 * @param login
	 * @param password
	 */
	public void signInNewUser(String login, String password) {
		loggedUser = dblac.getUserFromCredentials(login, password);

		if(loggedUser != null) {
			switch(loggedUser.getAccessLevel()) {
				case Admin: am = new AdminManager(); break;
				case Officer: om = new OfficerManager(dblac.getOfficerFromUser(loggedUser)); break;
				case Commissioner: cm = new CommissionerManager(dblac.getOfficerFromUser(loggedUser)); break;
			}
		}
	}

	public void signOut() {
		// TODO - implement LoginManager.signOut
		throw new UnsupportedOperationException();
	}

}