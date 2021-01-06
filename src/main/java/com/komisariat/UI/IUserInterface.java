package com.komisariat.UI;

import java.util.Collection;
import java.util.Map;

public interface IUserInterface {

    void startUI();
    Collection<String> getCredentials();
    void showErrorMessage(String message);
    void showAdminUI();
    void showOfficerUI();
    void showCommissionerUI();
    //
    Map<String, String> getKitInfo();
    Collection<Map<String, String>> getToolsInfo();
    Integer getRemoveKitID();
    Map<String, String> getEditedKitInfo();
}
