package uk.ac.kcl.worldstatus.app.backend;

import java.util.Comparator;

/**
 * Comparator for country scores.
 */
public class CustomComparatorScore implements Comparator<CountryValue> {

    /**
     * Compare the score of two CountryValues.
     *
     * @param o1 The first CountryValue.
     * @param o2 The second CountryValue.
     * @return -1 if the first CountryValue score is bigger, 1 if the opposite, and 0 if they are both equal.
     */
    @Override
    public int compare(CountryValue o1, CountryValue o2) {
        if (o1.getScore() > o2.getScore()) {
            return -1;
        } else if (o1.getScore() == o2.getScore()) {
            return 0;
        } else {
            return 1;
        }
    }

}