package menu;
import java.util.Scanner;

public class InputMenu {
    private static void displayMenu() {
        System.out.println("t - Create template");
        System.out.println("n - New quote");
        System.out.println("v - View quote");
        System.out.println("q - Quit");
        
    }
    public static char menu() {
        Scanner scnr = new Scanner(System.in);
        displayMenu();
        System.out.print("Make a selection: ");
        String userInput = scnr.nextLine();
        return userInput.charAt(0);
    }
}
