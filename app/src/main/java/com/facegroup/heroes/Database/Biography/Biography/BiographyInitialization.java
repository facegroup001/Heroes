package com.facegroup.heroes.Database.Biography.Biography;

public interface BiographyInitialization {

    void insertBiographySettings();

    void updateBiographyTextStyle(int newFontStyle);

    int getBiographyTextStyle();

    void updateBiographyTextColor(int newTextColor);

    int getBiographyTextColor();

    void updateBiographyTextSize(int newTextSize);

    int getBiographyTextSize();

    void insertBiographyBackgroundMusic();

    void updateBiographyBackgroundMusic(int newBackgroundMusic);

    int getBiographyBackgroundMusic();

}
