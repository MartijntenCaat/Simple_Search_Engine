package search;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchApp {
    private final Scanner scanner;
    private boolean isUpAndRunning;
    private final SearchIndex searchIndex;

    public SearchApp() {
        this.scanner = new Scanner(System.in);
        this.isUpAndRunning = true;
        this.searchIndex = new SearchIndex();
    }

    public String[] askSearchQuery() {
        System.out.println("Enter a name or email to search all suitable people.");
        return scanner.nextLine().toLowerCase().split(" ");
    }

    public String askSearchMethod() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        return scanner.nextLine();
    }

    public boolean getIsUpAndRunning() {
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

    public void findAPersonByMethod() {
        Searcher searcher = new Searcher();

        switch (askSearchMethod()) {
            case "ALL":
                searcher.setMethod(new SearchMethodAll());
                break;
            case "ANY":
                searcher.setMethod(new SearchMethodAny());
                break;
            case "NONE":
                searcher.setMethod(new SearchMethodNone());
                break;
            default:
                System.out.println("No correct method given!");
                break;
        }

        ArrayList<String> result = searcher.searchFor(askSearchQuery());
        printFoundPeople(result);
    }

    public void processCommandLineArgs(String[] args) {
        if (args.length > 0 && args[0].equals("--data")) {
            String fileName = args[1];
            File file = new File(fileName);

            try (Scanner fileScanner = new Scanner(file)) {
                int positionInFile = 0;
                while (fileScanner.hasNext()) {
                    String rawInput = fileScanner.nextLine();
                    String[] input = rawInput.toLowerCase().split(" ");

                    addStringToIndex(rawInput);
                    addItemToInvertedIndex(input, positionInFile);

                    positionInFile++;
                }
            } catch (Exception exception) {
                System.out.println("Exception: " + exception);
            }
        }
    }

    public void printDataStore() {
        searchIndex.getRawSearchIndex().forEach((System.out::println));
    }

    public void exitApp() {
        isUpAndRunning = false;
        System.out.println("Bye!");
    }

    public void printFoundPeople(ArrayList<String> result) {
        if (result.isEmpty()) {
            System.out.println("Nothing found");
            return;
        }

        System.out.println(result.size() + " persons found:");
        result.forEach(System.out::println);
    }

    public void addStringToIndex(String input) {
        searchIndex.getRawSearchIndex().add(input);
    }

    public void addItemToInvertedIndex(String[] input, int positionInFile) {
        for (String string : input) {
            if (!searchIndex.getInvertedSearchIndex().containsKey(string)) {
                ArrayList<Integer> position = new ArrayList<>();
                position.add(positionInFile);
                searchIndex.getInvertedSearchIndex().put(string, position);
            } else {
                ArrayList<Integer> existingPositionList = searchIndex.getInvertedSearchIndex().get(string);
                existingPositionList.add(positionInFile);
                searchIndex.getInvertedSearchIndex().put(string, existingPositionList);
            }
        }
    }
}