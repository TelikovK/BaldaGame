package Test;

import model.Alphabet;
import model.Dictionary;
import model.StandartAlphabet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {

    @Test
    public void testFindWordForLenght() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        String word = "привет";
        assertEquals(word,dictionary.findWordForLength(6));
    }

    @Test
    public void testFindWordForLenght2() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        String word = null;
        assertEquals(word,dictionary.findWordForLength(7));
    }

    @Test
    public void testFindWordForLenght3() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        String word = "здравствуйте";
        assertEquals(word,dictionary.findWordForLength(12));
    }

    @Test
    public void testContainsWord() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        String word = "здравствуйте";
        assertTrue(dictionary.containsWord(word));
    }

    @Test
    public void testNoContainsWord() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        String word = "Почему";
        assertFalse(dictionary.containsWord(word));
    }

    @Test
    public void testNoContainsWord2() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        String word = "здравствуйтет";
        assertFalse(dictionary.containsWord(word));
    }

    @Test
    public void testContainsWord2() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        String word = "ЗДРАВСТВУЙТЕ";
        assertTrue(dictionary.containsWord(word));
    }

    @Test
    public void testContainsWord3() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.load("dictionary.txt");
        String word = "ЗдРаВсТвУЙТЕ";
        assertTrue(dictionary.containsWord(word));
    }

    @Test
    public void testAddWord() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        dictionary.addAlphabet(alphabet);
        dictionary.addWordToDictionary("здравствуйте");
        String word = "ЗдРаВсТвУЙТЕ";
        assertTrue(dictionary.containsWord(word));
    }

    @Test
    public void testAddWord2() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        dictionary.addAlphabet(alphabet);
        dictionary.addWordToDictionary("здравствуйтее");
        String word = "ЗдРаВсТвУЙТЕ";
        assertFalse(dictionary.containsWord(word));
    }

    @Test
    public void testAddWord3() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        dictionary.addAlphabet(alphabet);
        dictionary.addWordToDictionary("здравствуйте");
        dictionary.addWordToDictionary("хорошо");
        String word = "ЗдРаВсТвУЙТЕ";
        assertTrue(dictionary.containsWord(word));
        assertTrue(dictionary.containsWord("Хорошо"));
    }

    @Test
    public void testAddWord4() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        dictionary.addAlphabet(alphabet);
        dictionary.addWordToDictionary("здравствуйте");
        dictionary.addWordToDictionary("хорошо");
        dictionary.addWordToDictionary("здорово");
        String word = "ЗдРаВсТвУЙТЕ";
        assertTrue(dictionary.containsWord(word));
        assertTrue(dictionary.containsWord("Хорошо"));
        assertTrue(dictionary.containsWord("здорово"));
    }

    @Test
    public void testAddWord5() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        dictionary.addAlphabet(alphabet);
        dictionary.addWordToDictionary("Privet");
        assertTrue(dictionary.getWords().size() == 0);
    }

    @Test
    public void testAddWord6() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        dictionary.addAlphabet(alphabet);
        dictionary.addWordToDictionary("Привеt");
        assertTrue(dictionary.getWords().size() == 0);
    }

    @Test
    public void testAddWord7() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        dictionary.addAlphabet(alphabet);
        dictionary.addWordToDictionary("Привет");
        dictionary.addWordToDictionary("Привет");
        assertTrue(dictionary.getWords().size() == 1);
    }

    @Test
    public void testAddWord8() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        dictionary.addAlphabet(alphabet);
        dictionary.addWordToDictionary("Привет");
        dictionary.addWordToDictionary("Привет");
        dictionary.addWordToDictionary("Привет");
        assertTrue(dictionary.getWords().size() == 1);
    }

    @Test
    public void testAddFormedWord() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.addFormedWord("Привет");
        assertTrue(dictionary.getUsedWords().contains("Привет"));
        assertTrue(dictionary.getUsedWords().size() == 1);
    }

    @Test
    public void testAddFormedWord2() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.addFormedWord("Привет");
        dictionary.addFormedWord("Привет");
        assertTrue(dictionary.getUsedWords().contains("Привет"));
        assertTrue(dictionary.getUsedWords().size() == 1);
    }

    @Test
    public void testAddFormedWord3() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.addFormedWord("Привет");
        dictionary.addFormedWord("Привет");
        dictionary.addFormedWord("Дела");
        assertTrue(dictionary.getUsedWords().contains("Привет"));
        assertTrue(dictionary.getUsedWords().contains("Дела"));
        assertTrue(dictionary.getUsedWords().size() == 2);
    }

    @Test
    public void testWasFormedWord() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.addFormedWord("Привет");
        assertTrue(dictionary.wasFormedBefore("Привет"));
    }

    @Test
    public void testWasFormedWord2() throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        dictionary.addFormedWord("Привет");
        assertFalse(dictionary.wasFormedBefore("Приветт"));
    }



}
