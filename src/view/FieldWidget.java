package view;

import model.Cell;
import model.GameField;
import view.helpers.GlobalStyles;
import view.helpers.RoundedPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class FieldWidget extends RoundedPanel {

    private final GameWidget _owner;
    private CellWidget[][] _cells;

    public FieldWidget(GameWidget owner) {
        super(10);
        _owner = Objects.requireNonNull(owner);
        setPreferredSize(new Dimension(600, 600));
        setBackground(GlobalStyles.SECONDARY_COLOR);
        setVisible(false);
    }

    public void initField() {
        GameField field = _owner.getGame().field();
        _cells = new CellWidget[field.getFieldWidth()][field.getFieldHeight()];
        int fieldSize = field.getFieldWidth();
        setLayout(new GridLayout(fieldSize, fieldSize, 4, 4));
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                CellWidget cell = new CellWidget(fieldSize, field.getCell(i, j).getLetter());
                int finalI = i;
                int finalJ = j;
                cell.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (_owner.getGame().field().getSelectCell() != null && !(_owner.getGame().activePlayer().isEnteredLetter())) {
                            _owner.getGame().activePlayer().cancelMove();
                        }
                        if (_owner.getGame().activePlayer().selectToCell(finalI, finalJ)) {
                            Cell modelCell = field.getCell(finalI, finalJ);
                            cell.setSelection(modelCell);
                        }
                        if(_owner.getGame().activePlayer().isEnteredLetter()){
                            Cell modelCell = field.getCell(finalI, finalJ);
                           if(_owner.getGame().activePlayer().selectCellToFormatWord(finalI,finalJ)) _owner.extendSelectionOrder(modelCell.getLetter());
                        }
                        update();
                    }
                });
                cell.addKeyListener(new KeyListener() {
                    public void keyPressed(KeyEvent e) {
                        if (_owner.getGame().activePlayer().writeToSelectCell(e.getKeyChar()))
                            cell.setLetter(field.getCell(finalI, finalJ).getLetter());
                    }
                    public void keyTyped(KeyEvent e) { }
                    public void keyReleased(KeyEvent e) { }
                });
                _cells[i][j] = cell;
                add(cell);
            }
        }
        setVisible(true);
    }

    public void update() {
        GameField field = _owner.getGame().field();
        for (int i = 0; i < field.getFieldHeight(); i++) {
            for (int j = 0; j < field.getFieldWidth(); j++) {
                Cell cell = field.getCell(i, j);
                _cells[i][j].setLetter(cell.getLetter());
                _cells[i][j].setSelection(cell);
            }
        }
    }
}
