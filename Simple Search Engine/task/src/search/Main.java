package search;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private final Scanner scanner;
    private boolean isUpAndRunning;
    private int indexSize;
    public ArrayList<String> dataStore;
    private Map<String, ArrayList<Integer>> invertedIndex;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.dataStore = new ArrayList<>();
        this.isUpAndRunning = true;
        this.invertedIndex = new LinkedHashMap<>();
    }

    private void setIndexSize(int indexSize) {
        this.indexSize = indexSize;
    }

    public void addStringToIndex(String input) {
        dataStore.add(input);
    }

    public String askQuery() {
        System.out.println("Enter a name or email to search all suitable people.");
        return scanner.nextLine();
    }

    public void runMenuAction() {
        final String menu = "\n=== Menu ===\n" +
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

    public void findAPerson() {
        String query = askQuery();

        ArrayList<String> result = new ArrayList<>();
        for (String s : dataStore) {

            if (s.toLowerCase().contains(query.toLowerCase())) {
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

    public void exitApp() {
        isUpAndRunning = false;
        System.out.println("Bye!");
    }

    public void processCommandLineArgs(String[] args) {
        if (args.length > 0 && args[0].equals("--data")) {
            String fileName = args[1];

            File file = new File(fileName);

            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNext()) {
                    String input = fileScanner.nextLine();
                    addStringToIndex(input);
                }
            } catch (Exception e) {
                System.out.println("Wrong: " + e);
            }
        }
    }

    public void printDataStore() {
        for (String s : dataStore) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        Main app = new Main();

        app.processCommandLineArgs(args);
        while (app.isUpAndRunning) {
            app.runMenuAction();
        }
    }
}
