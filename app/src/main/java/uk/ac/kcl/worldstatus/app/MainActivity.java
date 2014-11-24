package uk.ac.kcl.worldstatus.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.io.Serializable;

public class MainActivity extends ActionBarActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textViewCountry = (AutoCompleteTextView) findViewById(R.id.textViewCountry);
        AutoCompleteTextView textViewCondition = (AutoCompleteTextView) findViewById(R.id.textViewCondition);
        // Get the string array
        String[] countries = getResources().getStringArray(R.array.countryList);
        String[] conditions = getResources().getStringArray(R.array.conditionList);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapterCountry =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        ArrayAdapter<String> adaptCondition =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, conditions);

        textViewCountry.setAdapter(adapterCountry);
        textViewCondition.setAdapter(adaptCondition);
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

    @Override
    public android.support.v4.app.FragmentManager getSupportFragmentManager() {
        return null;
    }

    public void graphHandler(View v) {

        Intent intent = new Intent(MainActivity.this, GraphActivity.class);
        intent.putExtra("graphType", "line"); // tell GraphActivity what kind of graph to draw
        startActivity(intent);
    }
}

