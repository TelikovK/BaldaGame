package model;

import java.util.ArrayList;
import java.util.List;

public class WordCreator {

    public enum WordCheckStatus {
        SUCCESS,
        CURRENT_LETTER_DOES_NOT_SELECTED,
        WORD_HAD_DISCOVERED_EARLIER,
        WORD_DOES_NOT_EXIST
    }

    private Dictionary dictionary;

    private String resultWord;

    public WordCreator(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String createWord(List<Cell> _cellsToFormatingWord) {

        StringBuilder word = new StringBuilder();
        if(_cellsToFormatingWord != null) {
            for (Cell cell : _cellsToFormatingWord) {
                word.append(cell.getLetter());
            }
        }
        else{
            return null;
        }

        resultWord = word.toString();
        return word.toString();
    }

    public void addFormedWordToDictionary(String word){dictionary.addFormedWord(word);}

    public void addWordToDictionary(List<Cell> _cellsToFormatingWord){
        StringBuilder word = new StringBuilder();
        for (Cell cell : _cellsToFormatingWord) {
            word.append(cell.getLetter());
        }
        dictionary.addWordToDictionary(word.toString());
    }

    public WordCheckStatus checkWord(List<Cell> _cellsToFormatingWord){

        if(_cellsToFormatingWord == null) return WordCheckStatus.CURRENT_LETTER_DOES_NOT_SELECTED;
        String result = createWord(_cellsToFormatingWord);
        if(!dictionary.containsWord(result.toString()) && _cellsToFormatingWord != null) {return WordCheckStatus.WORD_DOES_NOT_EXIST;}
        if(dictionary.wasFormedBefore(result.toString())){return WordCheckStatus.WORD_HAD_DISCOVERED_EARLIER;}
        return WordCheckStatus.SUCCESS;
    }

    public String getResultWord() {
        return resultWord;
    }
}
