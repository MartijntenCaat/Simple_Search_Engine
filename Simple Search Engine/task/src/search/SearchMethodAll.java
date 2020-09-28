package search;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class SearchMethodAll implements ISearchMethod {
    ArrayList<String> result;

    /**
     * If the strategy is ALL, the program should print lines containing all words from the query.
     * If at least one query part is not present in search index the result should be empty.
     *
     * @param queryParts from user input to be searched in search index.
     * @return result list with zero, one or more results in the search index.
     */
    @Override
    public ArrayList<String> search(String[] queryParts, SearchIndex searchIndex) {
        result = new ArrayList<>();

        for (String part : queryParts) {
            if (searchIndex.getInvertedSearchIndex().containsKey(part)) {
                for (int index : searchIndex.getInvertedSearchIndex().get(part)) {
                    result.add(searchIndex.getRawSearchIndex().get(index));
                }
            } else {
                result.clear();
                return result;
            }
        }
        LinkedHashSet<String> resultHashSet = new LinkedHashSet<>(result);
        return new ArrayList<>(resultHashSet);
    }
}