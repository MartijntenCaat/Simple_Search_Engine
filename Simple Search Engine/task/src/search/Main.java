package search;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

interface SearchMethod {
    ArrayList<String> searchFor(String[] queryParts);
}

class SearchMethodAll implements SearchMethod {
    ArrayList<String> result;

    /**
     * If the strategy is ALL, the program should print lines containing all words from the query.
     *
     * @param queryParts from user input to be searched in search index.
     * @return result list with zero, one or more results in the search index.
     */
    @Override
    public ArrayList<String> searchFor(String[] queryParts) {
        result = new ArrayList<>();

        for (int i = 0; i < queryParts.length; i++) {

            if (SearchIndex.invertedSearchIndex.containsKey(queryParts[i])) {
                for (int index : SearchIndex.invertedSearchIndex.get(queryParts[i])) {
                    result.add(SearchIndex.rawSearchIndex.get(index));
                }
            } else {
                return new ArrayList<>();
            }
        }
        LinkedHashSet<String> resultHashSet = new LinkedHashSet<>(result);
        return new ArrayList<>(resultHashSet);
    }
}

class SearchMethodAny implements SearchMethod {
    ArrayList<String> result;

    /**
     * If the strategy is ANY, the program should print lines containing at least one word from the query.
     *
     * @param queryParts from user input to be searched in search index.
     * @return result list with zero, one or more results in the search index.
     */
    @Override
    public ArrayList<String> searchFor(String[] queryParts) {
        result = new ArrayList<>();

        ArrayList<Integer> addableIndexNumbers = new ArrayList();

        for (String part : queryParts) {
            if (SearchIndex.invertedSearchIndex.keySet().contains(part)) {
                for (int i : SearchIndex.invertedSearchIndex.get(part)) {
                    addableIndexNumbers.add(i);
                }
            }
        }

        LinkedHashSet<Integer> addableIndexNumbersSet = new LinkedHashSet<>(addableIndexNumbers);

        for (int i : addableIndexNumbersSet) {
            result.add(SearchIndex.rawSearchIndex.get(i));
        }

        return result;
    }
}

class SearchMethodNone implements SearchMethod {
    ArrayList<String> result;

    /**
     * If the strategy is NONE, the program should print lines that do not contain words from the query at all.
     *
     * @param queryParts from user input to be searched in search index.
     * @return result list with zero, one or more results in the search index.
     */
    @Override
    public ArrayList<String> searchFor(String[] queryParts) {
        result = new ArrayList<>();
        LinkedHashSet<Integer> removableLineNumbers = new LinkedHashSet<>();

        for (String part : queryParts) {
            if (SearchIndex.invertedSearchIndex.containsKey(part)) {
                ArrayList<Integer> lineNumbers = SearchIndex.invertedSearchIndex.get(part);

                for (int number : lineNumbers) {
                    removableLineNumbers.add(number);
                }
            }
        }

        for (int i = 0; i < SearchIndex.rawSearchIndex.size(); i++) {
            if (!removableLineNumbers.contains(i)) {
                result.add(SearchIndex.rawSearchIndex.get(i));
            }
        }
        return result;
    }
}

class Searcher {
    private SearchMethod method;

    public Searcher() {
    }

    public void setMethod(SearchMethod method) {
        this.method = method;
    }

    public ArrayList<String> searchFor(String[] queryParts) {
        return method.searchFor(queryParts);
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
        rawSearchIndex = new ArrayList<>();
        invertedSearchIndex = new LinkedHashMap<>();
    }
}