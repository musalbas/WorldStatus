package uk.ac.kcl.worldstatus.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import org.achartengine.GraphicalView;

/**
 * Created by Kristin on 22-11-14.
 */
public class GraphActivity extends Activity {

    Spinner spinnerTwo;
    Spinner spinnerFour;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Intent intent = getIntent();
        String graphType = intent.getStringExtra("graphType");
        //depending on graphType draw the graph. Draws a Line Graph by default atm.

        LineGraph line = new LineGraph();
        GraphicalView lineView = line.getView(this);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.chart);
        frameLayout.addView(lineView);

        spinnerTwo = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapterST = ArrayAdapter.createFromResource(this,
                R.array.start_year, android.R.layout.simple_spinner_item);
        spinnerTwo.setAdapter(adapterST);

        spinnerFour = (Spinner) findViewById(R.id.spinner4);

        ArrayAdapter<CharSequence> adapterSF = ArrayAdapter.createFromResource(this,
                R.array.end_year, android.R.layout.simple_spinner_item);
        spinnerFour.setAdapter(adapterSF);

        //Getting reference to Spinners which contain the list of years
        //Set data for Spinners and set the array adapter to contain the year list to the spinner widget

        spinnerTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                spinnerTwo.getSelectedItem();
                String fromYear = parent.getItemAtPosition(pos).toString();
                // make the year from spinner as string, the get the string,
                // and replace the year in the indicator url
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        spinnerFour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                spinnerFour.getSelectedItem();
                String toYear = parent.getItemAtPosition(pos).toString();
                // make the year from spinner as string, the get the string,
                // and replace the year in the indicator url
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

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