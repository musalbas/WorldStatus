package uk.ac.kcl.worldstatus.app;

import java.util.ArrayList;

/**
 * An API for the World Bank data sets.
 */
public class WorldBankData {

    /**
     *
     * @param country The country
     * @param indicator The name of a development indicator
     * @return An ArrayList of [x, y] data points.
     */
    public static ArrayList<Integer[]> getIndicatorData(String country, String indicator) {
        ArrayList<Integer[]> dataPoints = new ArrayList<Integer[]>();

        return dataPoints;
    }

}
