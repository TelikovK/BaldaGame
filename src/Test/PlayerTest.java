package Test;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void checkSelectToCell() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        Alphabet alphabet = new StandartAlphabet();
        WordCreator wordCreator = new WordCreator(new Dictionary());
        Player test = new Player(gameField, alphabet, wordCreator, "test");
        test.selectToCell(1,1);
        Cell[][] cells = gameField.getCells();
        Cell cell = cells[1][1];
        assertEquals(cell,gameField.getSelectCell());

    }

    @Test
    void checkSelectToCell2() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        Alphabet alphabet = new StandartAlphabet();
        WordCreator wordCreator = new WordCreator(new Dictionary());
        Player test = new Player(gameField, alphabet, wordCreator, "test");
        test.selectToCell(1,1);
        test.selectToCell(2,1);
        Cell[][] cells = gameField.getCells();
        Cell cell = cells[1][1];
        assertEquals(cell,gameField.getSelectCell());

    }

    @Test
    void checkWriteToSelectCell() throws FileNotFoundException {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        WordCreator wordCreator = new WordCreator(new Dictionary());
        Player test = new Player(gameField, alphabet, wordCreator, "test");
        test.selectToCell(1,1);
        test.writeToSelectCell('а');
        assertEquals('а',gameField.getSelectCell().getLetter());

    }

    @Test
    void checkWriteToSelectCell2() throws FileNotFoundException {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        WordCreator wordCreator = new WordCreator(new Dictionary());
        Player test = new Player(gameField, alphabet, wordCreator, "test");
        test.selectToCell(1,1);
        test.writeToSelectCell('z');
        assertEquals(false,gameField.getSelectCell().isLetter());

    }

    //Не выполнив ход
    @Test
    void checkSelectCellToFormatWord() throws FileNotFoundException {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        WordCreator wordCreator = new WordCreator(new Dictionary());
        Player test = new Player(gameField, alphabet, wordCreator, "test");
        test.selectCellToFormatWord(1,1);
        assertTrue(gameField.getCellToFormat().size() == 0);

    }

    @Test
    void checkСellSelectionFinished() throws FileNotFoundException {
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

        assertFalse(test.isActive());
        assertTrue(test.getScore() == 5);

    }

    @Test
    void checkSetActive() throws FileNotFoundException {
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
        Player test = new Player(new StandartGameField(5, 5), new StandartAlphabet(), new WordCreator(new Dictionary()), "test");

        test.setActive(true);
        assertTrue(test.isActive());

        test.setActive(false);
        assertFalse(test.isActive());
    }

    @Test
    void checkCancelMove() throws FileNotFoundException {
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
        Player test = new Player(new StandartGameField(5, 5), new StandartAlphabet(), new WordCreator(new Dictionary()), "test");

        test.selectToCell(1, 1);
        test.cancelMove();

        assertNull(gameField.getSelectCell());
        assertFalse(test.isEnteredLetter());
    }

    @Test
    void checkSelectCellError() throws FileNotFoundException {
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
        Player test = new Player(gameField,alphabet, new WordCreator(new Dictionary()), "test");

        test.selectToCell(1, 1);
        test.writeToSelectCell('а');
        assertFalse(test.selectToCell(1, 2));
    }

    @Test
    void testGetPlayerName() throws FileNotFoundException {
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
        String expectedName = "Player1";
        Player player = new Player(gameField, new StandartAlphabet(), new WordCreator(new Dictionary()), expectedName);
        assertEquals(expectedName, player.getPlayerName());
    }

    @Test
    void testAddWordToDictionary() throws FileNotFoundException {
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
        test.writeToSelectCell('ы');
        test.selectCellToFormatWord(2,0);
        test.selectCellToFormatWord(2,1);
        test.selectCellToFormatWord(2,2);
        test.selectCellToFormatWord(2,3);
        test.selectCellToFormatWord(1,3);
        test.cellSelectionFinished();
        test.addWordToDictionary();

        assertTrue(dictionary.containsWord("Словы"));
    }

    @Test
    void testGetWords() throws FileNotFoundException {
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

        assertTrue(test.getWords().size() == 1);
    }





}
