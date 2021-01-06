package com.komisariat.UI;

import com.komisariat.MainControllers.App;

import java.util.*;

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

    @Override
    public Map<String, String> getKitInfo() {
        System.out.println("#########------- DODAWANIE NOWEGO EKWIPUNKU -------#########");
        Map<String, String> kitInfo = new HashMap<>();
        System.out.print("Podaj nazwe: ");
        String name = scanner.nextLine();
        System.out.print("Podaj kategorie: ");
        String category = scanner.nextLine();
        System.out.print("---- DOCELOWY KOMISARIAT ----");
        String hqID = showHeadquarterChoice().get("id");

        kitInfo.put("name", name);
        kitInfo.put("category", category);
        kitInfo.put("hqID", hqID);
        return kitInfo;
    }

    @Override
    public Collection<Map<String, String>> getToolsInfo() {
        System.out.println("#########------- dodaj narzedzia do ekwipunku -------#########");
        boolean isDone = false;
        List<Map<String, String>> toolInfos = new LinkedList<>();
        while(!isDone) {
            Map<String, String> toolInfo = new HashMap<>();
            System.out.print("Podaj model: ");
            toolInfo.put("model", scanner.nextLine());
            System.out.print("Podaj producenta: ");
            toolInfo.put("manufacturer", scanner.nextLine());
            System.out.print("Podaj kategorie: ");
            toolInfo.put("category", scanner.nextLine());
            toolInfos.add(toolInfo);
            System.out.print("\nZakonczyc dodawanie narzedzi? (y/n): ");
            String choice = scanner.nextLine();
            if(choice.equals("y")) isDone = true;
        }
        return toolInfos;
    }

    @Override
    public Integer getRemoveKitID() {
        System.out.println("#########------- USUWANIE EKWIPUNKU Z UZYCIA -------#########\n");
        Map<String, String> temp = showKitChoice();
        if(temp == null) {
            System.out.println("\n\n ####### ANULOWANO USUWANIE EKWIPUNKU ####### \n\n");
            return -1;
        }
        return Integer.parseInt(temp.get("id"));
    }

    @Override
    public Map<String, String> getEditedKitInfo() {
        System.out.println("#########------- EDYTOWANIE ISTNIEJACEGO EKWIPUNKU -------#########\n");
        Map<String, String> temp = showKitChoice();
        if(temp == null) {
            System.out.println("\n\n ####### ANULOWANO EDYCJE EKWIPUNKU ####### \n\n");
            return null;
        }
        System.out.print("Nazwa (" + temp.get("name")+"): ");
        String name = scanner.nextLine();
        if(!name.equals(""))
            temp.replace("name", name);

        System.out.print("Kategoria (" + temp.get("category")+"): ");
        String category = scanner.nextLine();
        if(!name.equals(""))
            temp.replace("category", category);

        return temp;
    }

    @Override
    public Collection<Map<String, String>> getEditedToolsInfo(Integer kitID) {
        Collection<Map<String, String>> toolInfos = app.getKitTools(kitID);
        while(true) {
            System.out.print("Czy chcesz edytować narzędzia, należące do tego ekwipunku? (y/n)");
            String choice = scanner.nextLine();
            if(choice.equals("y")) break;
            else if(choice.equals("n")) return toolInfos;
        }

        for (Map<String, String> toolInfo: toolInfos
             ) {
            System.out.println("\n####\n");
            System.out.print("Model (" + toolInfo.get("model") + "): ");
            String model = scanner.nextLine();
            if(!model.equals(""))
                toolInfo.replace("model", model);
            System.out.print("Producent (" + toolInfo.get("manufacturer") + "): ");
            String manufacturer = scanner.nextLine();
            if(!manufacturer.equals(""))
                toolInfo.replace("manufacturer", manufacturer);
            System.out.print("Kategoria (" + toolInfo.get("category") + "): ");
            String category = scanner.nextLine();
            if(!category.equals(""))
                toolInfo.replace("category", category);
        }
        return toolInfos;
    }

    @Override
    public Map<String, String> getNewOfficerInfo(Set<String> keys) {
        return null;
    }

    private Map<String, String> showKitChoice() {
        while(true) {
            ArrayList<Map<String, String>> kitInfos = new ArrayList<>(app.getKits());
            System.out.println("\tNUMER\t|\tNAZWA\t|\tKATEGORIA\t|");
            for (Map<String, String> kitInfo: kitInfos
            ) {
                System.out.println("\t" + (kitInfos.indexOf(kitInfo)+1) + "\t|\t" + kitInfo.get("name") + "\t|\t" + kitInfo.get("category") + "\t|");
            }
            System.out.print("Wybierz numer, aby wyświetlić szczegóły (0 aby wyjść): ");
            boolean isCorrect = false;
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 0)
                return null;
            while(!(choice <= kitInfos.size() && choice > 0)) {
                System.out.print("Nieprawidłowy numer, podaj ponownie: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            Map<String, String> chosenKit = kitInfos.get(choice-1);
            System.out.println("\n\nSZCZEGÓŁY: "+ chosenKit.get("name"));
            ArrayList<Map<String, String>> toolInfos = new ArrayList<>(app.getKitTools(Integer.parseInt(chosenKit.get("id"))));
            System.out.println("\tNUMER\t|\tMODEL\t|\tPRODUCENT\t|\tKATEGORIA\t");
            for (Map<String, String> toolInfo: toolInfos
            ) {
                System.out.println("\t" + (toolInfos.indexOf(toolInfo)+1) + "\t|\t" + toolInfo.get("model") + "\t|\t" + toolInfo.get("manufacturer") + "\t|\t" + toolInfo.get("category") + "\t|");
            }
            System.out.print("\nPotwierdasz swoj wybor? (y/n): ");
            if(scanner.nextLine().equals("y")) return chosenKit;
        }
    }

    private Map<String, String> showHeadquarterChoice() {
        while(true) {
            ArrayList<Map<String, String>> hqsInfos = new ArrayList<>(app.getHeadquarters());
            System.out.println("\tNUMER\t|\tADRES KOMISARIATU\t|");
            for (Map<String, String> hqInfo: hqsInfos
            ) {
                System.out.println("\t" + (hqsInfos.indexOf(hqInfo)+1) + "\t|\t" + hqInfo.get("address") + "\t|");
            }
            System.out.print("Wybierz numer komisariatu: ");
            Integer choice = scanner.nextInt();
            scanner.nextLine();
            while(!(choice <= hqsInfos.size() && choice > 0)) {
                System.out.print("Nieprawidłowy numer, podaj ponownie: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            return hqsInfos.get(choice-1);
        }
    }
}
