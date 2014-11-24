package uk.ac.kcl.worldstatus.app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by Kristin on 21-11-14.
 */
public class LineGraph {
    public GraphicalView getView(Context context) {
        int x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int y[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        TimeSeries series = new TimeSeries("HIV infections.");
        TimeSeries series1 = new TimeSeries("Ebola infection.");
        TimeSeries series2 = new TimeSeries("AIDS infections.");
        TimeSeries series3 = new TimeSeries("Breast Cancer.");

        for (int i=0; i<x.length; i++) {
            series.add(x[i], y[i]);
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series);
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.setChartTitle("Percentage of infections compared to time");
        mRenderer.setAxisTitleTextSize(16);
        mRenderer.setChartTitleTextSize(45);
        mRenderer.setLabelsTextSize(27);
        mRenderer.setLegendTextSize(60);
        mRenderer.setLegendHeight(1150);
        mRenderer.setFitLegend(true);
        mRenderer.setPointSize(8f);
        mRenderer.setMargins(new int[]{50, 40, 85, 40});
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setMarginsColor(Color.BLACK);
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.DKGRAY);
        mRenderer.setPanEnabled(false, true);
        mRenderer.setZoomRate(0.2f);
        mRenderer.setZoomEnabled(true, true);
        mRenderer.setPanLimits(new double[]{0, 12, 0, 100});
        mRenderer.setZoomLimits(new double[]{0, 12, 0, 100});

        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setColor(Color.GREEN);
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);

        mRenderer.addSeriesRenderer(renderer);
        mRenderer.setAxesColor(Color.DKGRAY);
        mRenderer.setLabelsColor(Color.LTGRAY);

        return ChartFactory.getLineChartView(context, dataset, mRenderer);
    }
}
