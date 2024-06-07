package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public abstract class  Alphabet {
    protected String letters;

    protected StringBuilder blockLetters = new StringBuilder();
    public void load(String _filename) throws FileNotFoundException {
        if (!_filename.endsWith(".txt")) throw new IllegalArgumentException("Файл должен быть в формате .txt");
        File file = new File(_filename);
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            letters = reader.nextLine().toLowerCase();
        }
        reader.close();
    }

    public boolean containsLetter(Character character){
        return letters.contains(character.toString().toLowerCase());
    }

    public boolean containsBlockLetters(Character character){
        return blockLetters.toString().contains(character.toString().toLowerCase());
    }

    public void blockLetters(){}

    public void clearBlockLetters(){blockLetters.setLength(0);}

    public String getListBlockLetters(){return blockLetters.toString();}

}
