package uk.ac.kcl.worldstatus.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.achartengine.GraphicalView;

import java.util.HashMap;

/**
 * The graph screen.
 */
public class GraphActivity extends Activity {
    private GrabDataGraph grabDataGraph;
    HashMap<String, Integer> indicators;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout); //set to loading layout

        Bundle extras = getIntent().getExtras();
        ParcelableMap pMap = extras.getParcelable("indicators");
        indicators = pMap.getIndicatorDataMap();

        grabDataGraph = new GrabDataGraph();
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
                grabDataGraph.cancel(true);
                setContentView(R.layout.error_layout);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Log.w("TEST", "TESTING");
            grabDataGraph.doCancel();
        }

        return super.onKeyDown(keyCode, event);
    }

    /*public void fetchData(View v) {
        //if(grabDataGraph.isCancelled()) {
            setContentView(R.layout.loading_layout);
            grabDataGraph = new GrabDataGraph();
            grabDataGraph.execute(indicators);
        //} else {
        //    Log.d("FAIL", "FAILED.");
        //}
    }*/
}