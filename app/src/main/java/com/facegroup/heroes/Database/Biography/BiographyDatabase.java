package com.facegroup.heroes.Database.Biography;

import android.content.ContentValues;
import android.graphics.Color;

import com.facegroup.heroes.R;

public class BiographyDatabase {
    public static final String DB_TEXT_COLOR = "TEXT_COLOR";
    public static final String DB_TEXT_STYLE = "TEXT_STYLE";
    public static final String DB_TEXT_SIZE = "FONT_SIZE";

    public static final String DB_BACKGROUND_MUSIC = "BACKGROUND_MUSIC";

    public static final String TABLE_BIOGRAPHY = "TABLE_BIOGRAPHY";
    public static final String CREATE_TABLE_BIOGRAPHY = "CREATE TABLE IF NOT EXISTS " + TABLE_BIOGRAPHY + " ( " +
            DB_TEXT_COLOR + " INTEGER," +
            DB_TEXT_STYLE + " INTEGER," +
            DB_TEXT_SIZE + " INTEGER," +
            DB_BACKGROUND_MUSIC + " INTEGER" +
            " )";

    public static ContentValues insertBiographySettings() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_TEXT_COLOR, Color.WHITE);
        contentValues.put(DB_TEXT_STYLE, 0);
        contentValues.put(DB_TEXT_SIZE, 18);
        contentValues.put(DB_BACKGROUND_MUSIC, R.raw.music1);
        return contentValues;
    }

    public static ContentValues putTextColor(int color) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_TEXT_COLOR, color);
        return contentValues;
    }

    public static ContentValues putTextStyle(int fontStyle) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_TEXT_STYLE, fontStyle);
        return contentValues;
    }

    public static ContentValues putTextSize(int textSize) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_TEXT_SIZE, textSize);
        return contentValues;
    }

    public static ContentValues putBackgroundMusic(int backgroundMusic) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_BACKGROUND_MUSIC, backgroundMusic);
        return contentValues;
    }


}
