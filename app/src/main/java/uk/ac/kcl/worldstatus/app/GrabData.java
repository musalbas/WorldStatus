package uk.ac.kcl.worldstatus.app;

import android.os.AsyncTask;
import org.json.JSONException;
import org.xml.sax.SAXException;
import uk.ac.kcl.worldstatus.app.backend.WorldBankData;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mus on 05/12/14.
 */
public class GrabData extends AsyncTask<HashMap<String, Integer>, Void, HashMap<String, ArrayList<Float[]>>> {

    private String country;

    protected HashMap<String, ArrayList<Float[]>> doInBackground(HashMap<String, Integer>... maps) {
        HashMap<String, Integer> map = maps[0];

        String country = WorldBankData.findCountry(map).get(0);
        this.country = country;

        HashMap<String, ArrayList<Float[]>> result = new HashMap<String, ArrayList<Float[]>>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            try {
                result.put(entry.getKey(), WorldBankData.getIndicatorDataByCountry(country, entry.getKey(), 2007, 2012));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    protected void onPostExecute(HashMap<String, ArrayList<Float[]>> result) {
        // do something with result and this.country
    }

}
