package com.komisariat.UI;

import java.util.Collection;

public interface IUserInterface {

    void startUI();
    Collection<String> getCredentials();
    void showErrorMessage(String message);
    void showAdminUI();
    void showOfficerUI();
    void showCommissionerUI();
}
