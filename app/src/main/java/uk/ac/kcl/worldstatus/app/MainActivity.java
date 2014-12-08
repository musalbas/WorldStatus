package uk.ac.kcl.worldstatus.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The Main Activity which represents the first thing the user sees when they run the app. It contains the core features for selecting the user's preferences before proceeding to look for a country.
 *
 * @author Team 2-L
 * @see android.app.Activity
 * @see android.support.v7.app.ActionBarActivity
 */
public class MainActivity extends ActionBarActivity implements Serializable {
    private Pairer paierer1, pairer2, pairer3, pairer4, pairer5;

    private Pairer[] pairerArray;
    HashMap<String, Integer> indicators;

    /**
     * The method to be executed when the Main Activity starts. Additionally where each indicator's components are paired together to allow for further functionality.
     *
     * @param savedInstanceState
     * @see uk.ac.kcl.worldstatus.app.Pairer
     * @see android.support.v7.app.ActionBarActivity onCreate
     */
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

        indicators = new HashMap<String, Integer>();
    }

    /**
     * The method invoked when the Menu is created.
     *
     * @param menu the menu created
     * @return true if the menu has been successfully created; false otherwise
     * @see android.view.Menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * The method invoked when an item in the Menu has been selected.
     *
     * @param item the MenuItem item selected
     * @return true after the defined functionality has executed successfully; false otherwise
     * @see android.view.MenuItem
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main);
        LinearLayout instructionsLayout = (LinearLayout) findViewById(R.id.instructions);

        if (id == R.id.instructions) {
            if (mainLayout.getVisibility() == View.VISIBLE) {
                mainLayout.setVisibility(View.GONE);
                instructionsLayout.setVisibility(View.VISIBLE);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * TODO
     *
     * @return
     */
    @Override
    public android.support.v4.app.FragmentManager getSupportFragmentManager() {
        return null;
    }

    /**
     * Checks if the selected indicator has not already been selected in any of the other Spinners.
     *
     * @param p the Paier object containing the indicator reference
     * @return true if the indicator has been selected only once; false otherwise;
     * @see uk.ac.kcl.worldstatus.app.Pairer
     * @see android.widget.Spinner
     */
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

    /**
     * Determines whether the user is allowed to proceed to the next activity after having validated whether there is
     * a valid choice of indicators.
     *
     * @return true if validation succeeds; false otherwise
     */
    public boolean allowRun() {
        indicators.clear();
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            if (pairerArray[i].isEnabled()) {
                if (validateIndicators(pairerArray[i])) {
                    indicators.put(getMetaCode(pairerArray[i].getScenario()), pairerArray[i].getImportance() + 1);

                } else {
                    new AlertDialog.Builder(this).setTitle("Invalid choice")
                            .setMessage("You must select different indicators.")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).setIcon(android.R.drawable.ic_dialog_alert).show();
                    return false;
                }
            } else {
                counter++;
                if (counter == 5) {
                    Context context = getApplicationContext();
                    CharSequence text = "Please select at least one indicator.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Gets the code for the specified indicator required to query the API.
     *
     * @param s the indicator name
     * @return the indicator code for the WorldBank API
     * @see uk.ac.kcl.worldstatus.app.backend.WorldBankData
     */
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

    /**
     * Starts the {@link uk.ac.kcl.worldstatus.app.GraphActivity}. Before proceeding to the next activity,
     * checks if there is a valid selection of all the indicators. If yes, proceeds to send a
     * {@link uk.ac.kcl.worldstatus.app.ParcelableMap} object containing a {@link java.util.HashMap} with the selected
     * indicators.
     *
     * @param v the View that invoked the method
     */
    public void graphHandler(View v) {

        if (allowRun()) {

            ParcelableMap parcelableIndicators = new ParcelableMap(indicators);

            Intent intent = new Intent(MainActivity.this, GraphActivity.class);
            intent.putExtra("indicators", parcelableIndicators);
            startActivity(intent);
        }
    }

    /**
     * A method for listening to button presses. Returns true if follows the default functionality.
     *
     * @param keyCode the keyCode for the pressed button
     * @param event   the type of KeyEvent
     * @return false if pressed during the Instructions screen.
     * @see android.view.KeyEvent
     * @see android.view.KeyEvent onKeyDown
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main);
            LinearLayout instructionsLayout = (LinearLayout) findViewById(R.id.instructions);
            if (instructionsLayout.getVisibility() == View.VISIBLE) {
                instructionsLayout.setVisibility(View.GONE);
                mainLayout.setVisibility(View.VISIBLE);
                return false;
            } /*else {
                return super.onKeyDown(keyCode, event);
            }*/
        }

        return super.onKeyDown(keyCode, event);
    }


}