package Test;
import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testExchangePlayerNull() {
        GameField gameField = new StandartGameField(5,5);
        List<Player> playerList = new ArrayList<>();
        Alphabet alphabet = new StandartAlphabet();
        WordCreator wordCreator = new WordCreator(new Dictionary());
        Player player1 = new Player(gameField, alphabet, wordCreator, "test");
        Player player2 = new Player(gameField, alphabet, wordCreator, "test2");
        playerList.add(player1);
        playerList.add(player2);
        Game game = new Game(gameField, playerList, alphabet);
        Player activePlayer = game.exchangePlayer();
        assertEquals(playerList.get(0), activePlayer);
    }

    @Test
    public void testExchangePlayer1to2() {
        GameField gameField = new StandartGameField(5,5);
        List<Player> playerList = new ArrayList<>();
        Alphabet alphabet = new StandartAlphabet();
        WordCreator wordCreator = new WordCreator(new Dictionary());
        Player player1 = new Player(gameField, alphabet, wordCreator, "test");
        Player player2 = new Player(gameField, alphabet, wordCreator, "test2");
        playerList.add(player1);
        playerList.add(player2);
        Game game = new Game(gameField, playerList, alphabet);
        Player activePlayer = game.exchangePlayer();
        assertEquals(playerList.get(0), activePlayer);
        activePlayer = game.exchangePlayer();
        assertEquals(playerList.get(1), activePlayer);

    }

    @Test
    public void testExchangePlayer2to1() {
        GameField gameField = new StandartGameField(5,5);
        List<Player> playerList = new ArrayList<>();
        Alphabet alphabet = new StandartAlphabet();
        WordCreator wordCreator = new WordCreator(new Dictionary());
        Player player1 = new Player(gameField, alphabet, wordCreator, "test");
        Player player2 = new Player(gameField, alphabet, wordCreator, "test2");
        playerList.add(player1);
        playerList.add(player2);
        Game game = new Game(gameField, playerList, alphabet);
        Player activePlayer = game.exchangePlayer();
        assertEquals(playerList.get(0), activePlayer);
        activePlayer = game.exchangePlayer();
        assertEquals(playerList.get(1), activePlayer);
        activePlayer = game.exchangePlayer();
        assertEquals(playerList.get(0), activePlayer);
    }

    @Test
    void checkDetectWinner() throws FileNotFoundException {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        dictionary.addAlphabet(alphabet);
        WordCreator wordCreator = new WordCreator(dictionary);
        Player test = new Player(gameField, alphabet, wordCreator, "test");
        test.selectToCell(1,3);
        test.writeToSelectCell('а');
        test.selectCellToFormatWord(2,0);
        test.selectCellToFormatWord(2,1);
        test.selectCellToFormatWord(2,2);
        test.selectCellToFormatWord(2,3);
        test.selectCellToFormatWord(1,3);
        test.cellSelectionFinished();
        Player test2 = new Player(gameField, alphabet, wordCreator, "test");
        test.selectToCell(0,3);
        test2.writeToSelectCell('к');
        test2.selectCellToFormatWord(2,0);
        test2.selectCellToFormatWord(2,1);
        test2.selectCellToFormatWord(2,2);
        test2.selectCellToFormatWord(2,3);
        test2.selectCellToFormatWord(1,3);
        test2.selectCellToFormatWord(0,3);
        test2.cellSelectionFinished();
        List<Player> playerList = new ArrayList<>();
        playerList.add(test);
        playerList.add(test2);
        Game game = new Game(gameField, playerList, alphabet);
        assertEquals(game.detectWinner(), "Победил игрок: test Со счетом: 6");
    }

    @Test
    void checkDetectWinner2() throws FileNotFoundException {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        dictionary.addAlphabet(alphabet);
        WordCreator wordCreator = new WordCreator(dictionary);
        Player test = new Player(gameField, alphabet, wordCreator, "test");
        Player test2 = new Player(gameField, alphabet, wordCreator, "test");
        test2.selectToCell(1,3);
        test2.writeToSelectCell('а');
        test2.selectCellToFormatWord(2,0);
        test2.selectCellToFormatWord(2,1);
        test2.selectCellToFormatWord(2,2);
        test2.selectCellToFormatWord(2,3);
        test2.selectCellToFormatWord(1,3);
        test2.cellSelectionFinished();

        test.selectToCell(0,3);
        test.writeToSelectCell('к');
        test.selectCellToFormatWord(2,0);
        test.selectCellToFormatWord(2,1);
        test.selectCellToFormatWord(2,2);
        test.selectCellToFormatWord(2,3);
        test.selectCellToFormatWord(1,3);
        test.selectCellToFormatWord(0,3);
        test.cellSelectionFinished();
        List<Player> playerList = new ArrayList<>();
        playerList.add(test);
        playerList.add(test2);
        Game game = new Game(gameField, playerList, alphabet);
        assertEquals(game.detectWinner(), "Победил игрок: test Со счетом: 6");
    }

    @Test
    public void testActivePlayerNoneActive() {
        GameField gameField = new StandartGameField(5, 5);
        List<Player> playerList = new ArrayList<>();
        Alphabet alphabet = new StandartAlphabet();
        WordCreator wordCreator = new WordCreator(new Dictionary());
        Player player1 = new Player(gameField, alphabet, wordCreator, "Player1");
        Player player2 = new Player(gameField, alphabet, wordCreator, "Player2");
        playerList.add(player1);
        playerList.add(player2);
        Game game = new Game(gameField, playerList, alphabet);
        assertNull(game.activePlayer());
    }

    @Test
    public void testActivePlayerFirstActive() {
        GameField gameField = new StandartGameField(5, 5);
        List<Player> playerList = new ArrayList<>();
        Alphabet alphabet = new StandartAlphabet();
        WordCreator wordCreator = new WordCreator(new Dictionary());
        Player player1 = new Player(gameField, alphabet, wordCreator, "Player1");
        Player player2 = new Player(gameField, alphabet, wordCreator, "Player2");
        player1.setActive(true);
        playerList.add(player1);
        playerList.add(player2);
        Game game = new Game(gameField, playerList, alphabet);
        assertEquals(player1, game.activePlayer());
    }

    @Test
    public void testActivePlayerSecondActive() {
        GameField gameField = new StandartGameField(5, 5);
        List<Player> playerList = new ArrayList<>();
        Alphabet alphabet = new StandartAlphabet();
        WordCreator wordCreator = new WordCreator(new Dictionary());
        Player player1 = new Player(gameField, alphabet, wordCreator, "Player1");
        Player player2 = new Player(gameField, alphabet, wordCreator, "Player2");
        player2.setActive(true);
        playerList.add(player1);
        playerList.add(player2);
        Game game = new Game(gameField, playerList, alphabet);
        assertEquals(player2, game.activePlayer());
    }

    @Test
    public void testActivePlayerBothActive() {
        GameField gameField = new StandartGameField(5, 5);
        List<Player> playerList = new ArrayList<>();
        Alphabet alphabet = new StandartAlphabet();
        WordCreator wordCreator = new WordCreator(new Dictionary());
        Player player1 = new Player(gameField, alphabet, wordCreator, "Player1");
        Player player2 = new Player(gameField, alphabet, wordCreator, "Player2");
        player1.setActive(true);
        player2.setActive(true);
        playerList.add(player1);
        playerList.add(player2);
        Game game = new Game(gameField, playerList, alphabet);
        assertEquals(player1, game.activePlayer());
    }

    @Test
    void testCheckFinishGame() {
        GameField gameField = new StandartGameField(5, 5);
        List<Player> playerList = new ArrayList<>();
        Alphabet alphabet = new StandartAlphabet();
        WordCreator wordCreator = new WordCreator(new Dictionary());
        Player player1 = new Player(gameField, alphabet, wordCreator, "test");
        Player player2 = new Player(gameField, alphabet, wordCreator, "test2");
        playerList.add(player1);
        playerList.add(player2);
        Game game = new Game(gameField, playerList, alphabet);
        assertFalse(game.checkFinishGame());
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                gameField.getCells()[i][j].setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
                gameField.getCells()[i][j].setLetter('a');
            }
        }
        assertTrue(game.checkFinishGame());
    }

    @Test
    void checkDetectWinnerNoWinner() throws FileNotFoundException {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        dictionary.addAlphabet(alphabet);
        WordCreator wordCreator = new WordCreator(dictionary);
        Player test = new Player(gameField, alphabet, wordCreator, "test");
        Player test2 = new Player(gameField, alphabet, wordCreator, "test2");

        List<Player> playerList = new ArrayList<>();
        playerList.add(test);
        playerList.add(test2);
        Game game = new Game(gameField, playerList, alphabet);
        assertEquals(game.detectWinner(), "Ничья!!!");
    }

    @Test
    void testGetPlayerList() {
        GameField gameField = new StandartGameField(5, 5);
        Alphabet alphabet = new StandartAlphabet();
        Dictionary dictionary = new Dictionary();
        dictionary.addAlphabet(alphabet);
        WordCreator wordCreator = new WordCreator(dictionary);
        List<Player> playerList = List.of(new Player(gameField, alphabet, wordCreator, "test"), new Player(gameField, alphabet, wordCreator, "test2"));
        Game game = new Game(gameField, playerList, alphabet);
        assertEquals(playerList, game.getPlayerList());
    }



}
