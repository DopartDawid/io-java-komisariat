package com.komisariat.UI;

import com.komisariat.MainControllers.App;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

public class TextUI implements IUserInterface {

    private Scanner scanner;
    private App app;

    public TextUI(App parent) {
        scanner = new Scanner(System.in);
        app = parent;
    }

    @Override
    public void startUI() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isDone = false;

        while(!isDone) {
            System.out.println("\n\n##############------- SYSTEM OBSLUGI KOMISARIATOW POLICJI -------##############");
            System.out.println("1. Zaloguj sie");
            System.out.println("2. Wyjdz");
            System.out.print("\nWybierz opcje: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch(choice) {
                    case 1: app.signIn(); break;
                    case 2: isDone = true; break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public Collection<String> getCredentials() {
        Collection<String> credentials = new LinkedList<>();
        System.out.print("\nEnter login: ");
        credentials.add(scanner.nextLine());
        System.out.print("Enter password: ");
        credentials.add(scanner.nextLine());

        return credentials;
    }

    @Override
    public void showErrorMessage(String message) {
        System.out.println("\n############ ! ! ! ! ! ############\n");
        System.out.println(message);
        System.out.println("\n############ ! ! ! ! ! ############\n");
    }

    @Override
    public void showAdminUI() {

        boolean isDone = false;
        while(!isDone) {
            System.out.println("\n\t\tPANEL ADMINISTRACYJNY");
            System.out.println("----------- MENU -----------");
            System.out.println("# SPRZET #");
            System.out.println("1. Dodaj nowy ekwipunek");
            System.out.println("2. Edytuj ekwipunek");
            System.out.println("3. Usun ekwipunek");
            System.out.println("# FUNKCJONARIUSZE #");
            System.out.println("4. Dodaj nowego funkcjonariusza");
            System.out.println("5. Edytuj funkcjonariusza");
            System.out.println("6. Usun funkcjonariusza");
            System.out.println("# INNE #");
            System.out.println("0. Wyjdz z programu");
            System.out.print("\nWybierz opcje: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1: app.addNewKit(); break;
                case 2: app.editKit(); break;
                case 3: app.removeKit(); break;
                case 4: app.addNewOfficer(); break;
                case 5: app.editOfficer(); break;
                case 6: app.removeOfficer(); break;
                case 0: app.signOut(); isDone = true; break;

                default:
                    System.out.println("Niepoprawna opcja!"); break;
            }

        }
    }

    @Override
    public void showOfficerUI() {
        boolean isDone = false;
        while(!isDone) {
            System.out.println("\n\t\tPANEL FUNKCJONARIUSZA");
            System.out.println("----------- MENU -----------");
            System.out.println("# SLUZBA #");
            System.out.println("1. Rozpocznij sluzbe");
            System.out.println("2. Zakoncz sluzbe");
            System.out.println("# INNE #");
            System.out.println("0. Wyjdz z programu");
            System.out.print("\nWybierz opcje: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1: app.startShift(); break;
                case 2: app.finishShift(); break;
                case 0: app.signOut(); isDone = true; break;

                default:
                    System.out.println("Niepoprawna opcja!"); break;
            }

        }
    }

    @Override
    public void showCommissionerUI() {
        boolean isDone = false;
        while(!isDone) {
            System.out.println("\n\t\tPANEL KOMENDANTA");
            System.out.println("----------- MENU -----------");
            System.out.println("# SLUZBA #");
            System.out.println("1. Rozpocznij sluzbe");
            System.out.println("2. Zakoncz sluzbe");
            System.out.println("# FUNKCJONARIUSZE #");
            System.out.println("3. Wyswietl dostepne raporty");
            System.out.println("4. Wyswietl informacje o funkcjonariuszach");
            System.out.println("5. Wyswietl ewidencje czasu pracy");
            System.out.println("6. Wyswiet aktywnych funkcjonariuszy");
            System.out.println("# INNE #");
            System.out.println("0. Wyjdz z programu");
            System.out.print("\nWybierz opcje: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1: app.startShift(); break;
                case 2: app.finishShift(); break;
                case 3: app.viewReports(); break;
                case 4: app.viewOfficerInfos(); break;
                case 5: app.viewTimeSheet(); break;
                case 6: app.viewActiveOfficers(); break;
                case 0: app.signOut(); isDone = true; break;

                default:
                    System.out.println("Niepoprawna opcja!"); break;
            }

        }
    }
}
