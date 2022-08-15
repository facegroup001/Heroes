package com.facegroup.heroes.Database.Biography.Biographies;

public interface BiographiesDatabaseInitialization {

    void insertAllBiographies();

    boolean getBiographyPerson(int whichPerson);

    void updateBiographyPerson(int whichPerson);

}
