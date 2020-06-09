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

        while (app.isUpAndRunning) {
            SearchIndex searchIndex = new SearchIndex(app.askForIndexSize());

            System.out.println("Enter all people:");
            for (int i = 0; i < searchIndex.getIndexSize(); i++) {
                String userInput = app.userInputScanner.nextLine();
                searchIndex.addStringToIndex(userInput);
            }

            for (String s : searchIndex.searchIndex) {
                System.out.println(s);
            }

            System.out.println("Enter the number of search queries:");
            int numberOfQueries = Integer.parseInt(app.userInputScanner.nextLine());

            for (int i = 0; i < numberOfQueries; i++) {

                String query = app.askQuery();

                if (!searchIndex.searchIndex.contains(query)) {
                    System.out.println("No matching people found.");
                    continue;
                }

                for (String s : searchIndex.searchIndex) {
                    if (s.contains(query)) {
                        System.out.println("Found people:");
                        System.out.println(s);
                    }
                }


            }

        }


//        String[] input = inputScanner.nextLine().split(" ");
//        String query = inputScanner.nextLine();
//
//        int i  = 0;
//        boolean queryFound = false;
//
//        while (i < input.length && !queryFound) {
//            if (input[i].equals(query)) {
//                System.out.println(i + 1);
//                queryFound = true;
//            }
//            i++;
//        }
//
//        if (!queryFound) {
//            System.out.println("Not found");
//        }
//    }
    }
}
