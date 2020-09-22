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
    public ArrayList<String> searchFor(String[] queryParts, SearchIndex searchIndex) {
        result = new ArrayList<>();

        ArrayList<Integer> addableIndexNumbers = new ArrayList<>();

        for (String part : queryParts) {
            if (searchIndex.getInvertedSearchIndex().containsKey(part)) {
                ArrayList<Integer> indexes = searchIndex.getInvertedSearchIndex().get(part);
                addableIndexNumbers.addAll(indexes);
            }
        }

        LinkedHashSet<Integer> addableIndexNumbersSet = new LinkedHashSet<>(addableIndexNumbers);

        for (int index : addableIndexNumbersSet) {
            result.add(searchIndex.getRawSearchIndex().get(index));
        }

        return result;
    }
}