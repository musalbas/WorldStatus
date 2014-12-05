package uk.ac.kcl.worldstatus.app.backend;

import java.util.Comparator;

public class CustomComparator implements Comparator<CountryValue> {
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
