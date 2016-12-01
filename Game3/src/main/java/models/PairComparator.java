package models;

import java.util.Comparator;

public class PairComparator implements Comparator<Pair> {
    public int compare(Pair x1, Pair x2) {
        int result = Double.compare(x1.getY(), x2.getY());
        if ( result == 0 ) {
          result = Double.compare(x1.getX(), x2.getX());
        } 
        return result;
     }
}
