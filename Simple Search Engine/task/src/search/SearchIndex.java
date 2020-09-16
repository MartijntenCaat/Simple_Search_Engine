package search;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class of the index that has a raw index and a inverted index to search for specific words
 */
public class SearchIndex {
    private final ArrayList<String> rawSearchIndex;
    private final Map<String, ArrayList<Integer>> invertedSearchIndex;

    public SearchIndex() {
        rawSearchIndex = new ArrayList<>();
        invertedSearchIndex = new LinkedHashMap<>();
    }

    public ArrayList<String> getRawSearchIndex() {
        return rawSearchIndex;
    }

    public Map<String, ArrayList<Integer>> getInvertedSearchIndex() {
        return invertedSearchIndex;
    }
}