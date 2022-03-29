package src;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlangDictionary {
  final static private String SLANG_FILE_PATH = "./Data/slang.txt";
  private final int NUMBER_OF_CHOICE = 4;
  private HashMap<String, List<String>> dictionary;
  private HashMap<String, List<String>> dictionaryBackup;
  private List<String> historySearched;
  private Scanner input;

  SlangDictionary() {
    dictionary = new HashMap<String, List<String>>();
    dictionaryBackup = new HashMap<String, List<String>>();
    historySearched = new ArrayList<>();
    input = new Scanner(System.in);
  }

  public void loadSlangDictionary(String filename) {
    try {
      Scanner fileScanner = new Scanner(new File(filename));
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        String[] words = line.split("`");
        if (words.length == 2) {
          String key = words[0];
          String[] values = words[1].split("\\| ");
          if (dictionary.containsKey(key)) {
            List<String> valueList = dictionary.get(key);
            for (String value : values) {
              valueList.add(value);
            }
            dictionary.put(key, valueList);

          } else {
            List<String> valueList = new ArrayList<>();
            for (String value : values) {
              valueList.add(value);
            }
            dictionary.put(key, valueList);
          }
        }
      }
      dictionaryBackup.putAll(dictionary);

    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void findBySlang() {
    System.out.println("Enter a Slang word to search: (ex: SOS, $ ,..) ");
    String inputWord = input.nextLine();
    String word = inputWord.toUpperCase();
    if (dictionary.containsKey(word)) {
      List<String> values = dictionary.get(word);
      for (String value : values) {
        System.out.println(value);
      }
    } else {
      System.out.println("No slang found");
    }
    historySearched.add(inputWord);
  }

  public void findByDefinition() {
    boolean isContained = false;
    System.out.println("Enter a definition to search for: ");
    String definition = input.nextLine();
    String capitalizedDefinition = capitalizeInput(definition);
    for (String key : dictionary.keySet()) {
      List<String> values = dictionary.get(key);
      for (String value : values) {
        if (value.contains(definition) || value.contains(capitalizedDefinition)) {
          System.out.println("\t" + key);
          isContained = true;
        }
      }
    }
    if (isContained == false) {
      System.out.println("No Slang found");
      return;
    }
    historySearched.add(definition);
  }

  public HashMap<String, List<String>> randomSlangFromDictionary(int numberOfSlang) {
    HashMap<String, List<String>> randomSlang = new HashMap<>();
    int randomIndex = 0;
    for (int i = 0; i < numberOfSlang; i++) {
      do {
        randomIndex = new Random().nextInt(dictionary.size());
      } while (randomSlang.containsKey(dictionary.keySet().toArray()[randomIndex]));
      String key = (String) dictionary.keySet().toArray()[randomIndex];
      List<String> values = dictionary.get(key);
      randomSlang.put(key, values);
    }
    return randomSlang;
  }

  public void displayHistorySearch() {
    System.out.println("History search: ");
    if (historySearched.size() == 0) {
      System.out.println("No history found");
    } else {
      for (String word : historySearched) {
        System.out.println(word);
      }
    }
  }

  public void addWord() {
    System.out.println("Enter a Slang word to add: (ex: SOS) ");
    String word = input.nextLine();
    System.out.println("Enter a definition to add: (ex: 'Help me!') ");
    String definition = capitalizeInput(input.nextLine());
    if (dictionary.containsKey(word)) {
      dictionary.get(word).add(definition);
    } else {
      List<String> meanings = new ArrayList<>();
      meanings.add(definition);
      dictionary.put(word, meanings);
    }
    System.out.println("Word added " + word + ":" + definition);
  }

  public void showDictionary() {
    for (String key : dictionary.keySet()) {
      System.out.println(key + ": ");
      List<String> values = dictionary.get(key);
      for (String value : values) {
        System.out.println("\t" + value);
      }
      System.out.println("\n==========================================");
    }
  }

  public void editSlangWord() {
    System.out.println("Enter a Slang word to edit: (ex: SOS) ");
    String word = input.nextLine();
    if (dictionary.containsKey(word)) {
      List<String> meanings = dictionary.get(word); // get all meanings of slang
      displaySlangWord(word); // display all meaning of the word
      int index;
      // Chose option
      do {
        System.out.println("Enter a definition option to edit: (ex: 1,2,3,...) ");
        index = input.nextInt() - 1;
      } while (index < 0 || index >= meanings.size());

      System.out.println("Enter a replacement's definition: (ex: 'Help me!') ");
      input.nextLine();
      String definition = input.nextLine();
      meanings.set(index, capitalizeInput(definition));
      meanings.get(index);
      System.out.println("Word edited " + word + ":" + definition);
    } else {
      System.out.println("No slang found");
    }
  }

  public final void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public String capitalizeInput(String input) {
    input.toLowerCase();
    StringBuffer strbf = new StringBuffer();
    Matcher match = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(input);
    while (match.find()) {
      match.appendReplacement(strbf, match.group(1).toUpperCase() + match.group(2).toLowerCase());
    }
    return match.appendTail(strbf).toString();
  }

  public static void showDictionary(HashMap<String, List<String>> dictionary) {
    for (String key : dictionary.keySet()) {
      System.out.println(key + ": ");
      List<String> values = dictionary.get(key);
      for (String value : values) {
        System.out.println("\t" + value);
      }
    }
  }

  public void displaySlangWord(String key) {
    List<String> values = dictionary.get(key);
    int length = values.size();
    if (length >= 2) {
      System.out.println(key + ": ");
      for (int i = 0; i < length; i++) {
        System.out.println("\t " + (i + 1) + ". " + values.get(i));
      }
    } else {
      System.out.println(key + ": " + "\t " + values.get(0));
    }
  }

  public void deleteSlangWords() {
    System.out.println("Enter a Slang word to delete: (ex: SOS, $ ,..) ");
    String key = input.nextLine().toUpperCase();
    if (dictionary.containsKey(key)) {
      displaySlangWord(key);
      System.out.println("Do you want to delete " + key + "? (y/n)");
      String answer = input.nextLine().toLowerCase();
      if (answer.equals("y")) {
        dictionary.remove(key);
        System.out.println("Word deleted " + key);
      } else {
        System.out.println("Word not deleted");
      }
    } else {
      System.out.println("No slang found");
    }
  }

  public void resetDictionary() throws ClassNotFoundException, IOException {
    dictionary.clear();
    loadSlangDictionaryData(SLANG_FILE_PATH);
    System.out.println("Dictionary reset");
  }

  public void gameFindCorrectDefination() {
    HashMap<String, List<String>> randomSlang = randomSlangFromDictionary(NUMBER_OF_CHOICE);
    int correctAnswerNumber = new Random().nextInt(NUMBER_OF_CHOICE);
    char correctAnswer = (char) (correctAnswerNumber + 65);
    String correctAnswerSlang = (String) randomSlang.keySet().toArray()[correctAnswerNumber];

    System.out.println("Choose correct definition of " + correctAnswerSlang + " : ");
    for (int i = 0; i < NUMBER_OF_CHOICE; i++) {
      String key = (String) randomSlang.keySet().toArray()[i];
      String definition = randomSlang.get(key).get(0);
      System.out.println((char) (i + 65) + ". " + definition);
    }

    String chosenAnswer = input.nextLine().toUpperCase();
    if (chosenAnswer.charAt(0) == correctAnswer) {
      System.out.println("Correct answer!");
    } else {
      System.out.println("Wrong answer!");
      System.out.println("Correct answer is " + correctAnswer + ": " + randomSlang.get(correctAnswerSlang).get(0));
    }
  }

  public void gameFindCorrectSlang(){
    HashMap<String, List<String>> randomSlang = randomSlangFromDictionary(NUMBER_OF_CHOICE);
    int correctAnswerNumber = new Random().nextInt(NUMBER_OF_CHOICE); // random number from 0 to 3
    char correctAnswerKey = (char) (correctAnswerNumber + 65); // A,B,C,D
    String correctAnswerSlang = (String) randomSlang.keySet().toArray()[correctAnswerNumber];
    String correctAnswerDefinition = randomSlang.get(correctAnswerSlang).get(0);
    System.out.println("Choose correct slang of " + correctAnswerDefinition + " : ");
    for(int i = 0; i < NUMBER_OF_CHOICE; i++){
      String key = (String) randomSlang.keySet().toArray()[i];
      System.out.println((char) (i + 65) + ". " + key);
    }
    char chosenAnswer = input.nextLine().toUpperCase().charAt(0);
    if(chosenAnswer == correctAnswerKey){
      System.out.println("Correct answer!");
  }
    else{
      System.out.println("Wrong answer!");
      System.out.println("Correct answer is " + correctAnswerKey + ": " + correctAnswerSlang);
    }
  }

  public void saveDataSlang(String destinationFile){
    try {
      // store to file txt
      FileOutputStream fileOut = new FileOutputStream(destinationFile);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(dictionary);
      out.close();
      fileOut.close();
      System.out.println("Serialized data is saved in " + destinationFile);
    } catch (IOException i) {
      i.printStackTrace();
      System.out.println("Error while saving data, please try again");
    }
  }

  public void loadSlangDictionaryData(String destinationFile) throws IOException, ClassNotFoundException {
    FileInputStream fileIn = new FileInputStream(destinationFile);
    ObjectInputStream in = new ObjectInputStream(fileIn);
    dictionary = (HashMap<String, List<String>>) in.readObject();
    in.close();
    fileIn.close();
  }

  public void finalize() throws Throwable {
    input.close();
  }
}
