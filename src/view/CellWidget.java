package view;

import model.Cell;
import view.helpers.CustomActionButton;
import view.helpers.GlobalStyles;

import java.awt.*;
import java.util.Objects;

public class CellWidget extends CustomActionButton {

    public CellWidget(int fieldSize, Character initLetter) {
        super();
        int fontSize;
        switch (fieldSize) {
            case 5 -> fontSize = 60;
            case 6 -> fontSize = 55;
            case 7 -> fontSize = 45;
            case 8 -> fontSize = 35;
            default -> fontSize = 30;
        }
        setFont(new Font("Roboto", Font.PLAIN, fontSize));
        this.setLetter(initLetter);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!getBackground().equals(Color.decode("#D0D0D0")) &&
                        !getBackground().equals(Color.decode("#37FF18")) && !getBackground().equals(Color.decode("000000")))
                    setBackground(GlobalStyles.SECONDARY_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!getBackground().equals(Color.decode("#D0D0D0")) &&
                        !getBackground().equals(Color.decode("#37FF18")) && !getBackground().equals(Color.decode("000000")))
                    setBackground(GlobalStyles.PRIMARY_COLOR);
            }
        });
    }

    public void setSelection(Cell state) {
        if(state.selectionState() == Cell.SelectionState.SELECTED_TO_WRITE_LETTER){
            setBackground(Color.decode("#D0D0D0"));
        }
        if(state.selectionState() == Cell.SelectionState.NOT_SELECTED){
            setBackground(GlobalStyles.PRIMARY_COLOR);
        }
        if(state.selectionState() == Cell.SelectionState.BLOCK_SELECTED){
            setBackground(Color.decode("#000000"));
        }
        if(state.selectionState() == Cell.SelectionState.SELECTED_TO_CREATE_WORD){
            setBackground(Color.decode("#37FF18"));
        }
    }

    public void setLetter(Character letter) {
        setText(Objects.requireNonNull(letter).toString());
    }
}
