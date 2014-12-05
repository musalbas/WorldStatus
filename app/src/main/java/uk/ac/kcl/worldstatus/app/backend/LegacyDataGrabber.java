package uk.ac.kcl.worldstatus.app.backend;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Harrison Perry
 *         A class that wraps all the data for the country selected based on user input
 *         this class feeds the graphs generated after a user has pressed 'go!'
 */
public class LegacyDataGrabber {

    ArrayList<ArrayList<Float[]>> data = null; // five indicators : 5 years worth of data : pairs of year & %
    String name = null;
    String[] indicators = null;

    public LegacyDataGrabber(ArrayList<ArrayList<Float[]>> data, String name, String[] indicators) {
        this.data = data;
        this.name = name;
        this.indicators = indicators;

    }


    public ArrayList<ArrayList<Float[]>> getData() {
        return data;
    }

    public String getName() {

        return name;
    }

    public String[] getIndicators() {

        return indicators;
    }

    public HashMap getDataByYear(int year) {
        HashMap<String, Float> map = new HashMap<String, Float>();
        int index = 0;
        for (ArrayList<Float[]> af : data)  // this loops between indicators
        {
            System.out.println("Idicator's code: " + indicators[index]);
            for (Float[] pairs : af) // this loops through years
            {
                if (pairs[0] == year) {
                    map.put(getEnglishName(indicators[index]), pairs[1]);
                    System.out.println(pairs[0] + " " + pairs[1]); // prints the year and the data
                }
            }

            index++;
        }

        return map;
    }

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