public class Main {
    final static private String SLANG_FILE_PATH = "./Data/slang.txt";
    final static private String SLANG_FILE_PATH_TEMP = "./Data/slang_temp.txt";

    public static void main(String[] args) {
        SlangDictionary dictionary = new SlangDictionary();
        dictionary.loadSlangDictionary(SLANG_FILE_PATH);
        // dictionary.showDictionary();
        // dictionary.randomSlangFromDictionary(5);
        // dictionary.displaySlangWord("SOS");
        // dictionary.editSlangWord();
        // dictionary.findBySlang();
        // dictionary.findByDefinition();

        // dictionary.displayHistorySearch();
        // dictionary.addWord();
        dictionary.deleteSlangWords();
        dictionary.findBySlang();

    }
}