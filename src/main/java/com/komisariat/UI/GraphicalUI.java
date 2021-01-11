package com.komisariat.UI;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

import com.komisariat.MainControllers.App;

public class GraphicalUI implements IUserInterface {
    private App app;

    public GraphicalUI(App parent) {
        app = parent;
    }

    @Override
    public void startUI() {

    }

    @Override
    public Collection<String> getCredentials() {
        return null;
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showAdminUI() {

    }

    @Override
    public void showOfficerUI() {

    }

    @Override
    public void showCommissionerUI() {

    }

    @Override
    public Map<String, String> getKitInfo() {
        return null;
    }

    @Override
    public Collection<Map<String, String>> getToolsInfo() {
        return null;
    }

    @Override
    public Integer getRemoveKitID() {
        return null;
    }

    @Override
    public Map<String, String> getEditedKitInfo() {
        return null;
    }

    @Override
    public Collection<Map<String, String>> getEditedToolsInfo(Integer kitID) {
        return null;
    }

    @Override
    public Map<String, String> getNewOfficerInfo() {
        return null;
    }

    @Override
    public Map<String, String> getEditedOfficerInfo() {
        return null;
    }

    @Override
    public Integer getRemoveOfficerID() {
        return null;
    }

    @Override
    public void showReports() {

    }

    @Override
    public void showActiveOfficers() {

    }

    @Override
    public void showTimesheet() {

    }

    @Override
    public void showOfficersInfo() {

    }

    @Override
    public Map<String, String> getNewShiftInfo() {
        return null;
    }

    @Override
    public Map<String, String> getEndShiftInfo() {
        return null;
    }

}
