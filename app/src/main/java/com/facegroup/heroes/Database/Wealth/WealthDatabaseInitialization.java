package com.facegroup.heroes.Database.Wealth;

public interface WealthDatabaseInitialization {
    void insertWealth();

    void updateCoinCount(int newCoinAmount);

    int getCoinCount();

    void updateHeartCount(int newHeartAmount);

    int getHeartCount();

    void updateEyeCount(int newEyeAmount);

    int getEyeCount();

    void updateCardCount(int newCardAmount);

    int getCardCount();

}
