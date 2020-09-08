package search;

import java.util.ArrayList;

public class Searcher {
    private ISearchMethod method;

    public Searcher() {
    }

    public void setMethod(ISearchMethod method) {
        this.method = method;
    }

    public ArrayList<String> searchFor(String[] queryParts) {
        return method.searchFor(queryParts);
    }
}