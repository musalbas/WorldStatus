package uk.ac.kcl.worldstatus.app.backend;

import java.util.Comparator;

public class CustomComparatorScore implements Comparator<CountryValue> {
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