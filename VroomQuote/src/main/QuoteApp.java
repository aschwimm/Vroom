package main;

import menu.InputMenu;
import render.*;
import template.*;

public class QuoteApp {
    public static void main(String[] args) {
        while (true) { 
            char input = InputMenu.menu();
            switch(input) {
                case 't':
                    TemplateBuilder.buildTemplate();
                    break;
                case 'n':
                    TemplateInput.inputValues();
                    break;
                case 'v':
                    DisplayQuote.display();
                    break;
                case 'q':
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
    
}