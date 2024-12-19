import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TemplateBuilder {
    private static Map<String, String> templateMap;

    @SuppressWarnings("resource")
    public static void buildTemplate() {
        templateMap = TemplateMap.loadMap();
        String line;
        Scanner scnr = new Scanner(System.in); 
            if(templateMap.isEmpty()) {
                System.out.println("Template is empty. Create a template. Enter 'end' to end.");
                while (true) { 
                    line = scnr.nextLine();
                    if(line.equalsIgnoreCase("end")) {
                        break;
                    }
                    templateMap.put(line, null);
                }
                TemplateMap.saveHashMap(templateMap);
            } else {
                System.out.println("Template exists. Override? [y/N]");
                String input = scnr.nextLine().toLowerCase();
                char charInput = input.charAt(0);
                switch(charInput) {
                    case 'y' ->  {
                        templateMap = new LinkedHashMap<>();
                        while (true) { 
                            line = scnr.nextLine();
                            if(line.equalsIgnoreCase("end")) {
                                break;
                            }
                            templateMap.put(line, null);
                        }
                        TemplateMap.saveHashMap(templateMap);
                    }
                    case 'n' ->  {
                        templateMap = TemplateMap.loadMap();
                    }
                    default -> System.out.println("ERROR: Invalid input");
                }
            }
        }
        
    }
