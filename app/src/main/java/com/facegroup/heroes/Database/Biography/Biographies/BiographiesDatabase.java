package com.facegroup.heroes.Database.Biography.Biographies;

import android.content.ContentValues;

public class BiographiesDatabase {

    public static final String TABLE_BIOGRAPHIES = "TABLE_BIOGRAPHIES";

    public static final String DB_PERSON0 = "PERSON_0";
    public static final String DB_PERSON1 = "PERSON_1";
    public static final String DB_PERSON2 = "PERSON_2";
    public static final String DB_PERSON3 = "PERSON_3";
    public static final String DB_PERSON4 = "PERSON_4";
    public static final String DB_PERSON5 = "PERSON_5";
    public static final String DB_PERSON6 = "PERSON_6";
    public static final String DB_PERSON7 = "PERSON_7";
    public static final String DB_PERSON8 = "PERSON_8";
    public static final String DB_PERSON9 = "PERSON_9";
    public static final String[] DB_PEOPLE = {DB_PERSON0, DB_PERSON1, DB_PERSON2, DB_PERSON3, DB_PERSON4, DB_PERSON5, DB_PERSON6, DB_PERSON7, DB_PERSON8, DB_PERSON9};

    public static final String CREATE_TABLE_BIOGRAPHIES = "CREATE TABLE IF NOT EXISTS " + TABLE_BIOGRAPHIES + " (" +
            DB_PERSON0 + " BOOLEAN , " +
            DB_PERSON1 + " BOOLEAN , " +
            DB_PERSON2 + " BOOLEAN , " +
            DB_PERSON3 + " BOOLEAN , " +
            DB_PERSON4 + " BOOLEAN , " +
            DB_PERSON5 + " BOOLEAN , " +
            DB_PERSON6 + " BOOLEAN , " +
            DB_PERSON7 + " BOOLEAN , " +
            DB_PERSON8 + " BOOLEAN , " +
            DB_PERSON9 + " BOOLEAN )";

    public static ContentValues insertAllBiographies() {
        ContentValues contentValues = new ContentValues();
        for (String dbPerson : DB_PEOPLE) {
            contentValues.put(dbPerson, false);
        }
        return contentValues;
    }

    public static ContentValues updateBiographyPerson(int whichOne) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_PEOPLE[whichOne], true);
        return contentValues;
    }

}
