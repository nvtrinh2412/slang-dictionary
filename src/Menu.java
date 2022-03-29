package src;

import java.util.Scanner;

public class Menu {
  public static void showMenu() {
    System.out.println("|----------WELCOME TO SLANG DICTIONARY----------  |");
    System.out.println("| 1. Find by Slang                                |");
    System.out.println("| 2. Find by Meaning                              |");
    System.out.println("| 3. Show your history                            |");
    System.out.println("| 4. Add new slang                                |");
    System.out.println("| 5. Edit slang                                   |");
    System.out.println("| 6. Delete slang                                 |");
    System.out.println("| 7. Reset original dictionary                    |");
    System.out.println("| 8. Random Slang                                 |");
    System.out.println("| 9. Game mode 1: Find the meaning of a slang     |");
    System.out.println("| 10. Game mode 2: Find the slang of a definition |");
    System.out.println("| 11. Exit                                        |");  
    System.out.println("|-----------------------------------------------  |");
    System.out.println("Enter your selection: ");

  }

  public static int getOption() {
    Scanner input = new Scanner(System.in);
    int option = 1;
    try {
      option = input.nextInt();
    } catch (Exception e) {
      System.out.println("Please enter a valid option");
      return 0;
    }
    return option;
  }

  public static void handleMenuSelection(SlangDictionary slangDictionary, int option) {
    switch (option) {
      case 0:
        break;
      case 1:
        slangDictionary.findBySlang();
        break;
      case 2:
        slangDictionary.findByDefinition();
        break;
      case 3:
        slangDictionary.displayHistorySearch();
        break;
      case 4:
        slangDictionary.addWord();
        break;
      case 5:
        slangDictionary.editSlangWord();
        break;
      case 6:
        slangDictionary.deleteSlangWords();
        break;
      case 7:
        slangDictionary.resetDictionary();
        break;
      case 8:
        slangDictionary.randomSlangFromDictionary(1);
        break;
      case 9:
        slangDictionary.gameFindCorrectDefination();
        break;
      case 10:
        slangDictionary.gameFindCorrectSlang();
        break;
      case 11:
        System.out.println("Thank you for using our application");
        break;


    }


  }

}
