package uk.ac.kcl.worldstatus.app;

import uk.ac.kcl.worldstatus.app.backend.LegacyDataGrabber;

import java.util.HashMap;

/**
 * Data structure to represent the indicator data to be displayed on the bar graph.
 */
public class GraphData {

    private LegacyDataGrabber data;
    private String countryName;
    private int year;
    private HashMap<String, Float> indicatorDataMap;

    /**
     * Initialise the graph data with a data grabber object.
     * @param data The data grabber object representing the data.
     */
    public GraphData(LegacyDataGrabber data) {
        this.data = data;
        this.countryName = data.getName();
        this.year = 2012; //by default the app will display the latest data
        this.indicatorDataMap = data.getDataByYear(year);
    }

    /**
     * Get the data.
     * @return The data.
     */
    public LegacyDataGrabber getData() {
        return data;
    }

    /**
     * Get the name of the country.
     * @return The name of the country.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Get the year that the data repesents.
     * @return The year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Get the indicators represented and their values for this country and year.
     * @return A HashMap of indicators and values.
     */
    public HashMap<String, Float> getIndicatorDataMap() {
        return indicatorDataMap;
    }
}
