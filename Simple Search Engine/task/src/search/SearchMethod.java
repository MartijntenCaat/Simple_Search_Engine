package search;

import java.util.ArrayList;

public interface SearchMethod {
    ArrayList<String> searchFor(String[] queryParts);
}