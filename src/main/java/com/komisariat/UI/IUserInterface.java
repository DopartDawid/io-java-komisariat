package com.komisariat.UI;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface IUserInterface {

    void startUI();
    Collection<String> getCredentials();
    void showErrorMessage(String message);
    void showAdminUI();
    void showOfficerUI();
    void showCommissionerUI();
    //
    Map<String, String> getKitInfo(Set<String> keys);
    Collection<Map<String, String>> getToolsInfo(Set<String> keys);
    Integer getRemoveKitID();
    Map<String, String> getEditedKitInfo();
    //
    Map<String, String> getNewOfficerInfo(Set<String> keys);

}
