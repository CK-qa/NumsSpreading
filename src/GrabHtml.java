import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

public class GrabHtml {

    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://www.euro-jackpot.net/en/results-archive-2017");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    static String content = null;

    public List<String> results;

    public static String grabHtmlCodeByYear(String year) {
        URLConnection connection = null;
        try {
            connection = new URL(baseUrl + year).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(content);
        return content;
    }

    public static String parseHtml(String year) throws IOException, SAXException, ParserConfigurationException {
        StringBuilder stringBuilder = new StringBuilder();
        String res = "";
        BufferedReader in = new BufferedReader(
                new InputStreamReader(baseUrl.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            stringBuilder.append(inputLine);
        }
        in.close();
        System.out.println("!---------------------------------------!");
        System.out.println(stringBuilder);
        return String.valueOf(stringBuilder).replace("<!DOCTYPE html>", "");
    }

//    private static Document convertStringToDocument(String xmlStr) {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder;
//        Document document = null;
//        try {
//            builder = factory.newDocumentBuilder();
//            document = builder.parse(new InputSource(new StringReader(xmlStr.replaceAll("<!DOCTYPE html>", ""))));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return document;
//    }



        private static void takeNumbers(String year) throws ParserConfigurationException, SAXException, IOException {
        String toParse = parseHtml(year);
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(toParse));

        Document doc = db.parse(is);
        System.out.println(doc);
//        System.out.println(doc.getAttributes().getNamedItem("balls small").getChildNodes().item(1));



//        BufferedInputStream is = new BufferedInputStream(grabHtmlCodeByYear(year));
//
//        File fXmlFile = new File();
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(fXmlFile);
//
//        NodeList nodeList = doc.getElementsByTagName(("small balls"));
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            System.out.println(nodeList.item(i).getChildNodes());
//        }

   //     System.out.println(doc.getElementsByTagName("balls small"));

    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
//        parseHtml("2017");
        takeNumbers("2017");
    }



}
