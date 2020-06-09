package search;

import java.util.ArrayList;
import java.util.Scanner;

class SearchIndex {
    private int indexSize;
    public ArrayList<String> searchIndex;

    SearchIndex(int indexSize) {
        this.indexSize = indexSize;
        this.searchIndex = new ArrayList<>();
    }

    public int getIndexSize() {
        return indexSize;
    }

    public void addStringToIndex (String input) {
        searchIndex.add(input);
    }

}

public class Main {
    private Scanner userInputScanner;
    private boolean isUpAndRunning;

    Main() {
        this.userInputScanner = new Scanner(System.in);
        this.isUpAndRunning = true;
    }

    public int askForIndexSize() {
        System.out.println("Enter the number of people:");
        return Integer.parseInt(userInputScanner.nextLine());
    }

    public String askQuery() {
        System.out.println("Enter data to search people:");
        String searchQuery = userInputScanner.nextLine();
        return searchQuery;
    }

    public void runMenuAction() {
        final String menu = "=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit";

        System.out.println(menu);

        String action = userInputScanner.nextLine();

        switch (action) {
            case "1":
                // do something
                break;
            case "2":
                // do something
                break;
            case "0":
                isUpAndRunning = false;
                break;
        }
    }


        public static void main(String[] args) {

        Main app = new Main();

        SearchIndex searchIndex = new SearchIndex(app.askForIndexSize());

        System.out.println("Enter all people:");
        for (int i = 0; i < searchIndex.getIndexSize(); i++) {
            String userInput = app.userInputScanner.nextLine();
            searchIndex.addStringToIndex(userInput);
        }

        while (app.isUpAndRunning) {

            app.runMenuAction();


            System.out.println("Enter the number of search queries:");
            int numberOfQueries = Integer.parseInt(app.userInputScanner.nextLine());

            for (int i = 0; i < numberOfQueries; i++) {

                String query = app.askQuery();

                ArrayList<String> result = new ArrayList<>();
                for (String s : searchIndex.searchIndex) {

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

            app.isUpAndRunning = false;

        }
    }
}
