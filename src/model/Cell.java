package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cell {
    List<Cell> neighbours = new ArrayList<>();
    private Character letter = ' ';
    private SelectionState selectionState = SelectionState.NOT_SELECTED;
    public enum SelectionState{ NOT_SELECTED, SELECTED_TO_WRITE_LETTER, SELECTED_TO_CREATE_WORD, BLOCK_SELECTED }

    public void setNeighbours(Cell _cell){this.neighbours.add(_cell);}

    public boolean checkLetterFromNeighbors() {
        for (Cell cell : neighbours) {
            if (cell.isLetter()) return true;
        }
        return false;
    }

    public boolean isNeighbourToCell(Cell cell) {
        return this.neighbours.contains(cell);
    }


    public void setSelectionState(SelectionState state) { selectionState = state; }

    public void setLetter(Character letter) {
        if(selectionState == SelectionState.SELECTED_TO_WRITE_LETTER) {
            this.letter = letter;
        }
    }


    public Character getLetter(){
        return this.letter;
    }

    public boolean isLetter(){
        return !(this.letter == ' ');
    }

    public List<Cell> getNeighbours() {
        return Collections.unmodifiableList(neighbours);
    }

    public SelectionState selectionState() { return selectionState; }

}
