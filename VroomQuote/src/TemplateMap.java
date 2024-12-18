import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class TemplateMap {
    private static final String FILE_PATH = "templates/template_map.ser";

    @SuppressWarnings({ "CallToPrintStackTrace", "unchecked" })
    public static Map<String, String> loadMap() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No existing HashMap found. Returning a new one.");
            return new LinkedHashMap<>(); // Return an empty HashMap if the file doesn't exist
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new LinkedHashMap<>(); // Return an empty HashMap on error
        }
    }
    @SuppressWarnings("CallToPrintStackTrace")
    public static void saveHashMap(Map<String, String> map) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(map);
            System.out.println("HashMap saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static File getMapFile() {
        return new File(FILE_PATH);
    }
}
