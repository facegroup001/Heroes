package com.facegroup.heroes.Database.Profile;

import android.content.ContentValues;

public class ProfileDatabase {

    public static final String TABLE_PROFILE = "TABLE_PROFILE";
    public static final String DB_USERNAME_PROFILE = "USERNAME";
    public static final String DB_IMAGE_INDEX_PROFILE = "IMAGE_INDEX";
    public static final String DB_IS_USER_REGISTERED_PROFILE = "IS_REGISTERED";
    public static final String DB_HAS_USED_OWN_NAME_PROFILE = "HAS_USED_OWN_NAME";

    public static final String CREATE_TABLE_PROFILE = "CREATE TABLE IF NOT EXISTS " + TABLE_PROFILE + " ( " +
            DB_USERNAME_PROFILE + " TEXT, " +
            DB_IMAGE_INDEX_PROFILE + " INTEGER, " +
            DB_IS_USER_REGISTERED_PROFILE + " BOOLEAN," +
            DB_HAS_USED_OWN_NAME_PROFILE + " BOOLEAN )";

    public static ContentValues putUsername(String username) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_USERNAME_PROFILE, username);
        return contentValues;
    }

    public static ContentValues putImageIndex(int imageIndex) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_IMAGE_INDEX_PROFILE, imageIndex);
        return contentValues;
    }

    public static ContentValues putHasUsedOwnName(boolean hasUsedOwnName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_HAS_USED_OWN_NAME_PROFILE, hasUsedOwnName);
        return contentValues;
    }

    public static ContentValues putIsUserRegistered(boolean isUserRegistered) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_IS_USER_REGISTERED_PROFILE, isUserRegistered);
        return contentValues;
    }

    public static ContentValues putProfile() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_USERNAME_PROFILE, "");
        contentValues.put(DB_IMAGE_INDEX_PROFILE, 0);
        contentValues.put(DB_HAS_USED_OWN_NAME_PROFILE, false);
        contentValues.put(DB_IS_USER_REGISTERED_PROFILE, false);
        return contentValues;
    }

}
