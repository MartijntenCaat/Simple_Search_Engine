package search;

public class Main {
    public static void main(String[] args) {
        SearchApp app = new SearchApp();

        app.processCommandLineArgs(args);

        while (app.getIsUpAndRunning()) {
            app.runMenuAction();
        }
    }
}