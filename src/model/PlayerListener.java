package model;

public interface PlayerListener {
    void moveConfirmed(WordCreator.WordCheckStatus status);
    void currentActionsUndone();
    void moveSkipped();
}
