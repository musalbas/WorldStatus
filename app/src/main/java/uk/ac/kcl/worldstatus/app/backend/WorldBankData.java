package uk.ac.kcl.worldstatus.app.backend;

import android.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * An API for the World Bank data sets.
 */
public class WorldBankData {

    /**
     * @param map: key = code for data and value is if that is a l/m/h priority.
     * @return Returns an ArrayList of strings with all the countries in order of awesomeness.
     */
    public static LegacyDataGrabber findCountry(HashMap<String, Integer> map) throws ParserConfigurationException, SAXException, IOException {
        Integer indicatorNum = map.size();
        HashMap<String, Integer> countryPoints = new HashMap<String, Integer>();
        HashMap<String, Integer> countryIndicatorCount = new HashMap<String, Integer>();

        ArrayList<ArrayList<Float[]>> legacyData = new ArrayList<ArrayList<Float[]>>();

        HashMap<String, HashMap<String, Float>> datasets = new HashMap<String, HashMap<String, Float>>();

        String[] indicators = new String[map.size()];
        int indexIndicators = 0;
        for (Entry<String, Integer> entry : map.entrySet()) {
            indicators[indexIndicators] = entry.getKey();
            indexIndicators++;
        }

        String indicator;
        Integer percentage = 0;
        String country;
        Integer value;
        Integer score;
        Float valueFloat;
        for (Entry<String, Integer> entry : map.entrySet()) {
            indicator = entry.getKey();
            switch (entry.getValue()) {
                case 1:
                    percentage = 0;
                    break;
                case 2:
                    percentage = 50;
                    break;
                case 3:
                    percentage = 100;
                    break;
            }

            Log.v("Downloading data", indicator);
            HashMap<String, Float> indicatorData = getIndicatorDataByYear(indicator, 2012);
            datasets.put(indicator, indicatorData);

            for (Entry<String, Float> datapoint : indicatorData.entrySet()) {
                country = datapoint.getKey();
                valueFloat = datapoint.getValue();
                value = Math.round(valueFloat);

                if (!countryPoints.containsKey(country)) {
                    countryPoints.put(country, 0);
                    countryIndicatorCount.put(country, 0);
                }

                score = 0 - Math.abs(percentage - value);
                countryPoints.put(country, countryPoints.get(country) + score);
                countryIndicatorCount.put(country, countryIndicatorCount.get(country) + 1);
            }
        }

        for (Entry<String, Integer> entry : countryIndicatorCount.entrySet()) {
            if (entry.getValue() != indicatorNum) {
                countryPoints.remove(entry.getKey());
            }
        }

        ArrayList<CountryValue> countryValues = new ArrayList<CountryValue>();
        for (Entry<String, Integer> countryPoint : countryPoints.entrySet()) {
            countryValues.add(new CountryValue(countryPoint.getKey(), countryPoint.getValue()));
        }

        Collections.sort(countryValues, new CustomComparator());

        for (Entry<String, Integer> entry : map.entrySet()) {
            ArrayList<Float[]> tempFloats = new ArrayList<Float[]>();
            for (Entry<String, Float> dataEntry : datasets.get(entry.getKey()).entrySet()) {
                String name = dataEntry.getKey();
                if (name.equals(countryValues.get(0).getName())) {
                    tempFloats.add(new Float[]{(float) 2012, datasets.get(entry.getKey()).get(name)});
                }
            }

            legacyData.add(tempFloats);
        }

        return new LegacyDataGrabber(legacyData, countryValues.get(0).getName(), indicators);
    }

    /**
     * Gets indicator data about a country.
     *
     * @param country   The country.
     * @param indicator The name of a development indicator.
     * @return An ArrayList of [x, y] data points.
     */
    public static ArrayList<Float[]> getIndicatorDataByCountry(String country, String indicator, int fromYear, int toYear) throws IOException, ParserConfigurationException, SAXException {
        String XMLString = null;
        XMLString = Utils.getDataFromURL("http://api.worldbank.org/countries/" + country + "/indicators/" + indicator + "?per_page=1000&date=" + fromYear + ":" + toYear + "&format=xml");

        XMLString = XMLString.substring(3);
        Document document = null;
        document = Utils.getDocumentFromString(XMLString);

        NodeList nodeList = document.getElementsByTagName("wb:data");

        ArrayList<Float[]> dataPoints = new ArrayList<Float[]>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String value = element.getElementsByTagName("wb:value").item(0).getTextContent();
                String year = element.getElementsByTagName("wb:date").item(0).getTextContent();

                if (!value.equals("")) {
                    Float yearFloat = Float.parseFloat(year);
                    Float valueFloat = Float.parseFloat(value);
                    dataPoints.add(new Float[]{yearFloat, valueFloat});
                }
            }
        }

        return dataPoints;
    }

    /**
     * Gets indicator data about all countries for a specific year.
     *
     * @param indicator The name of the development indicator.
     * @param year      The year.
     * @return A country, value hashmap.
     */
    public static HashMap<String, Float> getIndicatorDataByYear(String indicator, int year) throws IOException, SAXException, ParserConfigurationException {
        String XMLString = Utils.getDataFromURL("http://api.worldbank.org/countries/all/indicators/" + indicator + "?per_page=1000&date=" + year + "&format=xml");

        XMLString = XMLString.substring(3);
        Document document = Utils.getDocumentFromString(XMLString);

        NodeList nodeList = document.getElementsByTagName("wb:data");

        HashMap<String, Float> data = new HashMap<String, Float>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String value = element.getElementsByTagName("wb:value").item(0).getTextContent();
                String country = element.getElementsByTagName("wb:country").item(0).getTextContent();

                if (!value.equals("")) {
                    Float valueFloat = Float.parseFloat(value);
                    data.put(country, valueFloat);
                }
            }
        }

        return data;
    }

}
