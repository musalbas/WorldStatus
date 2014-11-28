package uk.ac.kcl.worldstatus.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;

import com.example.tester.HappyFamily;
import com.example.tester.R;

public class MainActivity extends ActionBarActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pairer test1 = new Pairer((SeekBar) findViewById(R.id.seekbar1), (TextView) findViewById(R.id.tv11), (Spinner) findViewById(R.id.sp11));
		Pairer test2 = new Pairer((SeekBar) findViewById(R.id.seekbar2), (TextView) findViewById(R.id.tv21), (Spinner) findViewById(R.id.sp21));
		Pairer test3 = new Pairer((SeekBar) findViewById(R.id.seekbar3), (TextView) findViewById(R.id.tv31), (Spinner) findViewById(R.id.sp31));
		Pairer test4 = new Pairer((SeekBar) findViewById(R.id.seekbar4), (TextView) findViewById(R.id.tv41), (Spinner) findViewById(R.id.sp41));
		Pairer test5 = new Pairer((SeekBar) findViewById(R.id.seekbar5), (TextView) findViewById(R.id.tv51), (Spinner) findViewById(R.id.sp51));
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

