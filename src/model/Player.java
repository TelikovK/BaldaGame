package model;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Objects;

public class Player {
    private String playerName;
    private GameField gameField;
    private Alphabet alphabet;

    private final ArrayList<String> composedWords = new ArrayList<>();
    int score = 0;

    private WordCreator wordCreator;
    private boolean active = false;

    private boolean enteredLetter = false;



    public Player(GameField _gamefield,Alphabet _alphabet, WordCreator _wordCreator, String _playerName) {
        playerName = _playerName;
        gameField = _gamefield;
        wordCreator = _wordCreator;
        alphabet = _alphabet;
    }

    public void setActive(boolean _active){
        active = _active;
    }

    public boolean selectToCell(int _height, int _width){
        if(!enteredLetter) {
            gameField.selectСellToWrite(_height, _width);
            return true;
        }
        return false;
    }

    public boolean writeToSelectCell(Character _char){
        if(alphabet.containsLetter(_char) && !enteredLetter && !alphabet.containsBlockLetters(_char)) {
            gameField.writeToSelectCell(_char);
            enteredLetter = true;
            return true;
        }
        return false;
    }

    public boolean selectCellToFormatWord(int _height, int _width) {
       if(enteredLetter){
           return (gameField.selectCellsToFormatingWord(_height, _width));
       }
       return false;
    }

    public void cellSelectionFinished(){
        if(enteredLetter){
            if(wordCreator.checkWord(gameField.getCellsToFormatingWord()) == WordCreator.WordCheckStatus.SUCCESS){
                score += wordCreator.getResultWord().length();
                composedWords.add(wordCreator.getResultWord());
                wordCreator.addFormedWordToDictionary(wordCreator.getResultWord());
                enteredLetter = false;
                gameField.canSelectCell();
                gameField.canSelectCellsToFormatingWord();
                fireMoveConfirmed(WordCreator.WordCheckStatus.SUCCESS);
            }
            else{
                fireMoveConfirmed(wordCreator.checkWord(gameField.getCellsToFormatingWord()));
            }

        }
    }

    public void cancelMove(){
        enteredLetter = false;
        gameField.canSelectCellToWrite();
        gameField.canSelectCellsToFormatingWord();
        fireCurrentActionsUndone();
    }

    public void addWordToDictionary(){
        wordCreator.addWordToDictionary(gameField.getCellsToFormatingWord());
    }

    public boolean isActive() {
        return active;
    }

    public int getScore() {
        return score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isEnteredLetter() {
        return enteredLetter;
    }

    public ArrayList<String> getWords() { return new ArrayList<>(composedWords); }

    // --------------------- Player's listeners -------------------------
    private final HashSet<PlayerListener> _listeners = new HashSet<>();

    public void addListener(PlayerListener listener) { _listeners.add(listener); }

    public void fireMoveConfirmed(WordCreator.WordCheckStatus status) {
        _listeners.forEach(listener -> listener.moveConfirmed(status));
    }

    public void fireCurrentActionsUndone() { _listeners.forEach(PlayerListener::currentActionsUndone); }
}

