package uk.ac.kcl.worldstatus.app.backend;

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
     * Keeps track of score for countries.
     */
    public static ArrayList<CountryValue> MasterScoreKeeper = new ArrayList<CountryValue>();

    /**
     * @param map: key = code for data and value is if that is a l/m/h priority.
     * @return Returns an ArrayList of strings with all the countries in order of awesomeness.
     */
    public static LegacyDataGrabber findCountry(HashMap<String, Integer> map) {
        ArrayList<String> Ids = new ArrayList<String>();
        String[] indicators = new String[map.size()];
        int indexIndicators = 0;

        for (Entry<String, Integer> entry : map.entrySet()) {

            indicators[indexIndicators] = entry.getKey(); // saving for later
            indexIndicators++;
            Ids.add(entry.getKey());
        }

        for (String id : Ids) {
            ArrayList<CountryValue> temp = new ArrayList<CountryValue>();
            HashMap<String, Float> mp = null;
            try {
                mp = getIndicatorDataByYear(id, 2012);
            } catch (IOException e) { // TODO deal with exceptions
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }

            for (Map.Entry<String, Float> entry : mp.entrySet()) {
                temp.add(new CountryValue(entry.getKey(), entry.getValue()));
            }

            Collections.sort(temp, new CustomComparator());

            float score = map.get(id);

            for (CountryValue i : temp) {
                boolean found = false;

                for (CountryValue cv : MasterScoreKeeper) {
                    if (cv.getName().equals(i.getName())) // just update the score
                    {
                        cv.setScore(score);
                        found = true;
                    }
                }

                if (found == false) {
                    MasterScoreKeeper.add(i);
                }

                score /= 1.1;
            }
        }

        ArrayList<String> places = new ArrayList<String>();

        Collections.sort(MasterScoreKeeper, new CustomComparatorScore());
        for (CountryValue cv : MasterScoreKeeper) {
            places.add(cv.getName());
        }

        ArrayList<ArrayList<Float[]>> legacyData = new ArrayList<ArrayList<Float[]>>();

        for (Entry<String, Integer> entry : map.entrySet()) {
            try {
                ArrayList<Float[]> tempFloats = new ArrayList<Float[]>();

                for (int k = 2008; k < 2013; ++k) {
                    HashMap<String, Float> data = null;
                    try {
                        data = getIndicatorDataByYear(entry.getKey(), k);
                    } catch (IOException e) { // TODO deal with exceptions
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    }

                    for (Entry<String, Float> dataEntry : data.entrySet()) {
                        String name = dataEntry.getKey();
                        if (name.equals(MasterScoreKeeper.get(0).getName())) {
                            tempFloats.add(new Float[]{(float) k, data.get(name)});
                        }
                    }
                }

                legacyData.add(tempFloats);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return new LegacyDataGrabber(legacyData, MasterScoreKeeper.get(0).getName(), indicators);
    }

    /**
     * Gets indicator data about a country.
     *
     * @param country   The country.
     * @param indicator The name of a development indicator.
     * @return An ArrayList of [x, y] data points.
     */
    public static ArrayList<Float[]> getIndicatorDataByCountry(String country, String indicator, int fromYear, int toYear) {
        String XMLString = null;
        try {
            XMLString = Utils.getDataFromURL("http://api.worldbank.org/countries/" + country + "/indicators/" + indicator + "?per_page=1000&date=" + fromYear + ":" + toYear + "&format=xml");
        } catch (IOException e) {
            //TODO handle when user loses connection
            e.printStackTrace();
        }

        XMLString = XMLString.substring(3);
        Document document = null;
        try {
            document = Utils.getDocumentFromString(XMLString);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

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
