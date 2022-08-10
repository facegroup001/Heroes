package com.facegroup.heroes.Database.Profile;

public interface ProfileDatabaseInitialization {
    void insertProfile();

    void updateUsername(String username);

    String getUsername();

    void updateImageIndex(int newImageIndex);

    int getImageIndex();

    boolean isUserRegistered();

    void updateIsUserRegistered();

    void updateHasUsedOwnName(boolean usedOwnName);

    boolean hasUsedOwnName();

}
