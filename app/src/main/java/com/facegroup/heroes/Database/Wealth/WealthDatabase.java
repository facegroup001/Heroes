package com.facegroup.heroes.Database.Wealth;

import android.content.ContentValues;

import com.facegroup.heroes.Wealth.Wealth;

public class WealthDatabase {

    public static final String TABLE_WEALTH = "TABLE_WEALTH";

    public static final String DB_COIN_COUNT = "COIN_COUNT";
    public static final String DB_HEART_COUNT = "HEART_COUNT";
    public static final String DB_EYE_COUNT = "EYE_COUNT";
    public static final String DB_CARD_COUNT = "CARD_COUNT";

    public static final String CREATE_TABLE_WEALTH = "CREATE TABLE IF NOT EXISTS " + TABLE_WEALTH + " ( " +
            DB_COIN_COUNT + " INTEGER, " +
            DB_HEART_COUNT + " INTEGER, " +
            DB_EYE_COUNT + " INTEGER, " +
            DB_CARD_COUNT + " INTEGER " +
            " )";

    public static ContentValues insertWealth() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_COIN_COUNT, Wealth.NORMAL_COIN_AMOUNT);
        contentValues.put(DB_HEART_COUNT, Wealth.NORMAL_HEART_AMOUNT);
        contentValues.put(DB_EYE_COUNT, Wealth.NORMAL_EYE_AMOUNT);
        contentValues.put(DB_CARD_COUNT, Wealth.NORMAL_CARD_AMOUNT);
        return contentValues;
    }

    public static ContentValues putCoin(int coin) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_COIN_COUNT, coin);
        return contentValues;
    }

    public static ContentValues putHeart(int heart) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_HEART_COUNT, heart);
        return contentValues;
    }

    public static ContentValues putEye(int eye) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_EYE_COUNT, eye);
        return contentValues;
    }

    public static ContentValues putCard(int card) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_CARD_COUNT, card);
        return contentValues;
    }

}
