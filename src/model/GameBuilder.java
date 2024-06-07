package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class GameBuilder {
    private Game game;

    private List<Player> playerList;
    private Dictionary dictionary;
    private Alphabet alphabet;

    private WordCreator wordCreator;

    private GameField gameField;

    public GameBuilder(){
        dictionary = new Dictionary();
        alphabet = new StandartAlphabet();
        playerList = new ArrayList<>();
    }

    public void addPlayer(String _playerName){
        if(_playerName == null) throw new IllegalArgumentException("Имя игрока не указано");
        if(playerList.size() > 2) playerList.clear();
       playerList.add(new Player(gameField,alphabet, new WordCreator(dictionary), _playerName));
    }

    public void addDictionary(String _filename) throws FileNotFoundException{
     dictionary.load(_filename);
    }

    public void addAlphabet(String _filename, String difficulties) throws FileNotFoundException{
        if(Objects.equals(difficulties, "Средний"))alphabet = new MediumDifficultyAlphabet();
        if(Objects.equals(difficulties, "Сложный"))alphabet = new HardDifficultyAlphabet();
        alphabet.load(_filename);
        dictionary.addAlphabet(alphabet);
    }


    public void createField(int _fieldHeight, int _fieldWidth, String difficulties){
        if((_fieldHeight < 5) || (_fieldWidth < 5) ) throw new IllegalArgumentException("Минимальный размер ширины/высоты поля - 5");
        if(Objects.equals(difficulties, "Легкий"))gameField = new StandartGameField( _fieldWidth, _fieldHeight);
        if(Objects.equals(difficulties, "Средний"))gameField = new MediumDifficultyGameField( _fieldWidth, _fieldHeight);
        if(Objects.equals(difficulties, "Сложный"))gameField = new HardDifficultyGameField( _fieldWidth, _fieldHeight);
        gameField.setStartWord(dictionary.findWordForLength(_fieldWidth));
    }

    public Game createGame()  {
        game =  new Game(gameField, playerList, alphabet);
        game.exchangePlayer();
        return game;
    }
}
