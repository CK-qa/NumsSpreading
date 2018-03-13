import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Ticket {

    private String year = "2018";
    //private String week;
    private String regularNum1;
    private String regularNum2;
    private String regularNum3;
    private String regularNum4;
    private String regularNum5;
    private String euroNum1;
    private String euroNum2;

    private int size = GrabHtmlContent.getNumbers(GrabHtmlContent.htmlString(year)).size();


    public Ticket(GenerateCSVFromHtmlData generateCSVFromHtmlData) throws IOException {
        for (int i = 0; i < size; i++) {
            this.year = GrabHtmlContent.year;
           // this.week = String.valueOf(GrabHtmlContent.getNumbers(GrabHtmlContent.htmlString(year)).size());
            this.regularNum1 = String.valueOf(GrabHtmlContent.getNumbers(GrabHtmlContent.htmlString(year)).get(i).get(0));
            this.regularNum2 = String.valueOf(GrabHtmlContent.getNumbers(GrabHtmlContent.htmlString(year)).get(i).get(1));
            this.regularNum3 = String.valueOf(GrabHtmlContent.getNumbers(GrabHtmlContent.htmlString(year)).get(i).get(2));
            this.regularNum4 = String.valueOf(GrabHtmlContent.getNumbers(GrabHtmlContent.htmlString(year)).get(i).get(3));
            this.regularNum5 = String.valueOf(GrabHtmlContent.getNumbers(GrabHtmlContent.htmlString(year)).get(i).get(4));
            this.euroNum1 = String.valueOf(GrabHtmlContent.getNumbers(GrabHtmlContent.htmlString(year)).get(i).get(5));
            this.euroNum2 = String.valueOf(GrabHtmlContent.getNumbers(GrabHtmlContent.htmlString(year)).get(i).get(6));
        }
    }

    public String getYear() {
        return GrabHtmlContent.year;
    }

//    public String getWeek() {
//        return week;
//    }

    public String getRegularNum1() {
        return regularNum1;
    }

    public String getRegularNum2() {
        return regularNum2;
    }

    public String getRegularNum3() {
        return regularNum3;
    }

    public String getRegularNum4() {
        return regularNum4;
    }

    public String getRegularNum5() {
        return regularNum5;
    }

    public String getEuroNum1() {
        return euroNum1;
    }

    public String getEuroNum2() {
        return euroNum2;
    }
}
