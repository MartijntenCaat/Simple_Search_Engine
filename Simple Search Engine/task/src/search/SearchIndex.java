package search;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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