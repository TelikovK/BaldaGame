package view;

import model.Game;
import view.helpers.CustomActionButton;
import view.helpers.CustomMessageModal;
import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Objects;

public class MainWindow extends JFrame {

    private final GridBagConstraints _gbc = new GridBagConstraints();
    private final StartMenuWidget _startMenu = new StartMenuWidget(this);
    private GameWidget _gameWidget = new GameWidget(this);

    public MainWindow() {
        setTitle("Супер Балда");
        setIconImage(new ImageIcon("./img/icon.png").getImage());
        setSize(new Dimension(1120, 760));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent e) {
               System.exit(0);
           }
        });

        getContentPane().setBackground(GlobalStyles.PRIMARY_COLOR);
        setLayout( new GridBagLayout());

        add(_gameWidget, _gbc);
        add(_startMenu, _gbc);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void runGame(Game game) {
        game.getPlayerList().get(0).addListener(_gameWidget);
        game.getPlayerList().get(1).addListener(_gameWidget);
        _gameWidget.setGame(Objects.requireNonNull(game));
        _gameWidget.setVisible(true);
    }

    public void toStartMenu() {
        _gameWidget.setVisible(false);
        _gameWidget = new GameWidget(this);
        add(_gameWidget, _gbc);
        _startMenu.setVisible(true);
    }
}
