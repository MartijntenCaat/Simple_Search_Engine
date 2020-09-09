package search;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class SearchMethodAll implements ISearchMethod {
    ArrayList<String> result;

    /**
     * If the strategy is ALL, the program should print lines containing all words from the query.
     *
     * @param queryParts from user input to be searched in search index.
     * @return result list with zero, one or more results in the search index.
     */
    @Override
    public ArrayList<String> searchFor(String[] queryParts, SearchIndex searchIndex) {
        result = new ArrayList<>();

        for (int i = 0; i < queryParts.length; i++) {

            if (searchIndex.getInvertedSearchIndex().containsKey(queryParts[i])) {
                for (int index : searchIndex.getInvertedSearchIndex().get(queryParts[i])) {
                    result.add(searchIndex.getRawSearchIndex().get(index));
                }
            } else {
                return new ArrayList<>();
            }
        }
        LinkedHashSet<String> resultHashSet = new LinkedHashSet<>(result);
        return new ArrayList<>(resultHashSet);
    }
}