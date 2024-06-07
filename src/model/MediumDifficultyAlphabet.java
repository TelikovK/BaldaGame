package model;

public class MediumDifficultyAlphabet extends Alphabet{

    public void blockLetters(){
        for(int i =0; i < letters.length(); i++)
        {
            if(Math.random() > 0.8 && (blockLetters.length() < 5)) blockLetters.append(letters.charAt(i));
        }
    }
}
