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


    public static void main(String[] args) {

        Main app = new Main();

        SearchIndex searchIndex = new SearchIndex(app.askForIndexSize());

        while (app.isUpAndRunning) {


            System.out.println("Enter all people:");
            for (int i = 0; i < searchIndex.getIndexSize(); i++) {
                String userInput = app.userInputScanner.nextLine();
                searchIndex.addStringToIndex(userInput);
            }

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
