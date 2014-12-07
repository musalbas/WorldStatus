package uk.ac.kcl.worldstatus.app;

import android.os.AsyncTask;
import uk.ac.kcl.worldstatus.app.backend.LegacyDataGrabber;
import uk.ac.kcl.worldstatus.app.backend.Utils;
import uk.ac.kcl.worldstatus.app.backend.WorldBankData;

import java.util.HashMap;

/**
 * Class to asynchronously grab data from the World Bank API.
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

    @Override
    protected void onCancelled() {
        Utils.cancel();
    }

    protected void onPostExecute(GraphData graphData) {
    }

}
