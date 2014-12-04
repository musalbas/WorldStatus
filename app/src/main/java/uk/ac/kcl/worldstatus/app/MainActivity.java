package uk.ac.kcl.worldstatus.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.io.Serializable;

public class MainActivity extends ActionBarActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pairer test1 = new Pairer((SeekBar) findViewById(R.id.sb1), (TextView) findViewById(R.id.tv1), (Spinner) findViewById(R.id.sp1), (LinearLayout) findViewById(R.id.ll1), (Button) findViewById(R.id.button1));
        Pairer test2 = new Pairer((SeekBar) findViewById(R.id.sb2), (TextView) findViewById(R.id.tv2), (Spinner) findViewById(R.id.sp2), (LinearLayout) findViewById(R.id.ll2), (Button) findViewById(R.id.button2));
        Pairer test3 = new Pairer((SeekBar) findViewById(R.id.sb3), (TextView) findViewById(R.id.tv3), (Spinner) findViewById(R.id.sp3), (LinearLayout) findViewById(R.id.ll3), (Button) findViewById(R.id.button3));
        Pairer test4 = new Pairer((SeekBar) findViewById(R.id.sb4), (TextView) findViewById(R.id.tv4), (Spinner) findViewById(R.id.sp4), (LinearLayout) findViewById(R.id.ll4), (Button) findViewById(R.id.button4));
        Pairer test5 = new Pairer((SeekBar) findViewById(R.id.sb5), (TextView) findViewById(R.id.tv5), (Spinner) findViewById(R.id.sp5), (LinearLayout) findViewById(R.id.ll5), (Button) findViewById(R.id.button5));

        test1.setGray();
        test3.setGray();
        test5.setGray();
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