package Test;
import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameFieldTest {

    @Test
    void checkHeightAndWidth() {
        int Height = 5;
        int Width = 5;
        GameField gameField = new StandartGameField(Height, Width);

        assertEquals(Height, gameField.getFieldHeight());
        assertEquals(Width, gameField.getFieldWidth());
    }

    @Test
    void checkFieldSetStartWord() {
        int initHeight = 5;
        int initWidth = 5;
        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "пятак";
        gameField.setStartWord(word);
        char[] expectedChars = word.toLowerCase().toCharArray();

        Cell[][] cells = gameField.getCells();

        for (int i = 0; i < initWidth; i++) {
            Assertions.assertEquals(expectedChars[i], cells[initHeight / 2][i].getLetter());
        }
    }

    @Test
    void checkFieldSetStartWord2() {
        int initHeight = 11;
        int initWidth = 11;
        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "приветствую";
        gameField.setStartWord(word);
        char[] expectedChars = word.toLowerCase().toCharArray();

        Cell[][] cells = gameField.getCells();

        for (int i = 0; i < initWidth; i++) {
            assertEquals(expectedChars[i], cells[initHeight / 2][i].getLetter());
        }
    }

    @Test
    void checkNeighbours00Cell() {
        int Height = 5;
        int Width = 5;
        GameField field = new StandartGameField(Height, Width);

        Cell[][] cells = field.getCells();
        Cell cell = cells[0][0];
        List<Cell> neighbours = cell.getNeighbours();

        assertTrue(neighbours.contains(cells[0][1]));
        assertTrue(neighbours.contains(cells[1][0]));
        assertFalse(neighbours.contains(cells[2][1]));
        assertFalse(neighbours.contains(cells[3][0]));
        assertEquals(2, neighbours.size());
    }

    @Test
    void checkNeighbours40Cell() {
        int Height = 5;
        int Width = 5;
        GameField field = new StandartGameField(Height, Width);

        Cell[][] cells = field.getCells();
        Cell cell = cells[4][0];
        List<Cell> neighbours = cell.getNeighbours();

        assertTrue(neighbours.contains(cells[3][0]));
        assertTrue(neighbours.contains(cells[4][1]));
        assertFalse(neighbours.contains(cells[3][1]));
        assertEquals(2, neighbours.size());
    }

    @Test
    void checkNeighbours44Cell() {
        int Height = 5;
        int Width = 5;
        GameField field = new StandartGameField(Height, Width);

        Cell[][] cells = field.getCells();
        Cell cell = cells[4][4];
        List<Cell> neighbours = cell.getNeighbours();

        assertTrue(neighbours.contains(cells[3][4]));
        assertTrue(neighbours.contains(cells[4][3]));
        assertFalse(neighbours.contains(cells[3][3]));
        assertEquals(2, neighbours.size());
    }

    @Test
    void checkNeighbours04Cell() {
        int Height = 5;
        int Width = 5;
        GameField field = new StandartGameField(Height, Width);

        Cell[][] cells = field.getCells();
        Cell cell = cells[0][4];
        List<Cell> neighbours = cell.getNeighbours();

        assertTrue(neighbours.contains(cells[0][3]));
        assertTrue(neighbours.contains(cells[1][4]));
        assertFalse(neighbours.contains(cells[1][3]));
        assertEquals(2, neighbours.size());
    }

    @Test
    void checkNeighbours33Cell() {
        int Height = 5;
        int Width = 5;
        GameField field = new StandartGameField(Height, Width);

        Cell[][] cells = field.getCells();
        Cell cell = cells[3][3];
        List<Cell> neighbours = cell.getNeighbours();

        assertEquals(4, neighbours.size());
        assertTrue(neighbours.contains(cells[2][3]));
        assertTrue(neighbours.contains(cells[3][4]));
        assertTrue(neighbours.contains(cells[4][3]));
        assertTrue(neighbours.contains(cells[3][2]));
    }

    @Test
    void checkSelectCell() {
        int initHeight = 5;
        int initWidth = 5;
        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "Балда";
        gameField.setStartWord(word);
        Cell[][] cells = gameField.getCells();
        Cell cell = cells[1][1];

        gameField.selectСellToWrite(1,1);
        assertEquals(cell, gameField.getSelectCell());
    }

    @Test
    void checkSelectCell2() {
        int initHeight = 5;
        int initWidth = 5;
        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "Балда";
        gameField.setStartWord(word);

        gameField.selectСellToWrite(0,0);
        assertEquals(null, gameField.getSelectCell());
    }

    @Test
    void checkSelectCell3() {
        int initHeight = 5;
        int initWidth = 5;
        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "Балда";
        gameField.setStartWord(word);
        Cell[][] cells = gameField.getCells();
        Cell cell = cells[1][1];

        gameField.selectСellToWrite(1,1);
        gameField.selectСellToWrite(1,2);
        assertEquals(cell, gameField.getSelectCell());
    }

    @Test
    void checkSelectCell4() {
        int initHeight = 5;
        int initWidth = 5;
        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "Балда";
        gameField.setStartWord(word);
        Cell[][] cells = gameField.getCells();
        Cell cell = cells[1][2];

        gameField.selectСellToWrite(1,1);
        gameField.canSelectCellToWrite();
        gameField.selectСellToWrite(1,2);
        assertEquals(cell, gameField.getSelectCell());
    }

    @Test
    void checkWriteToSelectCell() {
        int initHeight = 5;
        int initWidth = 5;
        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "Балда";
        gameField.setStartWord(word);
        Cell[][] cells = gameField.getCells();
        Cell cell = cells[1][1];

        gameField.selectСellToWrite(1,1);
        gameField.writeToSelectCell('а');
        assertEquals('а', cell.getLetter());
    }

    @Test
    void checkIfFieldHasEmptyCellsTrue() {
        int initHeight = 5;
        int initWidth = 5;
        String word = "слово";

        GameField gameField = new StandartGameField(initHeight, initWidth);
        gameField.setStartWord(word);
        assertTrue(gameField.containsEmptyCells());
    }

    @Test
    void checkIfFieldHasEmptyCellsFalse() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        for (Cell[] cells1 : gameField.getCells()) {
            for (Cell cell : cells1) {
                cell.setLetter('a');
            }
        }

       assertTrue(gameField.containsEmptyCells());
    }

    @Test
    void checkselectCellsToFormatingWord() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        gameField.selectСellToWrite(1,3);
        gameField.writeToSelectCell('а');
        gameField.selectCellsToFormatingWord(2,0);
        gameField.selectCellsToFormatingWord(2,1);
        gameField.selectCellsToFormatingWord(2,2);
        gameField.selectCellsToFormatingWord(2,3);
        gameField.selectCellsToFormatingWord(1,3);
        List<Cell> actual = gameField.getCellToFormat();
        Cell[][] cells = gameField.getCells();
        List<Cell> expected = new ArrayList<>();
        expected.add(cells[2][0]);
        expected.add(cells[2][1]);
        expected.add(cells[2][2]);
        expected.add(cells[2][3]);
        expected.add(cells[1][3]);

        assertEquals(expected,actual);
    }

    //Не является соседом
    @Test
    void checkselectCellsToFormatingWord2() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        gameField.selectСellToWrite(1,3);
        gameField.writeToSelectCell('а');
        gameField.selectCellsToFormatingWord(2,0);
        gameField.selectCellsToFormatingWord(2,1);
        gameField.selectCellsToFormatingWord(2,2);
        gameField.selectCellsToFormatingWord(2,3);
        gameField.selectCellsToFormatingWord(1,3);
        gameField.selectCellsToFormatingWord(2,3);

        List<Cell> actual = gameField.getCellToFormat();
        Cell[][] cells = gameField.getCells();
        List<Cell> expected = new ArrayList<>();
        expected.add(cells[2][0]);
        expected.add(cells[2][1]);
        expected.add(cells[2][2]);
        expected.add(cells[2][3]);
        expected.add(cells[1][3]);

        assertEquals(expected,actual);
    }

    //Пустая
    @Test
    void checkselectCellsToFormatingWord3() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        gameField.selectСellToWrite(1,3);
        gameField.writeToSelectCell('а');
        gameField.selectCellsToFormatingWord(2,0);
        gameField.selectCellsToFormatingWord(2,1);
        gameField.selectCellsToFormatingWord(2,2);
        gameField.selectCellsToFormatingWord(2,3);
        gameField.selectCellsToFormatingWord(1,3);
        gameField.selectCellsToFormatingWord(0,3);

        List<Cell> actual = gameField.getCellToFormat();
        Cell[][] cells = gameField.getCells();
        List<Cell> expected = new ArrayList<>();
        expected.add(cells[2][0]);
        expected.add(cells[2][1]);
        expected.add(cells[2][2]);
        expected.add(cells[2][3]);
        expected.add(cells[1][3]);

        assertEquals(expected,actual);
    }

    //Попытка повторного выбора
    @Test
    void checkselectCellsToFormatingWord4() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        gameField.selectСellToWrite(1,3);
        gameField.writeToSelectCell('а');
        gameField.selectCellsToFormatingWord(2,0);
        gameField.selectCellsToFormatingWord(2,1);
        gameField.selectCellsToFormatingWord(2,2);
        gameField.selectCellsToFormatingWord(2,3);
        gameField.selectCellsToFormatingWord(1,3);
        gameField.selectCellsToFormatingWord(2,3);

        List<Cell> actual = gameField.getCellToFormat();
        Cell[][] cells = gameField.getCells();
        List<Cell> expected = new ArrayList<>();
        expected.add(cells[2][0]);
        expected.add(cells[2][1]);
        expected.add(cells[2][2]);
        expected.add(cells[2][3]);
        expected.add(cells[1][3]);

        assertEquals(expected,actual);
    }

    //Содержит выбранную
    @Test
    void checkGetCellsToFormatingWord() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        gameField.selectСellToWrite(1,3);
        gameField.writeToSelectCell('а');
        gameField.selectCellsToFormatingWord(2,0);
        gameField.selectCellsToFormatingWord(2,1);
        gameField.selectCellsToFormatingWord(2,2);
        gameField.selectCellsToFormatingWord(2,3);
        gameField.selectCellsToFormatingWord(1,3);

        List<Cell> actual = gameField.getCellsToFormatingWord();
        Cell[][] cells = gameField.getCells();
        List<Cell> expected = new ArrayList<>();
        expected.add(cells[2][0]);
        expected.add(cells[2][1]);
        expected.add(cells[2][2]);
        expected.add(cells[2][3]);
        expected.add(cells[1][3]);

        assertEquals(expected,actual);
    }

    //Не содержит выбранную
    @Test
    void checkGetCellsToFormatingWord2() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "слово";
        gameField.setStartWord(word);
        gameField.selectСellToWrite(1,3);
        gameField.writeToSelectCell('а');
        gameField.selectCellsToFormatingWord(2,0);
        gameField.selectCellsToFormatingWord(2,1);
        gameField.selectCellsToFormatingWord(2,2);
        gameField.selectCellsToFormatingWord(2,3);
        gameField.selectCellsToFormatingWord(2,4);

        List<Cell> actual = gameField.getCellsToFormatingWord();
        Cell[][] cells = gameField.getCells();

        assertEquals(null,actual);
    }

    @Test
    void checkWriteToSelectCellSelected() {
        int initHeight = 5;
        int initWidth = 5;
        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "пятак";
        gameField.setStartWord(word);
        Cell[][] cells = gameField.getCells();
        Cell cell = cells[1][1];

        gameField.selectСellToWrite(1, 1);
        gameField.writeToSelectCell('а');
        assertEquals('а', cell.getLetter());
    }

    // Проверка выбора ячейки для формирования слова
    @Test
    void checkSelectCellsToFormatingWord() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "пятак";
        gameField.setStartWord(word);
        gameField.selectСellToWrite(1, 3);
        gameField.writeToSelectCell('а');

        assertTrue(gameField.selectCellsToFormatingWord(2, 3));
    }

    // Проверка выбора ячейки для формирования слова, когда ячейка уже выбрана
    @Test
    void checkSelectCellsToFormatingWordAlreadySelected() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "пятак";
        gameField.setStartWord(word);
        gameField.selectСellToWrite(1, 3);
        gameField.writeToSelectCell('а');
        gameField.selectCellsToFormatingWord(2, 3);

        assertFalse(gameField.selectCellsToFormatingWord(2, 3));
    }

    // Проверка выбора ячейки для формирования слова, когда ячейка не является соседом предыдущей выбранной ячейки
    @Test
    void checkSelectCellsToFormatingWordNotNeighbor() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "пятак";
        gameField.setStartWord(word);
        gameField.selectСellToWrite(1, 3);
        gameField.writeToSelectCell('а');
        gameField.selectCellsToFormatingWord(2, 3);

        assertFalse(gameField.selectCellsToFormatingWord(3, 3));
    }

    // Проверка метода canSelectCell()
    @Test
    void checkCanSelectCell() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "пятак";
        gameField.setStartWord(word);
        gameField.selectСellToWrite(1, 3);

        gameField.canSelectCell();
        assertNull(gameField.getSelectCell());
    }

    // Проверка метода canSelectCellToWrite()
    @Test
    void checkCanSelectCellToWrite() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "пятак";
        gameField.setStartWord(word);
        gameField.selectСellToWrite(1, 3);

        gameField.canSelectCellToWrite();
        assertNull(gameField.getSelectCell());
    }


    // Проверка метода getCellsToFormatingWord(), когда выбранная ячейка не входит в список ячеек для формирования слова
    @Test
    void checkGetCellsToFormatingWordWithoutSelectedCell() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new StandartGameField(initHeight, initWidth);
        String word = "пятак";
        gameField.setStartWord(word);
        gameField.selectСellToWrite(1, 3);
        gameField.writeToSelectCell('а');
        gameField.selectCellsToFormatingWord(2, 3);

        gameField.selectСellToWrite(2, 2);
        gameField.writeToSelectCell('а');

        assertNull(gameField.getCellsToFormatingWord());
    }

    // Проверка блокировки ячеек на поле
    @Test
    void checkBlockCells() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new MediumDifficultyGameField(initHeight, initWidth);
        String word = "пятак";
        gameField.setStartWord(word);
        gameField.blockCells();

        boolean blockedCellExists = false;
        for (int i = 0; i < initHeight; i++) {
            for (int j = 0; j < initWidth; j++) {
                if (gameField.getCell(i, j).selectionState() == Cell.SelectionState.BLOCK_SELECTED) {
                    blockedCellExists = true;
                    break;
                }
            }
            if (blockedCellExists) {
                break;
            }
        }
        assertTrue(blockedCellExists);
    }

    @Test
    void checkSelectCellToWrite() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new MediumDifficultyGameField(initHeight, initWidth);
        String word = "пятак";
        gameField.setStartWord(word);
        gameField.blockCells();

        boolean foundBlockedCell = false;
        boolean foundUnblockedCell = false;

        // Проходим по всем ячейкам
        for (int i = 0; i < initHeight; i++) {
            for (int j = 0; j < initWidth; j++) {

                if(gameField.getCell(i, j).selectionState() == Cell.SelectionState.BLOCK_SELECTED){
                    gameField.canSelectCell();
                    gameField.selectСellToWrite(i, j);
                    if(gameField.getSelectCell() == null) foundBlockedCell = true;
                }

                if(gameField.getCell(i, j).selectionState() != Cell.SelectionState.BLOCK_SELECTED){
                    gameField.selectСellToWrite(i, j);
                    if(gameField.getSelectCell() == null) foundUnblockedCell = true;
                }
            }
        }

        assertTrue(foundBlockedCell && foundUnblockedCell);
    }

    @Test
    void checkClearBlock() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new MediumDifficultyGameField(initHeight, initWidth);
        String word = "пятак";
        gameField.setStartWord(word);
        gameField.blockCells();
        gameField.clearBlockCells();

        boolean expected = true;

        // Проходим по всем ячейкам
        for (int i = 0; i < initHeight; i++) {
            for (int j = 0; j < initWidth; j++) {

                if(gameField.getCell(i, j).selectionState() == Cell.SelectionState.BLOCK_SELECTED){
                   expected = false;
                }
            }
        }

        assertTrue(expected);
    }


    @Test
    void checkSelectCellToWriteHardField() {
        int initHeight = 5;
        int initWidth = 5;

        GameField gameField = new HardDifficultyGameField(initHeight, initWidth);
        String word = "пятак";
        gameField.setStartWord(word);
        gameField.blockCells();

        boolean foundBlockedCell = false;
        boolean foundUnblockedCell = false;

        // Проходим по всем ячейкам
        for (int i = 0; i < initHeight; i++) {
            for (int j = 0; j < initWidth; j++) {

                if(gameField.getCell(i, j).selectionState() == Cell.SelectionState.BLOCK_SELECTED){
                    gameField.canSelectCell();
                    gameField.selectСellToWrite(i, j);
                    if(gameField.getSelectCell() == null) foundBlockedCell = true;
                }

                if(gameField.getCell(i, j).selectionState() != Cell.SelectionState.BLOCK_SELECTED){
                    gameField.selectСellToWrite(i, j);
                    if(gameField.getSelectCell() == null) foundUnblockedCell = true;
                }
            }
        }

        assertTrue(foundBlockedCell && foundUnblockedCell);
    }

}
