package search;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private final Scanner scanner;
    private boolean isUpAndRunning;
    public ArrayList<String> dataStore;
    private Map<String, ArrayList<Integer>> invertedIndex;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.dataStore = new ArrayList<>();
        this.isUpAndRunning = true;
        this.invertedIndex = new LinkedHashMap<>();
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
            case "1":
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

        if (invertedIndex.containsKey(query)) {
            printFoundPeople(query);
        } else {
            System.out.println("No matching people found.");
        }
    }

    public void printFoundPeople(String query) {
        ArrayList<Integer> foundPeopleIndex = invertedIndex.get(query);

        System.out.println(foundPeopleIndex.size() + " persons found:");
        for (int index : foundPeopleIndex) {
            System.out.println(dataStore.get(index));
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

            int positionInFile = 0;

            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNext()) {
                    String rawInput = fileScanner.nextLine();
                    String[] input = rawInput.split(" ");

                    addStringToIndex(rawInput);

                    for (String string : input) {
                        if (!invertedIndex.containsKey(string)) {
                            ArrayList<Integer> position = new ArrayList<>();
                            position.add(positionInFile);
                            invertedIndex.put(string, position);
                        } else {
                            ArrayList<Integer> existingPositionList = invertedIndex.get(string);
                            existingPositionList.add(positionInFile);
                            invertedIndex.put(string, existingPositionList);
                        }
                    }
                    positionInFile++;
                }
            } catch (Exception exception) {
                System.out.println("Wrong: " + exception);
            }
        }
    }

    public void printDataStore() {
        for (String string : dataStore) {
            System.out.println(string);
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
