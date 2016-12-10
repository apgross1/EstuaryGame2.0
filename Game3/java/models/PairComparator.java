package models;

import java.io.Serializable;
import java.util.Comparator;


/**
 * @author Andrew
 *
 */
public class PairComparator implements Comparator<Pair>, Serializable {
    public int compare(Pair x1, Pair x2) {
        int result = Double.compare(x1.getY(), x2.getY());
        if ( result == 0 ) {
          result = Double.compare(x1.getX(), x2.getX());
        } 
        return result;
    }
}
