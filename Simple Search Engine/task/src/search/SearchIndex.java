package search;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SearchIndex {
    public static ArrayList<String> rawSearchIndex;
    public static Map<String, ArrayList<Integer>> invertedSearchIndex;

    public SearchIndex() {
        rawSearchIndex = new ArrayList<>();
        invertedSearchIndex = new LinkedHashMap<>();
    }
}