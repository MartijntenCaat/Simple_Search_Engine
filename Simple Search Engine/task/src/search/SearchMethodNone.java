package search;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class SearchMethodNone implements ISearchMethod {
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