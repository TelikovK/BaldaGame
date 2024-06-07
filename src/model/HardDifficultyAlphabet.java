package model;

public class HardDifficultyAlphabet extends Alphabet{
    public void blockLetters(){
        for(int i =0; i < letters.length(); i++)
        {
            if(Math.random() > 0.5 && (blockLetters.length() < 10)) blockLetters.append(letters.charAt(i));
        }
    }
}
