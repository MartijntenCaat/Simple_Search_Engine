package search;

import java.util.ArrayList;
import java.util.Scanner;

class SearchIndex {

}

public class Main {
    private final Scanner scanner;
    private boolean isUpAndRunning;
    private int indexSize;
    public ArrayList<String> dataStore;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.dataStore = new ArrayList<>();
        this.isUpAndRunning = true;
    }

    public int getIndexSize() {
        return indexSize;
    }

    private void setIndexSize(int indexSize) {
        this.indexSize = indexSize;
    }

    public void addStringToIndex(String input) {
        dataStore.add(input);
    }

    public ArrayList<String> getDataStore() {
        return dataStore;
    }

    public int askForIndexSize() {
        System.out.println("Enter the number of people:");
        return Integer.parseInt(scanner.nextLine());
    }

    public String askQuery() {
        System.out.println("Enter data to search people:");
        String searchQuery = scanner.nextLine();
        return searchQuery;
    }

    public void runMenuAction() {
        final String menu = "=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit";

        System.out.println(menu);

        String action = scanner.nextLine();

        switch (action) {
            case "1": // find a person
                findAPerson();
                break;
            case "2":
                // do something
                break;
            case "0":
                isUpAndRunning = false;
                break;
            default:
                System.out.println("Incorrect option! Try again.");
                break;
        }
    }

    public void findAPerson() {
        System.out.println("Enter a name or email to search all suitable people.");
        String query = askQuery();

        ArrayList<String> result = new ArrayList<>();
        for (String s : dataStore) {

            if (s.contains(query.toLowerCase())) {
                result.add(s);
            }
        }

        if (!result.isEmpty()) {
            System.out.println("Found people:");
            for (String s : result) {
                System.out.println(s);
            }
        } else {
            System.out.println("No matching people found.");
        }

    }


    public static void main(String[] args) {
        Main app = new Main();
        int indexSize = Integer.parseInt(app.scanner.nextLine());
        app.setIndexSize(indexSize);

        System.out.println("Enter all people:");
        for (int i = 0; i < app.getIndexSize(); i++) {
            String userInput = app.scanner.nextLine();
            app.addStringToIndex(userInput);
        }

        while (app.isUpAndRunning) {

            app.runMenuAction();

            System.out.println("Enter the number of search queries:");
            int numberOfQueries = Integer.parseInt(app.scanner.nextLine());

            for (int i = 0; i < numberOfQueries; i++) {
                System.out.println("fix this");
            }

            app.isUpAndRunning = false;

        }
    }
}
