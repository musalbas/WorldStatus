package uk.ac.kcl.worldstatus.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.io.Serializable;
import java.util.HashMap;

public class MainActivity extends ActionBarActivity implements Serializable {
    private Pairer paierer1, pairer2, pairer3, pairer4, pairer5;

    private Pairer[] pairerArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paierer1 = new Pairer(1, (SeekBar) findViewById(R.id.sb1), (TextView) findViewById(R.id.tv1), (Spinner) findViewById(R.id.sp1), (LinearLayout) findViewById(R.id.ll1), (Button) findViewById(R.id.button1));
        pairer2 = new Pairer(2, (SeekBar) findViewById(R.id.sb2), (TextView) findViewById(R.id.tv2), (Spinner) findViewById(R.id.sp2), (LinearLayout) findViewById(R.id.ll2), (Button) findViewById(R.id.button2));
        pairer3 = new Pairer(3, (SeekBar) findViewById(R.id.sb3), (TextView) findViewById(R.id.tv3), (Spinner) findViewById(R.id.sp3), (LinearLayout) findViewById(R.id.ll3), (Button) findViewById(R.id.button3));
        pairer4 = new Pairer(4, (SeekBar) findViewById(R.id.sb4), (TextView) findViewById(R.id.tv4), (Spinner) findViewById(R.id.sp4), (LinearLayout) findViewById(R.id.ll4), (Button) findViewById(R.id.button4));
        pairer5 = new Pairer(5, (SeekBar) findViewById(R.id.sb5), (TextView) findViewById(R.id.tv5), (Spinner) findViewById(R.id.sp5), (LinearLayout) findViewById(R.id.ll5), (Button) findViewById(R.id.button5));

        paierer1.setColor(253, 245, 230);
        pairer2.setColor(253, 245, 230);
        pairer3.setColor(253, 245, 230);
        pairer4.setColor(253, 245, 230);
        pairer5.setColor(253, 245, 230);

        pairerArray = new Pairer[]{paierer1, pairer2, pairer3, pairer4, pairer5};
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

    public boolean validateIndicators(Pairer p) {
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            if (p.getScenario().equals(pairerArray[i].getScenario()) && pairerArray[i].isEnabled()) {
                counter++;
            }
            if (counter > 1) {
                return false;
            }
        }
        return true;
    }

    public String getMetaCode(String s) {
        String toReturn = "";
        char w = s.charAt(0);
        switch (w) {
            case '1':
                toReturn = "SL.UEM.TOTL.ZS";
                break;
            case '2':
                toReturn = "SP.URB.TOTL.ZS";
                break;
            case '3':
                toReturn = "IC.TAX.TOTL.CP.ZS";
                break;
            case '4':
                toReturn = "AG.LND.FRST.ZS";
                break;
            case '5':
                toReturn = "FP.CPI.TOTL.ZG";
                break;
            case '6':
                toReturn = "SE.SEC.ENRR";
                break;
            case '7':
                toReturn = "EG.USE.COMM.FO.ZS";
                break;
            case '8':
                toReturn = "GC.XPN.TOTL.GD.ZS";
                break;
        }

        return toReturn;
    }

    public void graphHandler(View v) {

        HashMap<String, Integer> indicators = new HashMap<String, Integer>();

        for (int i = 0; i < 5; i++) {
            if (pairerArray[i].isEnabled()) {
                if (validateIndicators(pairerArray[i])) {
                    indicators.put(getMetaCode(pairerArray[i].getScenario()), pairerArray[i].getImportance() + 1);
                } else {
                    new AlertDialog.Builder(this)
                            .setTitle("Invalid choice")
                            .setMessage("You must select different indicators.")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                }
            }
        }

        ParcelableMap parcelableIndicators = new ParcelableMap(indicators);

        Intent intent = new Intent(MainActivity.this, GraphActivity.class);
        intent.putExtra("indicators", parcelableIndicators);
        startActivity(intent);
    }

}