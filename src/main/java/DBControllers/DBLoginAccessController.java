package DBControllers;

import BusinessObjects.*;

public class DBLoginAccessController implements IDBLoginAccessController {

	private DBLoginAccessController instance;

	private DBLoginAccessController() {
		// TODO - implement DBLoginAccessController.DBLoginAccessController
		throw new UnsupportedOperationException();
	}

	public DBLoginAccessController getInstance() {
		return this.instance;
	}

	/**
	 * 
	 * @param login
	 * @param passHash
	 */
	public User getUserFromCredentials(string login, string passHash) {
		// TODO - implement DBLoginAccessController.getUserFromCredentials
		throw new UnsupportedOperationException();
	}

}