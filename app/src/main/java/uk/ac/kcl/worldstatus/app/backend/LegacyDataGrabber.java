package uk.ac.kcl.worldstatus.app.backend;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class that wraps all the data for the country selected based on user input
 * this class feeds the graphs generated after a user has pressed 'go!'
 */
public class LegacyDataGrabber {

    private ArrayList<ArrayList<Float[]>> data = null; // five indicators : 5 years worth of data : pairs of year & %
    private String name = null;
    private String[] indicators = null;

    /**
     * Initialise the data grabber.
     *
     * @param data       A two-dimension ArrayList of indicator data.
     * @param name       The name of the country.
     * @param indicators An array of indicators.
     */
    public LegacyDataGrabber(ArrayList<ArrayList<Float[]>> data, String name, String[] indicators) {
        this.data = data;
        this.name = name;
        this.indicators = indicators;

    }

    /**
     * Get the indicator data.
     *
     * @return The indicator data.
     */
    public ArrayList<ArrayList<Float[]>> getData() {
        return data;
    }

    /**
     * Get the name of the country.
     *
     * @return The name of the country.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the array of indicators.
     *
     * @return Array of indicators.
     */
    public String[] getIndicators() {
        return indicators;
    }

    /**
     * Get the indicator data by year.
     *
     * @param year The year.
     * @return The indicator data.
     */
    public HashMap getDataByYear(int year) {
        HashMap<String, Float> map = new HashMap<String, Float>();
        int index = 0;
        for (ArrayList<Float[]> af : data) { // this loops between indicators
            for (Float[] pairs : af) { // this loops through years
                if (pairs[0] == year) {
                    map.put(getEnglishName(indicators[index]), pairs[1]);
                    System.out.println(pairs[0] + " " + pairs[1]); // prints the year and the data
                }
            }

            index++;
        }

        return map;
    }

    /**
     * Get the English name of an indicator.
     *
     * @param x The indicator.
     * @return The English name of the indicator.
     */
    public String getEnglishName(String x) {
        if (x.equals("SL.UEM.TOTL.ZS")) {
            return "Unemployment";
        } else if (x.equals("SP.URB.TOTL.ZS")) {
            return "Urban Population";
        } else if (x.equals("IC.TAX.TOTL.CP.ZS")) {
            return "Tax Rate";
        } else if (x.equals("AG.LND.FRST.ZS")) {
            return "Forest Area";
        } else if (x.equals("FP.CPI.TOTL.ZG")) {
            return "Inflation";
        } else if (x.equals("SE.SEC.ENRR")) {
            return "Secondary school";
        } else if (x.equals("EG.USE.COMM.FO.ZS")) {
            return "Fossil Fuel";
        } else if (x.equals("GC.XPN.TOTL.GD.ZS")) {
            return "Expense";
        } else {
            return "Requested code is not mapped to an indicator.";
        }
    }

}