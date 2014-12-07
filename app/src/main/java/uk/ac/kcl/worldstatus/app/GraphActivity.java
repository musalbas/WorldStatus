package uk.ac.kcl.worldstatus.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
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
            grabDataGraph.doCancel();
            grabDataGraph.cancel(true);
        }

        return super.onKeyDown(keyCode, event);
    }

    public void fetchData(View v) {
        setContentView(R.layout.loading_layout);
        if (grabDataGraph == null || grabDataGraph.isCancelled()) {
            grabDataGraph = new GrabDataGraph();
            grabDataGraph.execute(indicators);
        } else {
            setContentView(R.layout.error_layout);
        }
    }
}