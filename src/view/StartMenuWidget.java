package view;

import model.GameBuilder;
import model.Player;
import view.helpers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.FileNotFoundException;
import java.util.Objects;

public class StartMenuWidget extends RoundedPanel {

    private final MainWindow _owner;
    private GameBuilder _builder = new GameBuilder();
    private final JTextField _firstPlayerNameField = new CustomTextField();
    private final JTextField _secondPlayerNameField = new CustomTextField();
    private final JLabel _secondPlayerName = new JLabel("Второй игрок");

    private final JLabel _difficultyLevel = new JLabel("Уровень сложности");
    private final JComboBox<String> _difficultiesSelect = new JComboBox<>(new String[]{"Легкий", "Средний", "Сложный"});
    private final JComboBox<String> _fieldSizeSelect = new JComboBox<>(new String[]{"5x5", "6x6", "7x7", "8x8", "9x9"});
    private final JCheckBox _jargonDictionary = new JCheckBox("Жаргонные слова");
    private final JCheckBox _russianTownsDictionary = new JCheckBox("Города России");
    private final JCheckBox _slangDictionary = new JCheckBox("Сленговые слова");
    private final CustomMessageModal _emptyNamesError;
    private final CustomMessageModal _longNamesError;
    private final CustomMessageModal _equalNamesError;
    public StartMenuWidget(MainWindow owner) {
        super(10);
        _owner = Objects.requireNonNull(owner);

        JLabel message1 = new JLabel("Введите имена игроков!");
        message1.setFont(GlobalStyles.HEADER_FONT);
        _emptyNamesError = new CustomMessageModal(_owner, message1);
        CustomActionButton cancelButton1 = new CustomActionButton("ОК");
        cancelButton1.addActionListener(e -> _emptyNamesError.setVisible(false));
        _emptyNamesError.addButton(cancelButton1);

        JLabel message2 = new JLabel("<html>Имена слишком длинные<br>(максимум 20 символов).</html>");
        message2.setFont(GlobalStyles.HEADER_FONT);
        _longNamesError = new CustomMessageModal(_owner, message2);
        CustomActionButton cancelButton2 = new CustomActionButton("ОК");
        cancelButton2.addActionListener(e -> _longNamesError.setVisible(false));
        _longNamesError.addButton(cancelButton2);

        JLabel message3 = new JLabel("Имена не могут быть одинаковыми.");
        message3.setFont(GlobalStyles.HEADER_FONT);
        _equalNamesError = new CustomMessageModal(_owner, message3);
        CustomActionButton cancelButton3 = new CustomActionButton("ОК");
        cancelButton3.addActionListener(e -> _equalNamesError.setVisible(false));
        _equalNamesError.addButton(cancelButton3);

        setPreferredSize(new Dimension(600, 600));
        setBackground(GlobalStyles.SECONDARY_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(15,15,15,15);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel("НОВАЯ ИГРА", SwingConstants.CENTER);
        title.setFont(GlobalStyles.HEADER_FONT);
        add(title, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 1;
        JLabel firstPlayerName = new JLabel("Первый игрок");
        firstPlayerName.setFont(GlobalStyles.HEADER_FONT);
        add(firstPlayerName, constraints);

        constraints.gridx = 1;
        add(_firstPlayerNameField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        _secondPlayerName.setFont(GlobalStyles.HEADER_FONT);
        add(_secondPlayerName, constraints);

        constraints.gridx = 1;
        add(_secondPlayerNameField, constraints);



        constraints.gridx = 0;
        constraints.gridy = 4;
        JLabel fieldSizeLabel = new JLabel("Размер поля");
        fieldSizeLabel.setFont(GlobalStyles.HEADER_FONT);
        add(fieldSizeLabel, constraints);
        constraints.gridx = 1;
        _fieldSizeSelect.setFont(GlobalStyles.MAIN_FONT);
        _fieldSizeSelect.setBackground(GlobalStyles.PRIMARY_COLOR);
        add(_fieldSizeSelect, constraints);
        constraints.gridx = 0;


        constraints.gridy = 5;
        _difficultyLevel.setFont(GlobalStyles.HEADER_FONT);
        _difficultyLevel.setVisible(true);
        add(_difficultyLevel, constraints);

        constraints.gridx = 1;
        _difficultiesSelect.setVisible(true);
        _difficultiesSelect.setFont(GlobalStyles.MAIN_FONT);
        _difficultiesSelect.setBackground(GlobalStyles.PRIMARY_COLOR);
        add(_difficultiesSelect, constraints);
        constraints.gridx = 0;


        JLabel additionalDictionariesLabel = new JLabel("Доп. словари");
        additionalDictionariesLabel.setFont(GlobalStyles.HEADER_FONT);
        constraints.gridy = 6;
        add(additionalDictionariesLabel, constraints);

        _jargonDictionary.setFont(GlobalStyles.MAIN_FONT);
        _jargonDictionary.setBackground(GlobalStyles.SECONDARY_COLOR);
        _russianTownsDictionary.setFont(GlobalStyles.MAIN_FONT);
        _russianTownsDictionary.setBackground(GlobalStyles.SECONDARY_COLOR);
        _slangDictionary.setFont(GlobalStyles.MAIN_FONT);
        _slangDictionary.setBackground(GlobalStyles.SECONDARY_COLOR);

        constraints.gridy = 7;
        add(_jargonDictionary, constraints);
        constraints.gridy = 8;
        add(_russianTownsDictionary, constraints);
        constraints.gridy = 9;
        add(_slangDictionary, constraints);

        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridy = 10;

        CustomActionButton _startButton = new CustomActionButton("СОЗДАТЬ");
        _startButton.addActionListener(e -> {
            try {
                this.onClickStart();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        add(_startButton, constraints);

        setVisible(true);
    }

    private void onClickStart() throws FileNotFoundException {
        _builder = new GameBuilder();
        String first = _firstPlayerNameField.getText();
        String second = _secondPlayerNameField.getText();
        if (second.isEmpty() || first.isEmpty()) {
            _emptyNamesError.setVisible(true);
        } else if (first.length() > 20 || second.length() > 20) {
            _longNamesError.setVisible(true);
        } else if (first.equals(second)) {
            _equalNamesError.setVisible(true);
        }
        else {
            try {
                _builder.addDictionary("./dictionaries/russianNounsWithDefinition.txt");
                //_builder.addDictionary("./dictionaries/customDictionary.txt");
                _builder.addAlphabet("./dictionaries/alphabet.txt", (String) _difficultiesSelect.getSelectedItem());
                if (_jargonDictionary.isSelected())
                    _builder.addDictionary("./dictionaries/jargon.txt");
                if (_russianTownsDictionary.isSelected())
                    _builder.addDictionary("./dictionaries/russianCities.txt");
                if (_slangDictionary.isSelected())
                    _builder.addDictionary("./dictionaries/russianYoungSlang.txt");
            } catch (FileNotFoundException ignored) {
            }
            _builder.createField(Character.getNumericValue(
                    Objects.requireNonNull(_fieldSizeSelect.getSelectedItem()).toString().charAt(0)), Character.getNumericValue(
                    Objects.requireNonNull(_fieldSizeSelect.getSelectedItem()).toString().charAt(0)), (String) _difficultiesSelect.getSelectedItem());
            _builder.addPlayer(first);
            _builder.addPlayer(second);

           // _builder.addDictionary("./dictionaries/customDictionary.txt");
            _owner.runGame(_builder.createGame());

            setVisible(false);
        }
    }
}

