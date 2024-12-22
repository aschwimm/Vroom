import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class HTMLBuilder {
    @SuppressWarnings("CallToPrintStackTrace")
    private static int fileCounter = 0;

    public static void buildHTML() {
        StringBuilder template;
        Scanner scnr = new Scanner(System.in); 
        File templateFile = selectQuote(scnr);
        Map<String, String> templateHash = new LinkedHashMap<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(templateFile))) {
            @SuppressWarnings("unchecked")
            Map<String, String> map = (Map<String, String>) ois.readObject();
            templateHash.putAll(map);
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        template = new StringBuilder();
        template.append("<!DOCTYPE html>\n<html>\n<head>\n<title>Dynamic Table</title>\n</head>\n<body>\n");
        template.append("<table border=\"0\" style=\"width:50%; text-align:left;\">\n");
        template.append("<tr><th>Labels</th><th>Values</th></tr>\n");
        for(Map.Entry<String, String> entry : templateHash.entrySet()) {
            template.append("\t<tr><td>");
            template.append(entry.getKey());
            template.append("</td>\n");
            template.append("\t<td>");
            template.append(entry.getValue());
            template.append("</td></tr>\n");
        }
        template.append("</table></body>\n</html>");

        saveQuote(template.toString());
    }
    private static void saveQuote(String quote) {
        counterInit();
        String fileName = String.format("quotes/HTMLPages/quote_%d.html", fileCounter);
        File file = new File(fileName);
        try(BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.write(quote);
            System.out.println("Quote saved successfully");
        } catch(IOException e) {
            e.printStackTrace();
        }

        fileCounter++;
    }
    private static void counterInit() {
        File quoteDir = new File("quotes/HTMLPages");
        if(!quoteDir.exists()) {
            quoteDir.mkdir();
        }
        File[] quotes = quoteDir.listFiles();
        if(quotes != null) {
            fileCounter = quotes.length;
        }
    }
    private static File selectQuote(Scanner scnr) {
        DisplayQuote.display();
        System.out.println("Enter quote number");
        String input = scnr.nextLine();
        String quote = "quotes/quote_" + input.charAt(0);
        return new File(quote);
    }
}