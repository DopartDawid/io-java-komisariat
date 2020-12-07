package com.komisariat.BusinessObjects;

import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class User {

	private String login;
    private String passHash;

    @Enumerated(EnumType.STRING)
	private AccessLevel accessLevel;

    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
}