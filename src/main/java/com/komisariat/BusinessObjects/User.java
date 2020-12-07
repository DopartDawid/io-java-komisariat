package com.komisariat.BusinessObjects;


import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
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