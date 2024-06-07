package Test;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class GameBuilderTest {

    @Test
    public void testAddPlayer() throws FileNotFoundException {
        GameBuilder gameBuilder = new GameBuilder();
        Dictionary dictionary = new Dictionary();
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        dictionary.addAlphabet(alphabet);
        gameBuilder.addDictionary("dictionary.txt");
        gameBuilder.createField(5, 5, "Легкий");
        gameBuilder.addPlayer("Player1");
        gameBuilder.addPlayer("Player2");

        Game game = gameBuilder.createGame();
        assertEquals(2, game.getPlayerList().size());
        assertEquals("Player1", game.getPlayerList().get(0).getPlayerName());
        assertEquals("Player2", game.getPlayerList().get(1).getPlayerName());
    }

    @Test
    public void testAddPlayerWithNullName() {
        GameBuilder gameBuilder = new GameBuilder();
        assertThrows(IllegalArgumentException.class, () -> {
            gameBuilder.addPlayer(null);
        });
    }

    @Test
    public void testAddPlayerMoreThanTwo() throws FileNotFoundException {
        GameBuilder gameBuilder = new GameBuilder();
        Dictionary dictionary = new Dictionary();
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        dictionary.addAlphabet(alphabet);
        gameBuilder.addDictionary("dictionary.txt");
        gameBuilder.createField(5, 5, "Легкий");
        gameBuilder.addPlayer("Player1");
        gameBuilder.addPlayer("Player2");
        gameBuilder.addPlayer("Player3");

        Game game = gameBuilder.createGame();
        assertEquals(3, game.getPlayerList().size());
        assertEquals("Player1", game.getPlayerList().get(0).getPlayerName());
    }

    @Test
    public void testAddDictionary() throws FileNotFoundException {
        GameBuilder gameBuilder = new GameBuilder();
        gameBuilder.addDictionary("dictionary.txt");
    }

    @Test
    public void testAddAlphabet() throws FileNotFoundException {
        GameBuilder gameBuilder = new GameBuilder();
        gameBuilder.addAlphabet("alphabet.txt", "Средний");
    }

    @Test
    public void testCreateField() throws FileNotFoundException {
        GameBuilder gameBuilder = new GameBuilder();
        Dictionary dictionary = new Dictionary();
        Alphabet alphabet = new StandartAlphabet();
        alphabet.load("alphabet.txt");
        dictionary.addAlphabet(alphabet);
        gameBuilder.addDictionary("dictionary.txt");
        gameBuilder.createField(5, 5, "Легкий");
        gameBuilder.addPlayer("Player1");
        gameBuilder.addPlayer("Player2");

        Game game = gameBuilder.createGame();
        assertNotNull(game.field());
    }

    @Test
    public void testCreateFieldWithSmallDimensions() {
        GameBuilder gameBuilder = new GameBuilder();
        assertThrows(IllegalArgumentException.class, () -> {
            gameBuilder.createField(4, 5, "Легкий");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            gameBuilder.createField(5, 4, "Легкий");
        });
    }

    @Test
    public void testCreateGame() throws FileNotFoundException {
        GameBuilder gameBuilder = new GameBuilder();
        gameBuilder.addDictionary("dictionary.txt");
        gameBuilder.createField(5, 5, "Легкий");
        gameBuilder.addPlayer("Player1");
        gameBuilder.addPlayer("Player2");

        Game game = gameBuilder.createGame();
        assertNotNull(game);
        assertEquals(2, game.getPlayerList().size());
        assertNotNull(game.field());
        assertNotNull(game.alphabet());
    }
}
