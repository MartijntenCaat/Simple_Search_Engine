package search;

import java.util.ArrayList;

/**
 * Interface for implementation of n search algorithms.
 */
public interface ISearchMethod {
    ArrayList<String> searchFor(String[] queryParts, SearchIndex searchIndex);
}