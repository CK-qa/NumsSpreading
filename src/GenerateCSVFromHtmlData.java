import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateCSVFromHtmlData {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String keys = "YEAR,REG_NUM1,REG_NUM2,REG_NUM3,REG_NUM4,REG_NUM5,EUR_NUM1,EUR_NUM1";

    public static void main(String[] args) throws IOException {
        GenerateCSVFromHtmlData generateCSVFromHtmlData = new GenerateCSVFromHtmlData();
        generateCSVFromHtmlData.writeCsvFile("fileAgrrr");
    }

    private void writeCsvFile(String fileName) throws IOException {
        //Ticket ticket = new Ticket();
        FileWriter fileWriter = null;
        List<Ticket> tickets = new ArrayList();
        for (int i = 0; i < GrabHtmlContent.getNumbers(GrabHtmlContent.htmlString("2018")).size(); i++) {
            tickets.add(new Ticket(this));
        }

        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(keys);

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);
            //Write a new student object list to the CSV file

            for (Ticket t : tickets) {

                fileWriter.append(String.valueOf(t.getYear()));
                appendComma(fileWriter);
//                fileWriter.append(t.getWeek());
//                appendComma(fileWriter);
                fileWriter.append(t.getRegularNum1());
                appendComma(fileWriter);
                fileWriter.append(t.getRegularNum2());
                appendComma(fileWriter);
                fileWriter.append(t.getRegularNum3());
                appendComma(fileWriter);
                fileWriter.append(t.getRegularNum4());
                appendComma(fileWriter);
                fileWriter.append(t.getRegularNum5());
                appendComma(fileWriter);
                fileWriter.append(t.getEuroNum1());
                appendComma(fileWriter);
                fileWriter.append(t.getEuroNum2());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

            System.out.println("CSV file was created successfully !!!");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }
    }

    private void appendComma(FileWriter fileWriter) throws IOException {
        fileWriter.append(COMMA_DELIMITER);
    }
}
