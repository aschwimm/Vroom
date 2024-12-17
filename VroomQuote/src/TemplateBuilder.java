import java.io.*;
import java.util.Scanner;

public class TemplateBuilder {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void buildTemplate() {
        File templateFile;
        StringBuilder template;
        try (Scanner scnr = new Scanner(System.in)) {
            templateFile = new File("templates/template.html");
            String line;
            template = new StringBuilder();
            template.append("<!DOCTYPE html>\n<html>\n<head>\n<title>Dynamic Table</title>\n</head>\n<body>\n");
            template.append("<table border=\"1\" style=\"width:50%; text-align:left;\">\n");
            template.append("<tr><th>Column 1</th><th>Column 2</th></tr>\n");
            while(true) {
                line = scnr.nextLine();
                if(line.equalsIgnoreCase("end")) {
                    break;
                }
                template.append("\t<tr><td>");
                String placeholder = String.format("{{%s}}", line);
                template.append(placeholder);
                template.append("</td>\n");
                template.append("\t<td>");
                template.append("{{VALUE}}");
                template.append("</td></tr>\n");

            }
            template.append("</table></body>\n</html>");

        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(templateFile))) {
            writer.write(template.toString());
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}