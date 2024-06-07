package model;

public class HardDifficultyGameField extends GameField{
    public HardDifficultyGameField(int _fieldWidth, int _fieldHeight) {
        super(_fieldWidth, _fieldHeight);
    }

    public void selectСellToWrite(int _height, int _width){
        if(selectCell == null && _cells[_height][_width].checkLetterFromNeighbors() && !_cells[_height][_width].isLetter() && !(_cells[_height][_width].selectionState() == Cell.SelectionState.BLOCK_SELECTED)){
            _cells[_height][_width].setSelectionState(Cell.SelectionState.SELECTED_TO_WRITE_LETTER);
            selectCell = _cells[_height][_width];
        }
    }

    public void blockCells(){
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                if(_cells[i][j].checkLetterFromNeighbors() && !_cells[i][j].isLetter() ){
                    if(Math.random() > 0.4) _cells[i][j].setSelectionState(Cell.SelectionState.BLOCK_SELECTED);
                }
            }
        }
    }
}
