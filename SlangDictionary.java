import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlangDictionary {
  private HashMap<String, List<String>> dictionary;
  private List<String> historySearched;
  private Scanner input;

  SlangDictionary() {
    dictionary = new HashMap<String, List<String>>();
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
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void findBySlang() {
    System.out.println("Enter a word to search for: ");
    String word = input.nextLine();
    if (dictionary.containsKey(word)) {
      List<String> values = dictionary.get(word);
      for (String value : values) {
        System.out.println(value);
      }
    } else {
      System.out.println("No slang found");
    }
  }

  public void findByDefinition() {
    boolean isContained = false;
    System.out.println("Enter a definition to search for: ");
    String definition = capitalizeInput(input.nextLine());
    for (String key : dictionary.keySet()) {
      List<String> values = dictionary.get(key);
      for (String value : values) {
        if (value.contains(definition)) {
          System.out.println("\t" + key);
          isContained = true;
        }
      }
    }
    if (isContained == false) {
      System.out.println("No Slang found");
    }
  }

  public void findByHistory() {
    System.out.println("Enter a word to search for: ");
    String word = input.nextLine();
    if (historySearched.contains(word)) {
      List<String> values = dictionary.get(word);
      for (String value : values) {
        System.out.println(value);
      }
    } else {
      System.out.println("No slang found");
    }
  }

  public void findByHistoryAndDefinition() {
    System.out.println("Enter a definition to search for: ");
    String definition = input.nextLine();
    if (historySearched.contains(definition)) {
      for (String key : dictionary.keySet()) {
        List<String> values = dictionary.get(key);
        if (values.contains(definition)) {
          System.out.println(key);
        }
      }
    } else {
      System.out.println("No slang found");
    }
  }

  public void findByHistoryAndWord() {
    System.out.println("Enter a word to search for: ");
    String word = input.nextLine();
    if (historySearched.contains(word)) {
      List<String> values = dictionary.get(word);
      for (String value : values) {
        System.out.println(value);
      }
    } else {
      System.out.println("No slang found");
    }
  }

  public void addWord(String word, String meaning) {
    if (dictionary.containsKey(word)) {
      dictionary.get(word).add(meaning);
    } else {
      List<String> meanings = new ArrayList<>();
      meanings.add(meaning);
      dictionary.put(word, meanings);
    }
  }

  public List<String> get(String word) {
    return dictionary.get(word);
  }

  public final static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public String capitalizeInput(String input) {
    StringBuffer strbf = new StringBuffer();
    Matcher match = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(input);
    while (match.find()) {
      match.appendReplacement(strbf, match.group(1).toUpperCase() + match.group(2).toLowerCase());
    }
    return match.appendTail(strbf).toString();
  }
}
