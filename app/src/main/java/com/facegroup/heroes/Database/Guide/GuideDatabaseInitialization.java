package com.facegroup.heroes.Database.Guide;

public interface GuideDatabaseInitialization {

    void insertAllGuides();

    void updateHomeGuide(boolean isAvailable);

    boolean isHomeGuideAvailable();

    void updateSettingsGuide(boolean isAvailable);

    boolean isSettingsGuideAvailable();

    void updateStoreGuide(boolean isAvailable);

    boolean isStoreGuideAvailable();

    void updateGamesGuide(boolean isAvailable);

    boolean isGamesGuideAvailable();

    void updateBiographiesGuide(boolean isAvailable);

    boolean isBiographiesGuideAvailable();

    void updateBiographyGuide(boolean isAvailable);

    boolean isBiographyGuideAvailable();

    void updatePictureGameSelectionGuide(boolean isAvailable);

    boolean isPictureGameSelectionGuideAvailable();

    boolean isGuideAvailable(String whichGuide);

    void updateGuide(String whichGuide, boolean isAvailable);

    void resetAllGuides();

}
