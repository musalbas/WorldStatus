package uk.ac.kcl.worldstatus.app;

import uk.ac.kcl.worldstatus.app.backend.LegacyDataGrabber;

import java.util.HashMap;

/**
 * Created by Kristin on 05-12-14.
 */
public class GraphData {
    private LegacyDataGrabber data;
    private String countryName;
    private int year;
    private HashMap<String, Float> indicatorDataMap;

    public GraphData(LegacyDataGrabber data) {
        this.data = data;
        this.countryName = data.getName();
        this.year = 2012; //by default the app will display the latest data
        this.indicatorDataMap = data.getDataByYear(year);
    }

    public LegacyDataGrabber getData() {
        return data;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getYear() {
        return year;
    }

    public HashMap<String, Float> getIndicatorDataMap() {
        return indicatorDataMap;
    }
}
