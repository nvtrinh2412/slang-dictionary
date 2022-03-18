import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
        System.out.println(line);
        if (words.length == 2) {
          String key = words[0];
          String[] values = words[1].split("\\| ");
          if (dictionary.containsKey(key)) {
            List<String> valueList = dictionary.get(key);
            for (String value : values) {
              valueList.add(value);
              valueList.add(value);
            }
            dictionary.put(key, valueList);

          } else {
            List<String> valueList = new ArrayList<>();
            for (String value : values) {
              valueList.add(value);
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
}
