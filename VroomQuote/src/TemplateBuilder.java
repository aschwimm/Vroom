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
            template.append("<!DOCTYPE html>\n")
                    .append("<html>\n<head></head>\n")
                    .append("<body>\n");
            while(true) {
                line = scnr.nextLine();
                if(line.equalsIgnoreCase("end")) {
                    break;
                }
                template.append("\t<div>");
                String placeholder = String.format("{{%s}}", line);
                template.append(placeholder);
                template.append("</div>\n");
            }
            template.append("</body>\n</html>");
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(templateFile))) {
            writer.write(template.toString());
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}