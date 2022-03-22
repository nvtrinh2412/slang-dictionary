public class Main {
    public static void main(String[] args) {
        SlangDictionary dictionary = new SlangDictionary();
        dictionary.loadSlangDictionary("./Data/slang.txt"); 
        // dictionary.showDictionary();
    //    dictionary.randomSlangFromDictionary(5);
        // dictionary.displaySlangWord("SOS");
        // dictionary.editSlangWord();
        // dictionary.findBySlang();
        dictionary.findByDefinition();
        // dictionary.displayHistorySearch();
        // dictionary.addWord();
    }
}