package com.komisariat.LogicControllers;

import com.komisariat.BusinessObjects.*;
import com.komisariat.DBControllers.DBLoginAccessController;
import com.komisariat.DBControllers.IDBLoginAccessController;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		loggedUser = dblac.getUserFromCredentials(login, hashPass(password));

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

	public String hashPass(String password){
		String passHash;
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(password.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			passHash = no.toString(16);
			while(passHash.length() < 32){
				passHash = "0"+ passHash;
			}
		}catch (NoSuchAlgorithmException e){
			throw new RuntimeException(e);
		}
		return passHash;
	}
}