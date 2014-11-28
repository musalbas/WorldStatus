package uk.ac.kcl.worldstatus.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
/**
 * This is used to pair the seekbar, spinner and textview, just to make sure that they comunicate propperly
 * @author Felix
 *
 */
public class Pairer extends Activity implements OnSeekBarChangeListener {

	private SeekBar scrollIt;
	private Spinner spinnIt;
	private TextView percentig;

	public Pairer(SeekBar seekBar, TextView textView, Spinner spinn) {
		this.scrollIt = seekBar;
		this.percentig = textView;
		this.spinnIt = spinn;
		scrollIt.setOnSeekBarChangeListener(this);
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