package model;

import java.util.List;

public class Game {

    private List<Player> playerList;

    private GameField gameField;

    private Alphabet alphabet;



    public Game(GameField _gamefield, List<Player> _playerList, Alphabet alphabet){
        this.playerList = _playerList;
        this.gameField = _gamefield;
        this.alphabet = alphabet;
    }


    public boolean checkFinishGame(){
     if(!gameField.containsEmptyCells())
     {
         return true;
     }
     return false;
    }

    public String detectWinner() {
        if(playerList.get(0).getScore() > playerList.get(1).getScore()) return "Победил игрок: " + playerList.get(0).getPlayerName() + " Со счетом: " + playerList.get(0).getScore();
        if(playerList.get(0).getScore() < playerList.get(1).getScore()) return "Победил игрок: " + playerList.get(1).getPlayerName() + " Со счетом: " + playerList.get(1).getScore();;
        if(playerList.get(0).getScore() == playerList.get(1).getScore()) return "Ничья!!!";
      return null;
    }


    public void exchangeMove(){
        field().clearBlockCells();
        field().blockCells();
        alphabet().clearBlockLetters();
        alphabet().blockLetters();
        exchangePlayer();
    }

    public Player exchangePlayer() {
        if (!playerList.get(0).isActive() && !playerList.get(1).isActive()) {
            playerList.get(0).setActive(true);
            return playerList.get(0);
        } else if (playerList.get(0).isActive() && !playerList.get(1).isActive()) {
            playerList.get(0).setActive(false);
            playerList.get(1).setActive(true);
            return playerList.get(1);
        } else {
            playerList.get(0).setActive(true);
            playerList.get(1).setActive(false);
            return playerList.get(0);
        }
    }

    public GameField field() { return gameField; }

    public Alphabet alphabet(){return alphabet;}
    public Player activePlayer() {
        if(playerList.get(0).isActive()) return playerList.get(0);
        if(playerList.get(1).isActive()) return playerList.get(1);
        return null;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
