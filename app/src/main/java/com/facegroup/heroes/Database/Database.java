package com.facegroup.heroes.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.facegroup.heroes.Database.Biography.BiographyDatabase;
import com.facegroup.heroes.Database.Biography.BiographyInitialization;
import com.facegroup.heroes.Database.Profile.ProfileDatabase;
import com.facegroup.heroes.Database.Profile.ProfileDatabaseInitialization;
import com.facegroup.heroes.Database.Wealth.WealthDatabase;
import com.facegroup.heroes.Database.Wealth.WealthDatabaseInitialization;
import com.facegroup.heroes.R;

public class Database extends SQLiteOpenHelper implements ProfileDatabaseInitialization, WealthDatabaseInitialization, BiographyInitialization {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "HEROES";

    SQLiteDatabase database;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ProfileDatabase.CREATE_TABLE_PROFILE);
        sqLiteDatabase.execSQL(WealthDatabase.CREATE_TABLE_WEALTH);
        sqLiteDatabase.execSQL(BiographyDatabase.CREATE_TABLE_BIOGRAPHY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ProfileDatabase.TABLE_PROFILE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WealthDatabase.TABLE_WEALTH);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BiographyDatabase.TABLE_BIOGRAPHY);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void insertProfile() {
        ContentValues contentValues = ProfileDatabase.putProfile();
        database.insert(ProfileDatabase.TABLE_PROFILE, null, contentValues);
    }

    @Override
    public void updateUsername(String username) {
        ContentValues contentValues = ProfileDatabase.putUsername(username);
        database.update(ProfileDatabase.TABLE_PROFILE, contentValues, null, null);
    }

    @SuppressLint("Range")
    @Override
    public String getUsername() {
        String username = "";
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM " + ProfileDatabase.TABLE_PROFILE, null);
        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(cursor.getColumnIndex(ProfileDatabase.DB_USERNAME_PROFILE));
            } while (cursor.moveToNext());
        }
        return username;
    }

    @Override
    public void updateImageIndex(int newImageIndex) {
        ContentValues contentValues = ProfileDatabase.putImageIndex(newImageIndex);
        database.update(ProfileDatabase.TABLE_PROFILE, contentValues, null, null);
    }

    @SuppressLint("Range")
    @Override
    public int getImageIndex() {
        int imageIndex = 0;
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM " + ProfileDatabase.TABLE_PROFILE, null);
        if (cursor.moveToFirst()) {
            do {
                imageIndex = cursor.getInt(cursor.getColumnIndex(ProfileDatabase.DB_IMAGE_INDEX_PROFILE));
            } while (cursor.moveToNext());
        }
        return imageIndex;
    }

    @SuppressLint("Range")
    @Override
    public boolean isUserRegistered() {
        boolean isUserRegistered = false;
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM " + ProfileDatabase.TABLE_PROFILE, null);
        if (cursor.moveToFirst()) {
            do {
                isUserRegistered = (cursor.getInt(cursor.getColumnIndex(ProfileDatabase.DB_IS_USER_REGISTERED_PROFILE)) == 1);
            } while (cursor.moveToNext());
        }
        return isUserRegistered;
    }

    @Override
    public void updateIsUserRegistered() {
        ContentValues contentValues = ProfileDatabase.putIsUserRegistered(true);
        database.update(ProfileDatabase.TABLE_PROFILE, contentValues, null, null);
    }

    @Override
    public void updateHasUsedOwnName(boolean hasUsedOwnName) {
        ContentValues contentValues = ProfileDatabase.putHasUsedOwnName(hasUsedOwnName);
        database.update(ProfileDatabase.TABLE_PROFILE, contentValues, null, null);
    }

    @SuppressLint("Range")
    @Override
    public boolean hasUsedOwnName() {
        boolean hasUsedOwnName = false;
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM " + ProfileDatabase.TABLE_PROFILE, null);
        if (cursor.moveToFirst()) {
            do {
                hasUsedOwnName = (cursor.getInt(cursor.getColumnIndex(ProfileDatabase.DB_HAS_USED_OWN_NAME_PROFILE)) == 1);
            } while (cursor.moveToNext());
        }
        return hasUsedOwnName;
    }

    @Override
    public void insertWealth() {
        ContentValues contentValues = WealthDatabase.insertWealth();
        database.insert(WealthDatabase.TABLE_WEALTH, null, contentValues);
    }

    @Override
    public void updateCoinCount(int newCoinAmount) {
        ContentValues contentValues = WealthDatabase.putCoin(newCoinAmount);
        database.update(WealthDatabase.TABLE_WEALTH, contentValues, null, null);
    }

    @SuppressLint("Range")
    @Override
    public int getCoinCount() {
        int coinCount = 0;
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM " + WealthDatabase.TABLE_WEALTH, null);
        if (cursor.moveToFirst()) {
            do {
                coinCount = cursor.getInt(cursor.getColumnIndex(WealthDatabase.DB_COIN_COUNT));
            } while (cursor.moveToNext());
        }
        return coinCount;
    }

    @Override
    public void updateHeartCount(int newHeartAmount) {
        ContentValues contentValues = WealthDatabase.putHeart(newHeartAmount);
        database.update(WealthDatabase.TABLE_WEALTH, contentValues, null, null);
    }

    @SuppressLint("Range")
    @Override
    public int getHeartCount() {
        int heartCount = 0;
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM " + WealthDatabase.TABLE_WEALTH, null);
        if (cursor.moveToFirst()) {
            do {
                heartCount = cursor.getInt(cursor.getColumnIndex(WealthDatabase.DB_HEART_COUNT));
            } while (cursor.moveToNext());
        }
        return heartCount;
    }

    @Override
    public void updateEyeCount(int newEyeAmount) {
        ContentValues contentValues = WealthDatabase.putEye(newEyeAmount);
        database.update(WealthDatabase.TABLE_WEALTH, contentValues, null, null);
    }

    @SuppressLint("Range")
    @Override
    public int getEyeCount() {
        int eyeCount = 0;
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM " + WealthDatabase.TABLE_WEALTH, null);
        if (cursor.moveToFirst()) {
            do {
                eyeCount = cursor.getInt(cursor.getColumnIndex(WealthDatabase.DB_EYE_COUNT));
            } while (cursor.moveToNext());
        }
        return eyeCount;
    }

    @Override
    public void updateCardCount(int newCardAmount) {
        ContentValues contentValues = WealthDatabase.putCard(newCardAmount);
        database.update(WealthDatabase.TABLE_WEALTH, contentValues, null, null);
    }

    @SuppressLint("Range")
    @Override
    public int getCardCount() {
        int cardCount = 0;
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM " + WealthDatabase.TABLE_WEALTH, null);
        if (cursor.moveToFirst()) {
            do {
                cardCount = cursor.getInt(cursor.getColumnIndex(WealthDatabase.DB_CARD_COUNT));
            } while (cursor.moveToNext());
        }
        return cardCount;
    }

    @Override
    public void insertBiographySettings() {
        ContentValues contentValues = BiographyDatabase.insertBiographySettings();
        database.insert(BiographyDatabase.TABLE_BIOGRAPHY, null, contentValues);
    }

    @Override
    public void updateBiographyTextStyle(int newFontStyle) {
        ContentValues contentValues = BiographyDatabase.putTextStyle(newFontStyle);
        database.update(BiographyDatabase.TABLE_BIOGRAPHY, contentValues, null, null);
    }

    @SuppressLint("Range")
    @Override
    public int getBiographyTextStyle() {
        int fontStyle = 0;
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM " + BiographyDatabase.TABLE_BIOGRAPHY, null);
        if (cursor.moveToFirst()) {
            do {
                fontStyle = cursor.getInt(cursor.getColumnIndex(BiographyDatabase.DB_TEXT_STYLE));
            } while (cursor.moveToNext());
        }
        return fontStyle;
    }

    @Override
    public void updateBiographyTextColor(int newTextColor) {
        ContentValues contentValues = BiographyDatabase.putTextColor(newTextColor);
        database.update(BiographyDatabase.TABLE_BIOGRAPHY, contentValues, null, null);
    }

    @SuppressLint("Range")
    @Override
    public int getBiographyTextColor() {
        int textColor = 0;
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM " + BiographyDatabase.TABLE_BIOGRAPHY, null);
        if (cursor.moveToFirst()) {
            do {
                textColor = cursor.getInt(cursor.getColumnIndex(BiographyDatabase.DB_TEXT_COLOR));
            } while (cursor.moveToNext());
        }
        return textColor;
    }

    @Override
    public void updateBiographyTextSize(int newTextSize) {
        ContentValues contentValues = BiographyDatabase.putTextSize(newTextSize);
        database.update(BiographyDatabase.TABLE_BIOGRAPHY, contentValues, null, null);
    }

    @SuppressLint("Range")
    @Override
    public int getBiographyTextSize() {
        int textSize = 18;
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM " + BiographyDatabase.TABLE_BIOGRAPHY, null);
        if (cursor.moveToFirst()) {
            do {
                textSize = cursor.getInt(cursor.getColumnIndex(BiographyDatabase.DB_TEXT_SIZE));
            } while (cursor.moveToNext());
        }
        return textSize;
    }

    @Override
    public void updateBiographyBackgroundMusic(int newBackgroundMusic) {
        ContentValues contentValues = BiographyDatabase.putBackgroundMusic(newBackgroundMusic);
        database.update(BiographyDatabase.TABLE_BIOGRAPHY, contentValues, null, null);
    }

    @SuppressLint("Range")
    @Override
    public int getBiographyBackgroundMusic() {
        int backgroundMusic = R.raw.music1;
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM " + BiographyDatabase.TABLE_BIOGRAPHY, null);
        if (cursor.moveToFirst()) {
            do {
                backgroundMusic = cursor.getInt(cursor.getColumnIndex(BiographyDatabase.DB_BACKGROUND_MUSIC));
            } while (cursor.moveToNext());
        }
        return backgroundMusic;
    }
}
