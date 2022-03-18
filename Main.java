public class Main {
    public static void main(String[] args) {
        SlangDictionary dictionary = new SlangDictionary();
        dictionary.loadSlangDictionary("slang.txt"); 
        dictionary.findSlang();   
    }
}