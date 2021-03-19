package search;

import java.util.ArrayList;

/**
 * Interface for implementation of n search algorithms.
 */
public interface ISearchMethod {

    /**
     * Method to be implemented in every subclass of a different search algorithm
     *
     * @param queryParts  incoming query split up in parts to be searched for
     * @param searchIndex the total index to be searched in
     * @return results of the search in a list
     */
    ArrayList<String> search(String[] queryParts, SearchIndex searchIndex);
}