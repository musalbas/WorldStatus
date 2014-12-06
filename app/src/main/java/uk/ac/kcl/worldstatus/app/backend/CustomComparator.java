package uk.ac.kcl.worldstatus.app.backend;

import java.util.Comparator;

/**
 * Comparator for CountryValues.
 */
public class CustomComparator implements Comparator<CountryValue> {

    /**
     * Compare two CountryValues.
     *
     * @param o1 The first CountryValue.
     * @param o2 The second CountryValue.
     * @return -1 if the first CountryValue is bigger, 1 if the opposite, and 0 if they are both equal.
     */
    @Override
    public int compare(CountryValue o1, CountryValue o2) {
        if (o1.getVal() > o2.getVal()) {
            return -1;
        } else if (o1.getVal() == o2.getVal()) {
            return 0;
        } else {
            return 1;
        }
    }

}
