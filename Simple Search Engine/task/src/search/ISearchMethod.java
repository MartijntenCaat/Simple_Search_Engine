package search;

import java.util.ArrayList;

public interface ISearchMethod {
    ArrayList<String> searchFor(String[] queryParts, SearchIndex searchIndex);
}