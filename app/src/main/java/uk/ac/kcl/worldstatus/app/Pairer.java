package uk.ac.kcl.worldstatus.app;


import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * This is used to pair the seekbar, spinner and textview, just to make sure that they comunicate propperly
 *
 * @author Felix
 */
public class Pairer extends Activity implements OnSeekBarChangeListener {

    private SeekBar scrollIt;
    private Spinner spinnIt;
    private TextView percentig;
    private LinearLayout layout;
    private Button button;

    public Pairer(SeekBar seekBar, TextView textView, Spinner spinn, LinearLayout linLay, Button b) {
        this.scrollIt = seekBar;
        this.percentig = textView;
        this.spinnIt = spinn;
        this.layout = linLay;
        this.button = b;
        scrollIt.setOnSeekBarChangeListener(this);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // this can either be layout.setVisibility(100), if you want to hide it, or layout.setVisibility(View.GONE)
                // if you want to compleatly distroy the layout. Your call.
                layout.setVisibility(100);
            }
        });
    }

    // if true, ignore when getting data
    public boolean getVisible() {
        if (layout.getVisibility() == 100) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
        int temp = scrollIt.getProgress();
        switch (temp) {
            case 0:
                percentig.setText("Low");
                break;
            case 1:
                percentig.setText("Medium");
                break;
            case 2:
                percentig.setText("High");
                break;
        }

    }

    public void setGray() {
        layout.setBackgroundColor(Color.GRAY);
    }

    public void setVisibility(int i) {
        layout.setVisibility(i);
    }

    public String getPercentig() {
        return (String) percentig.getText();
    }

    public String getScenario() {
        return (String) spinnIt.getSelectedItem();
    }

    @Override
    public void onStartTrackingTouch(SeekBar arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar arg0) {
        // TODO Auto-generated method stub

    }
}