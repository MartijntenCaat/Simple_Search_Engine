package search;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class SearchMethodAny implements ISearchMethod {
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