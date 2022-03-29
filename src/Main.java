package src;

import java.io.IOException;

public class Main {
    final static private String SLANG_FILE_PATH = "./Data/slang.txt";

    public static void main(String[] args) throws ClassNotFoundException, IOException {

        int option;
        // Read the slang file
        SlangDictionary slangDictionary = new SlangDictionary();
        slangDictionary.loadSlangDictionaryData(SLANG_FILE_PATH);
        // Show and test features
        do {
            Menu.showMenu();
            option = Menu.getOption();
            Menu.handleMenuSelection(slangDictionary, option);
        } while (option != 11);

        // Save file after exit
        slangDictionary.saveDataSlang(SLANG_FILE_PATH);

    }
}