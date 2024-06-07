package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dictionary {

    private List<String> words = new ArrayList<>();
    private List<String> usedWords = new ArrayList<>();

    private Alphabet alphabet;



    public void load(String _filename) throws FileNotFoundException{
        if (!_filename.endsWith(".txt")) throw new IllegalArgumentException("Файл должен быть в формате .txt");
        File file = new File(_filename);
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            words.add(reader.nextLine().toLowerCase());
        }
        reader.close();
    }

    public void addAlphabet(Alphabet _alphabet){
        alphabet = _alphabet;
    }

    public String findWordForLength(int length) {
        if(length == 0) throw new IllegalArgumentException("Не задана длина");
        for (String word : words) {
            if (word.length() == length) return word;
        }
        return null;
    }

    public boolean compareSymbolForAlphabet(Character letter) {
        return alphabet.containsLetter(letter);
    }

    public boolean containsWord(String word) {
        return words.contains(word.toLowerCase());
    }

    public void addWordToDictionary(String word) {
        char[] check = word.toCharArray();
        for(int i = 0; i < word.length(); i++){
            if(!compareSymbolForAlphabet(check[i])) return;
        }
        if(words.contains(word)) return;
        words.add(word);
    }

    public boolean wasFormedBefore(String word) {
        if (usedWords == null) return false;
        return usedWords.contains(word);
    }

    public void addFormedWord(String word) {
        if(!usedWords.contains(word)) {
            usedWords.add(word);
        }
    }

    public List<String> getWords() {
        return Collections.unmodifiableList(words);
    }

    public List<String> getUsedWords() {
        return Collections.unmodifiableList(usedWords);
    }
}

