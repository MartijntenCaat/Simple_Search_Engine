package search;

public class Main {
    public static void main(String[] args) {
        SearchApp searchApp = new SearchApp();

        searchApp.processCommandLineArgs(args);

        while (searchApp.isUpAndRunning()) {
            searchApp.runMenuAction();
        }
    }
}