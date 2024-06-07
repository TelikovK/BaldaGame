package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class GameField {

    protected int fieldWidth;
    protected int fieldHeight;

    protected final Cell[][] _cells;

    protected Cell selectCell;

    public GameField(int _fieldWidth, int _fieldHeight){
        fieldHeight = _fieldHeight;
        fieldWidth = _fieldWidth;
        _cells = new Cell[_fieldWidth][_fieldHeight];
        createField(_fieldWidth, _fieldHeight);
    }

    public void createField(int _fieldWidth, int _fieldHeight){
        if((_fieldHeight < 5) || (_fieldWidth < 5) ) throw new IllegalArgumentException("Минимальный размер ширины/высоты поля - 5");
        for (int i = 0; i < _fieldHeight; i++) {
            for (int j = 0; j < _fieldWidth; j++) {
                _cells[i][j] = new Cell();
            }
        }
        addNeighbourKnowledgeForAllCells();
    }

    public void setStartWord(String _startWord){
        for (int i = 0; i < fieldWidth; i++) {
            _cells[fieldHeight / 2][i].setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
            _cells[fieldHeight / 2][i].setLetter(_startWord.charAt(i));
            _cells[fieldHeight / 2][i].setSelectionState(Cell.SelectionState.NOT_SELECTED);
        }
    }

    private void addNeighbourKnowledgeForAllCells(){
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                if (i > 0) _cells[i][j].setNeighbours( _cells[i - 1][j] );
                if (j > 0) _cells[i][j].setNeighbours( _cells[i][j - 1] );
                if (i < fieldHeight - 1) _cells[i][j].setNeighbours( _cells[i + 1][j] );
                if (j < fieldWidth - 1) _cells[i][j].setNeighbours( _cells[i][j + 1] );
            }
        }
    }

    public void selectСellToWrite(int _height, int _width){
        if(selectCell == null && _cells[_height][_width].checkLetterFromNeighbors() && !_cells[_height][_width].isLetter()){
            _cells[_height][_width].setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
            selectCell = _cells[_height][_width];
        }
    }

    public void writeToSelectCell(Character _letter){
        selectCell.setLetter(_letter);
    }

    public void canSelectCell(){
        if(selectCell != null) {
            selectCell.setSelectionState(Cell.SelectionState.NOT_SELECTED);
            selectCell = null;
        }
    }

    public void canSelectCellToWrite(){
        if(selectCell != null) {
            selectCell.setLetter(' ');
            selectCell.setSelectionState(Cell.SelectionState.NOT_SELECTED);
            selectCell = null;
        }
    }

    public boolean containsEmptyCells() {
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                if (!_cells[i][j].isLetter()){
                    return true;
                }
            }
        }
        return false;
    }

    private List<Cell> cellsToFormatingWord = new ArrayList<>();
    public boolean selectCellsToFormatingWord(int _height, int _width){
        if(cellsToFormatingWord.isEmpty() && _cells[_height][_width].checkLetterFromNeighbors() && !cellsToFormatingWord.contains(_cells[_height][_width]) && _cells[_height][_width].isLetter()){
            cellsToFormatingWord.add(_cells[_height][_width]);
            _cells[_height][_width].setSelectionState(Cell.SelectionState.SELECTED_TO_CREATE_WORD);
            return true;
        }
        else if(_cells[_height][_width].checkLetterFromNeighbors() && !cellsToFormatingWord.contains(_cells[_height][_width]) && _cells[_height][_width].isLetter() && _cells[_height][_width].isNeighbourToCell(cellsToFormatingWord.get(cellsToFormatingWord.size()-1))){
            cellsToFormatingWord.add(_cells[_height][_width]);
            _cells[_height][_width].setSelectionState(Cell.SelectionState.SELECTED_TO_CREATE_WORD);
            return true;
        }
        else{
            return false;
        }
    }

    public void canSelectCellsToFormatingWord(){

        for (Cell cell : cellsToFormatingWord) {
            cell.setSelectionState(Cell.SelectionState.NOT_SELECTED);
        }
        cellsToFormatingWord.clear();

    }

    public List<Cell> getCellsToFormatingWord()
    {
        if(!cellsToFormatingWord.contains(selectCell)){
            System.out.print("В выбранные ячейки не входит введенная в текущем ходе, повторите заново");
            // canSelectCellsToFormatingWord();
            return null;
        }
        else {
            return Collections.unmodifiableList(cellsToFormatingWord);
        }
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {return fieldHeight;}

    public Cell[][] getCells() {
        return _cells.clone();
    }

    public Cell getSelectCell() {
        return selectCell;
    }

    public List<Cell> getCellToFormat(){
        return Collections.unmodifiableList(cellsToFormatingWord);
    }
    public Cell getCell(int row, int col) { return _cells[row][col]; }

    //TODO upd
    public void blockCells(){}

    public void clearBlockCells(){
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                if (_cells[i][j].selectionState() == Cell.SelectionState.BLOCK_SELECTED){
                    _cells[i][j].setSelectionState(Cell.SelectionState.NOT_SELECTED);
                }
            }
        }
    }
}
