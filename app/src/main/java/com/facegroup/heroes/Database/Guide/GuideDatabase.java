package com.facegroup.heroes.Database.Guide;

import android.content.ContentValues;

import com.facegroup.heroes.ActivitiesNames;

public class GuideDatabase {

    public static final String DB_GUIDE_HOME = ActivitiesNames.HOME.name();
    public static final String DB_GUIDE_SETTINGS = ActivitiesNames.SETTINGS.name();
    public static final String DB_GUIDE_STORE = ActivitiesNames.STORE.name();
    public static final String DB_GUIDE_BIOGRAPHIES = ActivitiesNames.BIOGRAPHIES.name();
    public static final String DB_GUIDE_BIOGRAPHY = ActivitiesNames.BIOGRAPHY.name();
    public static final String DB_GUIDE_GAMES = ActivitiesNames.GAMES.name();
    public static final String DB_GUIDE_PICTURE_GAME_LEVELS = ActivitiesNames.PICTURE_GAME_LEVELS.name();

    public static final String TABLE_GUIDE = "TABLE_GUIDE";
    public static final String CREATE_TABLE_GUIDE = "CREATE TABLE IF NOT EXISTS " + TABLE_GUIDE + " ( " +
            DB_GUIDE_HOME + " BOOLEAN, " +
            DB_GUIDE_SETTINGS + " BOOLEAN, " +
            DB_GUIDE_STORE + " BOOLEAN, " +
            DB_GUIDE_BIOGRAPHIES + " BOOLEAN, " +
            DB_GUIDE_BIOGRAPHY + " BOOLEAN, " +
            DB_GUIDE_GAMES + " BOOLEAN, " +
            DB_GUIDE_PICTURE_GAME_LEVELS + " BOOLEAN " +
            " )";


    public static ContentValues insertAllGuide() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_GUIDE_HOME, true);
        contentValues.put(DB_GUIDE_SETTINGS, true);
        contentValues.put(DB_GUIDE_STORE, true);
        contentValues.put(DB_GUIDE_BIOGRAPHIES, true);
        contentValues.put(DB_GUIDE_BIOGRAPHY, true);
        contentValues.put(DB_GUIDE_GAMES, true);
        contentValues.put(DB_GUIDE_PICTURE_GAME_LEVELS, true);
        return contentValues;
    }

    public static ContentValues putHome(boolean isAvailable) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_GUIDE_HOME, isAvailable);
        return contentValues;
    }

    public static ContentValues putSetting(boolean isAvailable) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_GUIDE_SETTINGS, isAvailable);
        return contentValues;
    }

    public static ContentValues putStore(boolean isAvailable) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_GUIDE_STORE, isAvailable);
        return contentValues;
    }

    public static ContentValues putBiographies(boolean isAvailable) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_GUIDE_BIOGRAPHIES, isAvailable);
        return contentValues;
    }

    public static ContentValues putBiography(boolean isAvailable) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_GUIDE_BIOGRAPHY, isAvailable);
        return contentValues;
    }

    public static ContentValues putGames(boolean isAvailable) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_GUIDE_GAMES, isAvailable);
        return contentValues;
    }

    public static ContentValues putPictureGameLevels(boolean isAvailable) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_GUIDE_PICTURE_GAME_LEVELS, isAvailable);
        return contentValues;
    }


}
