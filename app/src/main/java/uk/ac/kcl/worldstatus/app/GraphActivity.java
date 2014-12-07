package uk.ac.kcl.worldstatus.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.achartengine.GraphicalView;
import uk.ac.kcl.worldstatus.app.backend.LegacyDataGrabber;

import java.util.HashMap;

/**
 * The graph screen.
 */
public class GraphActivity extends Activity {
    private LegacyDataGrabber data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout); //set to loading layout

        Bundle extras = getIntent().getExtras();
        ParcelableMap pMap = extras.getParcelable("indicators");
        HashMap<String, Integer> indicators = pMap.getIndicatorDataMap();

        GrabDataGraph grabDataGraph = new GrabDataGraph();
        grabDataGraph.execute(indicators);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public android.support.v4.app.FragmentManager getSupportFragmentManager() {
        return null;
    }

    public class GrabDataGraph extends GrabData {
        @Override
        public void onPostExecute(GraphData graphData) {
            if (graphData == null) {
                //TODO call error layout
            } else {
                BarChart line = new BarChart(graphData);
                GraphicalView graphView = line.getView(GraphActivity.this);
                setContentView(R.layout.activity_graph);
                TextView countryLabel = (TextView) findViewById(R.id.countryLabel);
                countryLabel.setText("Best match: " + graphData.getCountryName());
                TextView yearLabel = (TextView) findViewById(R.id.yearLabel);
                yearLabel.setText("Showing data for: " + graphData.getYear());
                FrameLayout frameLayout = (FrameLayout) findViewById(R.id.chart);
                frameLayout.addView(graphView);
            }
        }
    }
}