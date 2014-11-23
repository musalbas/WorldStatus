package uk.ac.kcl.worldstatus.app.backend;

import org.json.JSONException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * An API for the World Bank data sets.
 */
public class WorldBankData {

    /**
     * @param country   The country
     * @param indicator The name of a development indicator
     * @return An ArrayList of [x, y] data points.
     */
    public static ArrayList<Float[]> getIndicatorData(String country, String indicator, int fromYear, int toYear) throws IOException, JSONException, ParserConfigurationException, SAXException {
        String XMLString = Utils.getDataFromURL("http://api.worldbank.org/countries/" + country + "/indicators/"
                + indicator + "?per_page=100&date=" + fromYear + ":" + toYear + "&format=xml");
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

    // TODO remove this main method
    public static void main(String[] args) throws IOException, JSONException, ParserConfigurationException, SAXException {
        getIndicatorData("USA", "AG.LND.ARBL.ZS", 1960, 2014);
    }

}
