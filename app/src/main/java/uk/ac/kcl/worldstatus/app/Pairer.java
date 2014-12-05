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

    private SeekBar seekbar;
    private Spinner spinner;
    private TextView percentage;
    private LinearLayout layout;
    private Button button;

    public Pairer(SeekBar seekBar, TextView textView, Spinner spinn, LinearLayout linLay, Button b) {
        this.seekbar = seekBar;
        this.percentage = textView;
        this.spinner = spinn;
        this.layout = linLay;
        this.button = b;

        seekbar.setOnSeekBarChangeListener(this);

        button.setEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (isVisible()) {
                    layout.setAlpha(0.3f);
                    seekbar.setEnabled(false);
                    spinner.setEnabled(false);
                } else {
                    layout.setAlpha(1.0f);
                    button.setText("X");
                    seekbar.setEnabled(true);
                    spinner.setEnabled(true);
                }
            }
        });
    }

    public void clickMe(View view) {

    }

    public Button getButton() {
        return button;
    }

    public boolean isVisible() {
        if (layout.getAlpha() == 1.0f) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
        int temp = seekbar.getProgress();
        switch (temp) {
            case 0:
                percentage.setText("Low");
                setColor(253, 245, 230);
                break;
            case 1:
                percentage.setText("Medium");
                setColor(255, 231, 186);
                break;
            case 2:
                percentage.setText("High");
                setColor(205, 186, 150);
                break;
        }
    }

    public void setColor(int i, int j, int k) {
        layout.setBackgroundColor(Color.rgb(i, j, k));
    }

    public void setVisibility(int i) {
        layout.setVisibility(i);
    }

    public int getImportance() {
        return seekbar.getProgress();
    }

    public String getScenario() {
        return (String) spinner.getSelectedItem();
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