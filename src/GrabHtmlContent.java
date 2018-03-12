import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.URL;
import java.util.*;

public class GrabHtmlContent {

    private static String url = "https://www.euro-jackpot.net/en/results-archive-";
    static String year;

    public static void main(String[] args) throws IOException {
        GrabHtmlContent grabHtmlContent = new GrabHtmlContent();
        grabHtmlContent.getNumbers(grabHtmlContent.htmlString("2018"));
//        getNumbers(htmlString("2017"));
//        getNumbers(htmlString("2016"));
//        getNumbers(htmlString("2015"));
//        getNumbers(htmlString("2014"));
//        getNumbers(htmlString("2013"));
//        getNumbers(htmlString("2012"));
    }



    public String htmlString(String year) throws IOException {
        GrabHtmlContent.year = year;
        StringBuilder stringBuilder = new StringBuilder();
        String res = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL((url + year)).openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            stringBuilder.append(inputLine);
        }

        in.close();

        int start = String.valueOf(stringBuilder).indexOf("<tbody>");
        int end = String.valueOf(stringBuilder).indexOf("</tbody>");

        String result = String.valueOf(stringBuilder).substring(start, end + 8).replaceAll("\\s{2,}", "");

        BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(year + ".xml")));
        bwr.write(result);
        bwr.flush();
        bwr.close();
        return result;
    }

    public List<List<String>> getNumbers(String xmlString) {
        List<List<String>> list = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlString));
            Document doc = db.parse(is);

            int weeks = doc.getElementsByTagName("ul").getLength();

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            List<String> lst = null;
            System.out.println("\n" + year + " numbers are: ");
            for (int i = 1; i <= weeks; i++) {
                lst = new ArrayList<>();
                for (int j = 1; j <= 5; j++) {
                    XPathExpression expr = xpath.compile("((//ul)[" + String.valueOf(i) + "]/li[@class='ball']/span)[" + j + "]");
                    lst.add(expr.evaluate(doc));
                }

                for (int j = 1; j <= 2; j++) {
                    XPathExpression expr = xpath.compile("((//ul)[" + String.valueOf(i) + "]/li[@class='euro']/span)[" + j + "]");
                    lst.add(expr.evaluate(doc));
                }
                list.add(lst);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (List l : list) {
            System.out.println(l);

        }
        return list;
    }
}