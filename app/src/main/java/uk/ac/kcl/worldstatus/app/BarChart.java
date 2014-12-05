package uk.ac.kcl.worldstatus.app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by Kristin on 21-11-14.
 */
public class BarChart {
    private String[] Years = new String[]{
            "2001", "2002", "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010", "2011", "2012"

    };

    public GraphicalView getView(Context context) {
        int[] x = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] disease1 = {2000, 2500, 2700, 3000, 2800, 3500, 3700, 3800, 0, 0, 0, 0};
        int[] disease2 = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400, 0, 0, 0, 0};

        XYSeries disease1Series = new XYSeries("disease1");
        XYSeries disease2Series = new XYSeries("disease2");

        // Adding data to Income and Expense Series
        for (int i = 0; i < x.length; i++) {
            disease1Series.add(i, disease1[i]);
            disease2Series.add(i, disease2[i]);
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        dataset.addSeries(disease1Series);
        dataset.addSeries(disease2Series);

        XYSeriesRenderer disease1Renderer = new XYSeriesRenderer();
        disease1Renderer.setColor(Color.CYAN);
        disease1Renderer.setFillPoints(true);
        disease1Renderer.setLineWidth(2);
        disease1Renderer.setDisplayChartValues(true);
        disease1Renderer.setDisplayChartValuesDistance(10);

        XYSeriesRenderer disease2Renderer = new XYSeriesRenderer();
        disease2Renderer.setColor(Color.GREEN);
        disease2Renderer.setFillPoints(true);
        disease2Renderer.setLineWidth(2);
        disease2Renderer.setDisplayChartValues(true);

        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("Disease1 vs Disease2 Chart");
        multiRenderer.setXTitle("Years");
        multiRenderer.setYTitle("Amount effected");
        multiRenderer.setChartTitleTextSize(28);
        multiRenderer.setAxisTitleTextSize(24);
        multiRenderer.setLabelsTextSize(24);
        multiRenderer.setZoomButtonsVisible(false);

        multiRenderer.setPanEnabled(false, false);
        multiRenderer.setClickEnabled(false);
        multiRenderer.setZoomEnabled(false, false);
        multiRenderer.setShowGridY(false);
        multiRenderer.setShowGridX(false);
        multiRenderer.setFitLegend(true);
        multiRenderer.setShowGrid(false);
        multiRenderer.setZoomEnabled(false);
        multiRenderer.setExternalZoomEnabled(false);
        multiRenderer.setAntialiasing(true);
        multiRenderer.setInScroll(false);
        multiRenderer.setLegendHeight(30);
        multiRenderer.setXLabelsAlign(Paint.Align.CENTER);
        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
        multiRenderer.setYLabels(10);
        multiRenderer.setYAxisMax(4000);
        multiRenderer.setXAxisMin(-0.5);
        multiRenderer.setXAxisMax(11);
        multiRenderer.setBarSpacing(0.5);
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
        //multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
        multiRenderer.setApplyBackgroundColor(true);

        multiRenderer.setMargins(new int[]{30, 30, 30, 30});
        for (int i = 0; i < x.length; i++) {
            multiRenderer.addXTextLabel(i, Years[i]);
        }
        multiRenderer.addSeriesRenderer(disease1Renderer);
        multiRenderer.addSeriesRenderer(disease2Renderer);

        final GraphicalView graphView = ChartFactory.getBarChartView(context, dataset, multiRenderer, org.achartengine.chart.BarChart.Type.DEFAULT);

        return graphView;
    }
}
