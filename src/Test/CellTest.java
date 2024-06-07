package Test;
import static org.junit.Assert.*;

import model.Cell;
import org.junit.Test;

import java.util.Optional;

public class CellTest {

    @Test
    public void testSetNeighbours() {
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();
        cell1.setNeighbours(cell2);
        assertTrue(cell1.isNeighbourToCell(cell2));
        assertTrue(cell2.getNeighbours().isEmpty());
    }

    @Test
    public void testSetLetter() {
        Cell cell = new Cell();
        cell.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell.setLetter('A');
        Character actual = 'A';
        Character expected = cell.getLetter();
        assertEquals(actual, expected);
    }

    @Test
    public void testSetLetterWithoutSelection() {
        Cell cell = new Cell();
        cell.setLetter('A');
        Character actual = ' ';
        Character expected = cell.getLetter();
        assertEquals(actual, expected);
    }

    @Test
    public void testCheckLetterFromNeighbors() {
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();
        cell1.setNeighbours(cell2);

        cell2.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell2.setLetter('B');

        assertTrue(cell1.checkLetterFromNeighbors());
    }

    @Test
    public void testCheckLetterFromNeighborsNoLetter() {
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();
        cell1.setNeighbours(cell2);

        assertFalse(cell1.checkLetterFromNeighbors());
    }

    @Test
    public void testIsLetter() {
        Cell cell = new Cell();
        assertFalse(cell.isLetter());

        cell.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        cell.setLetter('C');
        assertTrue(cell.isLetter());
    }

    @Test
    public void testSelectionState() {
        Cell cell = new Cell();
        assertEquals(Cell.SelectionState.NOT_SELECTED, cell.selectionState());

        cell.setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
        assertEquals(Cell.SelectionState.SELECTED_TO_WRITE_LETTER, cell.selectionState());

        cell.setSelectionState(Cell.SelectionState.BLOCK_SELECTED);
        assertEquals(Cell.SelectionState.BLOCK_SELECTED, cell.selectionState());

        cell.setSelectionState(Cell.SelectionState.SELECTED_TO_CREATE_WORD);
        assertEquals(Cell.SelectionState.SELECTED_TO_CREATE_WORD, cell.selectionState());
    }

    @Test
    public void testGetNeighbours() {
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();
        cell1.setNeighbours(cell2);

        assertEquals(1, cell1.getNeighbours().size());
        assertEquals(cell2, cell1.getNeighbours().get(0));
    }
}


