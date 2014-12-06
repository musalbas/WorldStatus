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

import java.util.Map;

/**
 * Indicators bar chart.
 */
public class BarChart {

    private GraphData graphData;

    /**
     * Initialise the bar chart.
     * @param graphData The graph data.
     */
    public BarChart(GraphData graphData) {
        this.graphData = graphData;
    }

    /**
     * Render the graph view.
     * @param context The context.
     * @return The graphical view object.
     */
    public GraphicalView getView(Context context) {
        XYSeries series = new XYSeries("World Bank Data");

        int c = 0;
        for (Map.Entry<String, Float> entry : graphData.getIndicatorDataMap().entrySet()) {
            series.add(c, Math.round(entry.getValue()));
            c++;
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        dataset.addSeries(series);

        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setColor(Color.BLACK);
        renderer.setFillPoints(true);
        renderer.setLineWidth(2);
        renderer.setDisplayChartValues(true);
        renderer.setDisplayChartValuesDistance(10);

        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("You should move to " + graphData.getCountryName());
        multiRenderer.setXTitle("Indicator");
        multiRenderer.setYTitle("%");
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
        multiRenderer.setYAxisMax(100);
        multiRenderer.setXAxisMin(-0.5);
        multiRenderer.setXAxisMax(5);
        multiRenderer.setBarWidth(50);
        multiRenderer.setBarSpacing(200);
        multiRenderer.setBackgroundColor(Color.WHITE);
        //multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
        multiRenderer.setApplyBackgroundColor(true);

        multiRenderer.setMargins(new int[]{30, 30, 30, 30});
        int i = 0;
        for (Map.Entry<String, Float> entry : graphData.getIndicatorDataMap().entrySet()) {
            multiRenderer.addXTextLabel(i, entry.getKey());
            i++;
        }
        multiRenderer.addSeriesRenderer(renderer);

        final GraphicalView graphView = ChartFactory.getBarChartView(context, dataset, multiRenderer, org.achartengine.chart.BarChart.Type.DEFAULT);

        return graphView;
    }

}
