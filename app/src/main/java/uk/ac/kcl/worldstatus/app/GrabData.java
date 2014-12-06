package uk.ac.kcl.worldstatus.app;

import android.os.AsyncTask;
import org.xml.sax.SAXException;
import uk.ac.kcl.worldstatus.app.backend.LegacyDataGrabber;
import uk.ac.kcl.worldstatus.app.backend.WorldBankData;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by mus on 05/12/14.
 */
public class GrabData extends AsyncTask<HashMap<String, Integer>, Void, GraphData> {

    @Override
    protected GraphData doInBackground(HashMap<String, Integer>... maps) {
        HashMap<String, Integer> map = maps[0];
        LegacyDataGrabber data = null;
        try {
            data = WorldBankData.findCountry(map);
        } catch (Exception e) {
            return null;
        }
        GraphData graphData = new GraphData(data);

        return graphData;
    }

    protected void onPostExecute(GraphData graphData) {
    }

}
