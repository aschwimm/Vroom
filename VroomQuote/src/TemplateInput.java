import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("CallToPrintStackTrace")
public class TemplateInput {
    private static int fileCounter = 0;
        private static LinkedHashMap<String, String> template = new LinkedHashMap<>();
        
        public static void inputValues() {
            Scanner scnr = new Scanner(System.in);
            File templateFile = new File("templates/template_map.ser");
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(templateFile))) {
                @SuppressWarnings("unchecked")
                Map<String, String> map = (Map<String, String>) ois.readObject();
                template.putAll(map);
            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Input template values.");
            for(Map.Entry<String, String> entry : template.entrySet()) {
                System.out.printf("Enter %s: ", entry.getKey());
                String line = scnr.nextLine();
                template.put(entry.getKey(), line);
            }
            
            saveValues(template);
        }
        public static void saveValues(LinkedHashMap<String, String> template) {
        counterInit();
        String fileName = String.format("quotes/quote_%d", fileCounter);
        File file = new File(fileName);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(template);
            System.out.println("Quote saved successfully");
        } catch(IOException e) {
            e.printStackTrace();
        }

        fileCounter++;
    }
    public static void counterInit() {
        File quoteDir = new File("quotes");
        if(!quoteDir.exists()) {
            quoteDir.mkdir();
        }
        File[] quotes = quoteDir.listFiles();
        if(quotes != null) {
            fileCounter = quotes.length;
        }
    }

}
