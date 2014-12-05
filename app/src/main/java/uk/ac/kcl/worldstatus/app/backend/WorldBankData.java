package uk.ac.kcl.worldstatus.app.backend;

import org.json.JSONException;
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

    // Method that takes 5 key value pairs of country and importance.
    // Then spits out the top scoring country


    // 1 = we want places with a low %
    // 2 = we want place with the avg %
    // 3 = we want places with a high %
    public static ArrayList<CountryValue> MasterScoreKeeper = new ArrayList<CountryValue>();

    public WorldBankData() {

    }

    public static void main(String[] args) {
        // use 2012
        // SL.UEM.TOTL.ZS - Unemployment
        // SP.URB.TOTL.ZS - Urban population
        // IC.TAX.TOTL.CP.ZS - Tax Rate
        // AG.LND.FRST.ZS - Forest Area
        // FP.CPI.TOTL.ZG - Inflation
        // SE.SEC.ENRR - secondary school
        // EG.USE.COMM.FO.ZS - Fossil fuel
        // GC.XPN.TOTL.GD.ZS - EXpense

        // dummy test map
        HashMap<String, Integer> testerMap = new HashMap<String, Integer>();
        testerMap.put("SL.UEM.TOTL.ZS", 1);
        testerMap.put("SP.URB.TOTL.ZS", 2);
        testerMap.put("IC.TAX.TOTL.CP.ZS", 3);
        testerMap.put("AG.LND.FRST.ZS", 3);
        testerMap.put("GC.XPN.TOTL.GD.ZS", 3);

        findCountry(testerMap); // this returns an array list of strings.
    }

    /**
     * @param map: key = code for data and value is if that is a l/m/h priority
     * @return Returns an ArrayList of strings with all the countries in order of awesomeness
     */
    public static ArrayList<String> findCountry(HashMap<String, Integer> map) {
        ArrayList<CountryValue> Unemploment = new ArrayList<CountryValue>();
        ArrayList<String> Ids = new ArrayList<String>();
        for (Entry<String, Integer> entry : map.entrySet()) {

            Ids.add(entry.getKey());
        }


        for (String id : Ids) {
            ArrayList<CountryValue> temp = new ArrayList<CountryValue>();
            HashMap<String, Float> mp = null;
            try {
                mp = getIndicatorDataByYear(id, 2012);
            } catch (IOException | SAXException | ParserConfigurationException e) {
                // TODO Auto-generated catch block
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

            System.out.println(cv.getName() + " : " + cv.getScore());
        }


        return places;

    }

    /**
     * Gets indicator data about a country.
     *
     * @param country   The country.
     * @param indicator The name of a development indicator.
     * @return An ArrayList of [x, y] data points.
     */
    public static ArrayList<Float[]> getIndicatorDataByCountry(String country, String indicator, int fromYear, int toYear) throws IOException, JSONException, ParserConfigurationException, SAXException {
        String XMLString = Utils.getDataFromURL("http://api.worldbank.org/countries/" + country + "/indicators/" + indicator + "?per_page=1000&date=" + fromYear + ":" + toYear + "&format=xml");

        XMLString = XMLString.substring(3);
        Document document = Utils.getDocumentFromString(XMLString);

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
