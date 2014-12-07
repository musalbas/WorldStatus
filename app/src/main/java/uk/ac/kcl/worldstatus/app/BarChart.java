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
        XYSeries series = new XYSeries("");

        int c = 0;
        for (Map.Entry<String, Float> entry : graphData.getIndicatorDataMap().entrySet()) {
            series.add(c, Math.round(entry.getValue()));
            c++;
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        dataset.addSeries(series);

        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setColor(Color.DKGRAY);
        renderer.setFillPoints(true);
        renderer.setLineWidth(2);
        renderer.setDisplayChartValues(true);
        renderer.setDisplayChartValuesDistance(10);

        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        multiRenderer.setXLabels(0);
        multiRenderer.setYTitle("Percentage (%)");
        multiRenderer.setAxisTitleTextSize(34);
        multiRenderer.setLabelsTextSize(24);
        multiRenderer.setZoomButtonsVisible(false);

        multiRenderer.setPanEnabled(false, false);
        multiRenderer.setClickEnabled(false);
        multiRenderer.setZoomEnabled(false, false);
        multiRenderer.setShowGrid(true);
        multiRenderer.setShowGridY(true);
        multiRenderer.setShowGridX(true);
        multiRenderer.setGridColor(Color.LTGRAY);
        multiRenderer.setShowLegend(false);
        multiRenderer.setShowGrid(false);
        multiRenderer.setZoomEnabled(false);
        multiRenderer.setExternalZoomEnabled(false);
        multiRenderer.setAntialiasing(true);
        multiRenderer.setInScroll(false);
        multiRenderer.setXLabelsAlign(Paint.Align.CENTER);
        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
        multiRenderer.setAxesColor(Color.BLACK);
        multiRenderer.setLabelsColor(Color.BLACK);
        multiRenderer.setXLabelsColor(Color.BLACK);
        multiRenderer.setYLabelsColor(0, Color.BLACK);
        multiRenderer.setYLabels(8);
        multiRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        multiRenderer.setYAxisMax(100);
        multiRenderer.setYAxisMin(0);
        multiRenderer.setXAxisMin(-1);
        multiRenderer.setXAxisMax(graphData.getIndicatorDataMap().size());
        multiRenderer.setBarWidth(200);
        multiRenderer.setBarSpacing(300);
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setBackgroundColor(Color.WHITE);
        multiRenderer.setMarginsColor(Color.WHITE);
        multiRenderer.setApplyBackgroundColor(true);

        multiRenderer.setMargins(new int[]{30, 85, 0, 10});
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
