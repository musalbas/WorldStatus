package uk.ac.kcl.worldstatus.app;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * Created by Kristin on 21-11-14.
 */
public class GraphActivity extends Activity {

    public void lineGraphHandler(View v) {
        LineGraph line = new LineGraph();
        Intent lineIntent = line.getIntent(this);
        startActivity(lineIntent);
    }
}
