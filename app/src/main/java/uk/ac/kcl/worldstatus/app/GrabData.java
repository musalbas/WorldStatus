package uk.ac.kcl.worldstatus.app;

import android.os.AsyncTask;
import uk.ac.kcl.worldstatus.app.backend.LegacyDataGrabber;
import uk.ac.kcl.worldstatus.app.backend.WorldBankData;

import java.util.HashMap;

/**
 * Created by mus on 05/12/14.
 */
public class GrabData extends AsyncTask<HashMap<String, Integer>, Void, GraphData> {

    @Override
    protected GraphData doInBackground(HashMap<String, Integer>... maps) {
        HashMap<String, Integer> map = maps[0];
        LegacyDataGrabber data = WorldBankData.findCountry(map);
        GraphData graphData = new GraphData(data);

        return graphData;
    }

    /*
     private boolean haveNetworkConnection() {
         boolean haveConnectedWifi = false;
         boolean haveConnectedMobile = false;

         ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo[] netInfo = cm.getAllNetworkInfo();
         for (NetworkInfo ni : netInfo) {
             if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                 if (ni.isConnected())
                     haveConnectedWifi = true;
             if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                 if (ni.isConnected())
                     haveConnectedMobile = true;
         }
         return haveConnectedWifi || haveConnectedMobile;
     }
 */
    protected void onPostExecute(GraphData graphData) {
    }


}
