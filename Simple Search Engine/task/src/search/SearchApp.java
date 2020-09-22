package search;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchApp {
    private final Scanner scanner;
    private final SearchIndex searchIndex;
    private boolean isUpAndRunning;
    private ISearchMethod method;

    public SearchApp() {
        this.scanner = new Scanner(System.in);
        this.isUpAndRunning = true;
        this.searchIndex = new SearchIndex();
    }

    private String[] askSearchQuery() {
        System.out.println("Enter a name or email to search all suitable people.");
        return scanner.nextLine().toLowerCase().split(" ");
    }

    private String askSearchMethod() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        return scanner.nextLine();
    }

    public boolean isUpAndRunning() {
        return isUpAndRunning;
    }

    public void runMenuAction() {
        final String menu = "\n=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit";

        System.out.println(menu);
        String action = scanner.nextLine();

        switch (action) {
            case "1":
                findAPersonByMethod();
                break;
            case "2":
                printDataStore();
                break;
            case "0":
                exitApp();
                break;
            default:
                System.out.println("Incorrect option! Try again.");
                break;
        }
    }

    /**
     * Method that does the searching, based on the chosen search algorithm, the algorithm is changed on runtime.
     */
    private void findAPersonByMethod() {
        String searchMethod = askSearchMethod();

        switch (searchMethod) {
            case "ALL":
                method = new SearchMethodAll();
                break;
            case "ANY":
                method = new SearchMethodAny();
                break;
            case "NONE":
                method = new SearchMethodNone();
                break;
            default:
                System.out.println("No correct method given! Try Again");
                break;
        }

        String[] searchQuery = askSearchQuery();
        ArrayList<String> searchResult = method.searchFor(searchQuery, searchIndex);
        printFoundPeople(searchResult);
    }

    /**
     * If the program is started with commandline args, this method will process them.
     * It reads the file and puts the file lines in the searchIndex.
     *
     * @param args commandline arguments
     */
    void processCommandLineArgs(String[] args) {
        if (args[0].equals("--data")) {
            File file = new File(args[1]);

            try (Scanner fileScanner = new Scanner(file)) {
                int positionInFile = 0;
                while (fileScanner.hasNext()) {
                    String rawInput = fileScanner.nextLine();
                    String[] input = rawInput.toLowerCase().split(" ");

                    searchIndex.addStringToRawIndex(rawInput);
                    searchIndex.addItemToInvertedIndex(input, positionInFile);

                    positionInFile++;
                }
            } catch (Exception exception) {
                System.out.println("Exception: " + exception);
            }
        }
    }

    private void printDataStore() {
        searchIndex.getRawSearchIndex().forEach((System.out::println));
    }

    private void exitApp() {
        isUpAndRunning = false;
        System.out.println("Bye!");
    }

    private void printFoundPeople(ArrayList<String> result) {
        if (result.isEmpty()) {
            System.out.println("Nothing found");
            return;
        }

        System.out.println(result.size() + " persons found:");
        result.forEach(System.out::println);
    }

}