package search;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

interface SearchMethod {
    ArrayList<String> searchFor(String query);
}

class SearchMethodAll implements SearchMethod {
    ArrayList<String> result;

    /**
     * If the strategy is ALL, the program should print lines containing all words from the query.
     *
     * @param query from user input to be searched in search index.
     * @return result list with zero, one or more results in the search index.
     */
    @Override
    public ArrayList<String> searchFor(String query) {
        result = new ArrayList<>();

        if (SearchIndex.invertedSearchIndex.containsKey(query)) {
            for (int index : SearchIndex.invertedSearchIndex.get(query)) {
                result.add(SearchIndex.rawSearchIndex.get(index));
            }
        }
        return result;
    }
}

class SearchMethodAny implements SearchMethod {
    ArrayList<String> result;

    /**
     * If the strategy is ANY, the program should print lines containing at least one word from the query.
     *
     * @param query from user input to be searched in search index.
     * @return result list with zero, one or more results in the search index.
     */
    @Override
    public ArrayList<String> searchFor(String query) {
        result = new ArrayList<>();

        if (SearchIndex.invertedSearchIndex.containsKey(query)) {
            for (int index : SearchIndex.invertedSearchIndex.get(query)) {
                result.add(SearchIndex.rawSearchIndex.get(index));
            }
        }
        return result;
    }
}

class SearchMethodNone implements SearchMethod {
    ArrayList<String> result;

    /**
     * If the strategy is NONE, the program should print lines that do not contain words from the query at all.
     *
     * @param query from user input to be searched in search index.
     * @return result list with zero, one or more results in the search index.
     */
    @Override
    public ArrayList<String> searchFor(String query) {
        result = new ArrayList<>();
        System.out.println("NONE");
        return new ArrayList<>();
    }
}

class Searcher {
    private SearchMethod method;

    public Searcher() {
    }

    public void setMethod(SearchMethod method) {
        this.method = method;
    }

    public ArrayList<String> searchFor(String query) {
        return method.searchFor(query);
    }
}

public class Main {
    public static void main(String[] args) {
        SearchApp app = new SearchApp();

        app.processCommandLineArgs(args);
        while (app.getIsUpAndRunning()) {
            app.runMenuAction();
        }
    }
}

class SearchIndex {
    public static ArrayList<String> rawSearchIndex;
    public static Map<String, ArrayList<Integer>> invertedSearchIndex;

    public SearchIndex() {
        this.rawSearchIndex = new ArrayList<>();
        this.invertedSearchIndex = new LinkedHashMap<>();
    }
}

class SearchApp {
    private final Scanner scanner;
    private boolean isUpAndRunning;
    private SearchIndex searchIndex;
//    public ArrayList<String> dataStore;
//    private Map<String, ArrayList<Integer>> invertedIndex;

    public SearchApp() {
        this.scanner = new Scanner(System.in);
        this.isUpAndRunning = true;
//        this.dataStore = new ArrayList<>();
//        this.invertedIndex = new LinkedHashMap<>();
        this.searchIndex = new SearchIndex();
    }

    public String askSearchQuery() {
        System.out.println("Enter a name or email to search all suitable people.");
        return scanner.nextLine();
    }

    public String askSearchMethod() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        return scanner.nextLine();
    }

    public boolean getIsUpAndRunning() {
        return isUpAndRunning;
    }

    public SearchIndex getSearchIndex() {
        return searchIndex;
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
                    String[] input = rawInput.split(" ");

                    addStringToIndex(rawInput);
                    addItemToInvertedIndex(input, positionInFile);

                    positionInFile++;
                }
            } catch (Exception exception) {
                System.out.println("Wrong: " + exception);
            }
        }
    }

    public void printDataStore() {
        for (String string : SearchIndex.rawSearchIndex) {
            System.out.println(string);
        }
    }

    public void exitApp() {
        isUpAndRunning = false;
        System.out.println("Bye!");
    }

    public void printFoundPeople(ArrayList<String> result) {
        if (result.size() == 0) {
            System.out.println("Nothing found");
            return;
        }

        System.out.println(result.size() + " persons found:");
        for (String singleResult : result) {
            System.out.println(singleResult);
        }
    }

    public void addStringToIndex(String input) {
        SearchIndex.rawSearchIndex.add(input);
    }

    public void addItemToInvertedIndex(String[] input, int positionInFile) {
        for (String string : input) {
            if (!SearchIndex.invertedSearchIndex.containsKey(string)) {
                ArrayList<Integer> position = new ArrayList<>();
                position.add(positionInFile);
                SearchIndex.invertedSearchIndex.put(string, position);
            } else {
                ArrayList<Integer> existingPositionList = SearchIndex.invertedSearchIndex.get(string);
                existingPositionList.add(positionInFile);
                SearchIndex.invertedSearchIndex.put(string, existingPositionList);
            }
        }
    }
}
