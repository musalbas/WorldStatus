package uk.ac.kcl.worldstatus.app;

import android.content.Context;
import android.content.Intent;
import org.achartengine.ChartFactory;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by Kristin on 21-11-14.
 */
public class LineGraph {
    public Intent getIntent(Context context) {
        int x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int y[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        TimeSeries series = new TimeSeries("Line1");

        for (int i=0; i<x.length; i++) {
            series.add(x[i], y[i]);
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series);

        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        mRenderer.addSeriesRenderer(renderer);

        Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer, "Line Graph Title");

        return intent;
    }
}
