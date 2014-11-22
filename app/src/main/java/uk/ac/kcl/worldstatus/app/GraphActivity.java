package uk.ac.kcl.worldstatus.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import org.achartengine.GraphicalView;

/**
 * Created by Kristin on 22-11-14.
 */
public class GraphActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Intent intent = getIntent();
        String graphType = intent.getStringExtra("graphType");
        //depending on graphType draw the graph. Draws a Line Graph by default atm.

        LineGraph line = new LineGraph();
        GraphicalView lineView = line.getView(this);
        LinearLayout linLayout = (LinearLayout) findViewById(R.id.chart);
        linLayout.addView(lineView);
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
}