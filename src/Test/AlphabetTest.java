package Test;
import model.Alphabet;
import model.HardDifficultyAlphabet;
import model.MediumDifficultyAlphabet;
import model.StandartAlphabet;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class AlphabetTest {


    @Test
    public void testContainsLetter1() throws FileNotFoundException {
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        assertTrue(alphabet.containsLetter('а'));
    }

    @Test
    public void testContainsLetter2() throws FileNotFoundException {
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        assertTrue(alphabet.containsLetter('А'));
    }

    @Test
    public void testNoContainsLetter1() throws FileNotFoundException {
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        assertFalse(alphabet.containsLetter('z'));
    }

    @Test
    public void testNoContainsLetter2() throws FileNotFoundException {
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        assertFalse(alphabet.containsLetter('Z'));
    }

    @Test
    public void testNoContainsLetter3() throws FileNotFoundException {
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        assertFalse(alphabet.containsLetter(' '));
    }

    // Тест на блокировку не более 5 случайных букв из алфавита
    @Test
    public void testBlockLettersMediumDifficultyAlphabet() throws FileNotFoundException {
        Alphabet alphabet = new MediumDifficultyAlphabet();
        alphabet.load("alphabet.txt");
        alphabet.blockLetters();
        alphabet.blockLetters();
        String blocked = alphabet.getListBlockLetters();
        assertTrue(blocked.length() <= 5);
    }

    // Тест на блокировку не более 5 случайных букв из алфавита
    @Test
    public void testBlockLettersHardDifficultyAlphabet() throws FileNotFoundException {
        Alphabet alphabet = new HardDifficultyAlphabet();
        alphabet.load("alphabet.txt");
        alphabet.blockLetters();
        alphabet.blockLetters();
        alphabet.blockLetters();
        String blocked = alphabet.getListBlockLetters();
        assertTrue(blocked.length() <= 10);
    }

    // Тест на очистку заблокированных букв
    @Test
    public void testClearBlockLetters() throws FileNotFoundException {
        Alphabet alphabet = new HardDifficultyAlphabet();
        alphabet.load("alphabet.txt");
        alphabet.blockLetters();
        alphabet.clearBlockLetters();
        assertEquals("", alphabet.getListBlockLetters());
    }

    // Тест на получение списка заблокированных букв
    @Test
    public void testGetListBlockLetters() throws FileNotFoundException {
        Alphabet alphabet = new HardDifficultyAlphabet();
        alphabet.load("alphabet.txt");
        alphabet.blockLetters();
        String blocked = alphabet.getListBlockLetters();
        assertNotNull(blocked);
    }

    // Тест на проверку наличия заблокированных букв
    @Test
    public void testContainsBlockLetters() throws FileNotFoundException {
        Alphabet alphabet = new HardDifficultyAlphabet();
        alphabet.load("alphabet.txt");
        alphabet.blockLetters();
        String blocked = alphabet.getListBlockLetters();
        for (char c : blocked.toCharArray()) {
            assertTrue(alphabet.containsBlockLetters(c));
        }
    }
}
