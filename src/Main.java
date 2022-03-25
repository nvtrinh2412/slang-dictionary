package src;
public class Main {
    final static private String SLANG_FILE_PATH = "./Data/slang.txt";
        public static void main(String[] args) {
        System.out.println("Welcome to Slang Dictionary");
        SlangDictionary slangDictionary = new SlangDictionary();
        slangDictionary.loadSlangDictionary(SLANG_FILE_PATH);
        // slangDictionary.findBySlang();
        slangDictionary.deleteSlangWords();
        slangDictionary.resetDictionary();
        slangDictionary.findBySlang();

    }
}