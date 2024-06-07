package Test;
import model.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WordCreatorTest {


    @Test
    public void testCreateWord() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        WordCreator wordCreator = new WordCreator(dictionary);
        // Создание ячеек
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();
        Cell cell3 = new Cell();
        cell1.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell1.setLetter('ч');
        cell2.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell2.setLetter('т');
        cell3.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell3.setLetter('о');

        // Создание списка ячеек
        List<Cell> cells = new ArrayList<>();
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);

        // Проверка, что слово создано
        assertEquals("что", wordCreator.createWord(cells));
    }

    @Test
    public void testCreateWord2() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        WordCreator wordCreator = new WordCreator(dictionary);
        // Создание ячеек
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();
        Cell cell3 = new Cell();
        cell1.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell1.setLetter('ч');
        cell2.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell2.setLetter('т');
        cell3.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell3.setLetter('о');

        List<Cell> cells = new ArrayList<>();
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);

        wordCreator.createWord(cells);

        assertNull(wordCreator.createWord(null));
    }

    @Test
    public void testAddWordToDictionary() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        WordCreator wordCreator = new WordCreator(dictionary);
        // Создание ячеек
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();
        Cell cell3 = new Cell();
        cell1.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell1.setLetter('к');
        cell2.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell2.setLetter('т');
        cell3.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell3.setLetter('о');

        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        dictionary.addAlphabet(alphabet);

        List<Cell> cells = new ArrayList<>();
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);

        wordCreator.addWordToDictionary(cells);

        assertTrue(dictionary.getWords().contains("кто"));
        wordCreator.addFormedWordToDictionary("кто");
        assertTrue(dictionary.getUsedWords().contains("кто"));
    }

    @Test
    public void testCheckWordSuccess() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        WordCreator wordCreator = new WordCreator(dictionary);
        // Создание ячеек
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();
        Cell cell3 = new Cell();
        cell1.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell1.setLetter('ч');
        cell2.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell2.setLetter('т');
        cell3.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell3.setLetter('о');

        List<Cell> cells = new ArrayList<>();
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        dictionary.addAlphabet(alphabet);

        dictionary.addWordToDictionary("что");

        WordCreator.WordCheckStatus status = wordCreator.checkWord(cells);
        assertEquals(WordCreator.WordCheckStatus.SUCCESS, status);
    }

    @Test
    public void testCheckWordCurrentLetterDoesNotSelected() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        WordCreator wordCreator = new WordCreator(dictionary);
        WordCreator.WordCheckStatus status = wordCreator.checkWord(null);
        assertEquals(WordCreator.WordCheckStatus.CURRENT_LETTER_DOES_NOT_SELECTED, status);
    }

    @Test
    public void testCheckWordHadDiscoveredEarlier() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        WordCreator wordCreator = new WordCreator(dictionary);
        // Создание ячеек
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();
        Cell cell3 = new Cell();
        cell1.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell1.setLetter('ч');
        cell2.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell2.setLetter('т');
        cell3.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell3.setLetter('о');

        List<Cell> cells = new ArrayList<>();
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);

        dictionary.addFormedWord("что");

        WordCreator.WordCheckStatus status = wordCreator.checkWord(cells);
        assertEquals(WordCreator.WordCheckStatus.WORD_HAD_DISCOVERED_EARLIER, status);
    }

    @Test
    public void testCheckWordDoesNotExist() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        WordCreator wordCreator = new WordCreator(dictionary);
        // Создание ячеек
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();
        Cell cell3 = new Cell();
        cell1.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell1.setLetter('ч');
        cell2.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell2.setLetter('т');
        cell3.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell3.setLetter('а');

        List<Cell> cells = new ArrayList<>();
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);

        WordCreator.WordCheckStatus status = wordCreator.checkWord(cells);
        assertEquals(WordCreator.WordCheckStatus.WORD_DOES_NOT_EXIST, status);
    }

    @Test
    public void testGetResultWord() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        WordCreator wordCreator = new WordCreator(dictionary);
        // Создание ячеек
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();
        Cell cell3 = new Cell();
        cell1.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell1.setLetter('ч');
        cell2.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell2.setLetter('т');
        cell3.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell3.setLetter('о');

        List<Cell> cells = new ArrayList<>();
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);

        wordCreator.createWord(cells);

        assertEquals("что", wordCreator.getResultWord());
    }

}
