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
    public ArrayList<String> searchFor(String[] queryParts, SearchIndex searchIndex) {
        result = new ArrayList<>();
        LinkedHashSet<Integer> removableLineNumbers = new LinkedHashSet<>();

        for (String part : queryParts) {
            if (searchIndex.getInvertedSearchIndex().containsKey(part)) {
                ArrayList<Integer> lineNumbers = searchIndex.getInvertedSearchIndex().get(part);
                removableLineNumbers.addAll(lineNumbers);
            }
        }

        for (int i = 0; i < searchIndex.getRawSearchIndex().size(); i++) {
            if (!removableLineNumbers.contains(i)) {
                result.add(searchIndex.getRawSearchIndex().get(i));
            }
        }
        return result;
    }
}