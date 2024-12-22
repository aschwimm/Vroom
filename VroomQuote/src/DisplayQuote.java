import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class DisplayQuote {
    @SuppressWarnings({"unchecked", "CallToPrintStackTrace"})
    public static void display() {
        File quoteDir = new File("quotes/");
        File[] quotes = quoteDir.listFiles((file) -> file.isFile());
        for(File quote : quotes) {
            System.out.println("\n" + quote + "\n---------");
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(quote))) {
                Map<String, String> map = (Map<String, String>) ois.readObject();
                for(Map.Entry<String, String> item : map.entrySet()) {
                    System.out.print(item.getKey() + ": " + item.getValue() + "\n");
                }
            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
