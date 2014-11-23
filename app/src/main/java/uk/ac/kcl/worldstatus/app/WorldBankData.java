package uk.ac.kcl.worldstatus.app;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * An API for the World Bank data sets.
 */
public class WorldBankData {

    /**
     * @param country   The country
     * @param indicator The name of a development indicator
     * @return An ArrayList of [x, y] data points.
     */
    public static ArrayList<Integer[]> getIndicatorData(String country, String indicator, int fromYear, int toYear) throws IOException, JSONException {
        ArrayList<Integer[]> dataPoints = new ArrayList<Integer[]>();

        String jsonString = "";

        URL url = new URL("http://api.worldbank.org/countries/" + country + "/indicators/" + indicator
                + "?per_page=100&date=" + fromYear + ":" + toYear + "&format=json");
        InputStream inputStream = url.openStream();
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));

        String buffer;
        while ((buffer = dataInputStream.readLine()) != null) {
            jsonString += buffer;
        }

        //JSONObject jsonObject = new JSONObject(jsonString);

        System.out.println(jsonString);

        return dataPoints;
    }

}
