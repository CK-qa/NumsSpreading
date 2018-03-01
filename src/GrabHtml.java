import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GrabHtml {

    private static String url = "https://www.euro-jackpot.net/en/results-archive-";
    private static String year;

    private static String htmlString(String year) throws IOException {
        GrabHtml.year = year;
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

    public static void main(String[] args) throws IOException {
        getNumbers(htmlString("2018"));
        getNumbers(htmlString("2017"));
        getNumbers(htmlString("2016"));
        getNumbers(htmlString("2015"));
        getNumbers(htmlString("2014"));
        getNumbers(htmlString("2013"));
        getNumbers(htmlString("2012"));

    }

    public static void getNumbers(String xmlRecords) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlRecords));

            Document doc = db.parse(is);
            int s = doc.getElementsByTagName("ul").getLength();
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            List<String> regular2017 = new ArrayList<>();
            List<String> euro2017 = new ArrayList<>();

            System.out.println("\n" + year + " regular numbers are: ");
            for (int i = 1; i <= s; i++) {
                for (int j = 1; j <= 5; j++) {
                    XPathExpression expr = xpath.compile("((//ul)[" + String.valueOf(i) + "]/li[@class='ball']/span)[" + j + "]");
                    regular2017.add(expr.evaluate(doc));
                }
            }
            System.out.println(regular2017);

            System.out.println("\n" + year + " euro numbers are: ");
            for (int i = 1; i <= s; i++) {
                for (int j = 1; j <= 2; j++) {
                    XPathExpression expr = xpath.compile("((//ul)[" + String.valueOf(i) + "]/li[@class='euro']/span)[" + j + "]");
                    euro2017.add(expr.evaluate(doc));
                }
            }
            System.out.println(euro2017);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}