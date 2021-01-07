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
    Map<String, String> getKitInfo();
    Collection<Map<String, String>> getToolsInfo();
    Integer getRemoveKitID();
    Map<String, String> getEditedKitInfo();
    Collection<Map<String, String>> getEditedToolsInfo(Integer kitID);
    //
    Map<String, String> getNewOfficerInfo();
    Map<String, String> getEditedOfficerInfo();
    Integer getRemoveOfficerID();
    //
    void showReports();
    void showActiveOfficers();

}
