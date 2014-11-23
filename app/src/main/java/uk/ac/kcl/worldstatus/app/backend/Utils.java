package uk.ac.kcl.worldstatus.app.backend;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Shared utilities for backend.
 */
public class Utils {

    public static String getDataFromURL(String URL) throws IOException {
        java.net.URL url = new URL(URL);
        InputStream inputStream = url.openStream();
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));

        String data = "";
        String buffer;
        while ((buffer = dataInputStream.readLine()) != null) {
            data += buffer;
        }

        return data;
    }

    public static Document getDocumentFromString(String string) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(string));
        Document document = dBuilder.parse(inputSource);

        return document;
    }

}
