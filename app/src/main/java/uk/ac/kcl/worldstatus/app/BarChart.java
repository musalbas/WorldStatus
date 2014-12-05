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
public class BarChart {
    public GraphicalView getView(Context context) {
        int x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int y[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        int a[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int b[] = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};

        int c[] = {99, 88, 77, 66, 55, 44, 33, 22, 11, 10};
        int d[] = {13, 78, 14, 56, 99, 23, 100, 44, 3, 16};

        TimeSeries series = new TimeSeries("HIV infections.");
        TimeSeries series1 = new TimeSeries("Ebola infection.");
        TimeSeries series2 = new TimeSeries("AIDS infections.");
        TimeSeries series3 = new TimeSeries("Breast Cancer.");

        for (int i = 0; i < x.length; i++) {
            series.add(b[i], c[i]);
            series1.add(a[i], b[i]);
            series2.add(c[i], d[i]);
            series3.add(a[i], d[i]);
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
        mRenderer.setZoomRate(10.2f);
        mRenderer.setZoomEnabled(true, true);
        mRenderer.setPanLimits(new double[]{0, 12, 0, 100});
        mRenderer.setZoomLimits(new double[]{0, 12, 0, 100});
        mRenderer.setAxesColor(Color.DKGRAY);
        mRenderer.setLabelsColor(Color.LTGRAY);
        mRenderer.setClickEnabled(true);
        mRenderer.setSelectableBuffer(10);
        mRenderer.setInScroll(true);

        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setColor(Color.RED);
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.setInScroll(true);
        renderer = new XYSeriesRenderer();
        renderer.setColor(Color.BLUE);
        renderer.setPointStyle(PointStyle.SQUARE);
        renderer.setFillPoints(true);
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.setInScroll(true);
        renderer = new XYSeriesRenderer();
        renderer.setColor(Color.YELLOW);
        renderer.setPointStyle(PointStyle.DIAMOND);
        renderer.setFillPoints(true);
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.setInScroll(true);
        renderer = new XYSeriesRenderer();
        renderer.setColor(Color.GREEN);
        renderer.setPointStyle(PointStyle.TRIANGLE);
        renderer.setFillPoints(true);
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.setInScroll(true);

        final GraphicalView graphView = ChartFactory.getLineChartView(context, dataset, mRenderer);

        return graphView;
    }
}
