package com.facegroup.heroes.Wealth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

import com.facegroup.heroes.Database.Database;
import com.facegroup.heroes.R;

public class Wealth {


    TextView tvCoin, tvHeart, tvEye, tvCard;
    public static int coinCount, heartCount, eyeCount, cardCount;

    public final static int NOT_ENOUGH_WEALTH_COLOR = Color.RED;
    public final static int NORMAL_WEALTH_COLOR = Color.WHITE;
    public final static int ENOUGH_WEALTH_COLOR = Color.GREEN;
    ///////////////////////////////////////////////////////////
    public final static int NOT_ENOUGH_COIN_AMOUNT = 20;
    public final static int NORMAL_COIN_AMOUNT = 100;
    public final static int ENOUGH_COIN_AMOUNT = 250;
    public final static int MAXIMUM_COIN_AMOUNT = 1000;
    ///////////////////////////////////////////////////////////
    public final static int NOT_ENOUGH_HEART_AMOUNT = 10;
    public final static int NORMAL_HEART_AMOUNT = 20;
    public final static int ENOUGH_HEART_AMOUNT = 50;
    public final static int MAXIMUM_HEART_AMOUNT = 500;
    ///////////////////////////////////////////////////////////
    public final static int NOT_ENOUGH_EYE_AMOUNT = 5;
    public final static int NORMAL_EYE_AMOUNT = 10;
    public final static int ENOUGH_EYE_AMOUNT = 20;
    public final static int MAXIMUM_EYE_AMOUNT = 250;
    ///////////////////////////////////////////////////////////
    public final static int NOT_ENOUGH_CARD_AMOUNT = 3;
    public final static int NORMAL_CARD_AMOUNT = 5;
    public final static int ENOUGH_CARD_AMOUNT = 10;
    public final static int MAXIMUM_CARD_AMOUNT = 100;

    Database database;

    public Wealth(Activity activity) {
        database = new Database(activity);
        this.tvCoin = activity.findViewById(R.id.tv_coin_count);
        this.tvHeart = activity.findViewById(R.id.tv_heart_count);
        this.tvEye = activity.findViewById(R.id.tv_eye_count);
        this.tvCard = activity.findViewById(R.id.tv_card_count);
    }

    public void initWealth() {
        getWealth();
        setWealth();
        setWealthColor();
    }

    public void assignWealth() {
        getWealth();
        setWealth();
        setWealthColor();
    }

    private void getWealth() {
        Wealth.coinCount = database.getCoinCount();
        Wealth.heartCount = database.getHeartCount();
        Wealth.eyeCount = database.getEyeCount();
        Wealth.cardCount = database.getCardCount();
    }

    @SuppressLint("SetTextI18n")
    private void setWealth() {
        tvCoin.setText(Wealth.coinCount + "");
        tvHeart.setText(Wealth.heartCount + "");
        tvEye.setText(Wealth.eyeCount + "");
        tvCard.setText(Wealth.cardCount + "");
    }

    private void setWealthColor() {
        if (Wealth.coinCount <= Wealth.NOT_ENOUGH_COIN_AMOUNT) {
            tvCoin.setTextColor(Wealth.NOT_ENOUGH_WEALTH_COLOR);
        } else if (Wealth.coinCount <= Wealth.NORMAL_COIN_AMOUNT) {
            tvCoin.setTextColor(Wealth.NORMAL_WEALTH_COLOR);
        } else if (Wealth.coinCount >= Wealth.ENOUGH_COIN_AMOUNT) {
            tvCoin.setTextColor(Wealth.ENOUGH_WEALTH_COLOR);
        }
        if (Wealth.heartCount <= Wealth.NOT_ENOUGH_HEART_AMOUNT) {
            tvHeart.setTextColor(Wealth.NOT_ENOUGH_WEALTH_COLOR);
        } else if (Wealth.heartCount <= Wealth.NORMAL_HEART_AMOUNT) {
            tvHeart.setTextColor(Wealth.NORMAL_WEALTH_COLOR);
        } else if (Wealth.heartCount >= Wealth.ENOUGH_HEART_AMOUNT) {
            tvHeart.setTextColor(Wealth.ENOUGH_WEALTH_COLOR);
        }
        if (Wealth.eyeCount <= Wealth.NOT_ENOUGH_EYE_AMOUNT) {
            tvEye.setTextColor(Wealth.NOT_ENOUGH_WEALTH_COLOR);
        } else if (Wealth.eyeCount <= Wealth.NORMAL_EYE_AMOUNT) {
            tvEye.setTextColor(Wealth.NORMAL_WEALTH_COLOR);
        } else if (Wealth.eyeCount >= Wealth.ENOUGH_EYE_AMOUNT) {
            tvEye.setTextColor(Wealth.ENOUGH_WEALTH_COLOR);
        }
        if (Wealth.cardCount <= Wealth.NOT_ENOUGH_CARD_AMOUNT) {
            tvCard.setTextColor(Wealth.NOT_ENOUGH_WEALTH_COLOR);
        } else if (Wealth.cardCount <= Wealth.NORMAL_CARD_AMOUNT) {
            tvCard.setTextColor(Wealth.NORMAL_WEALTH_COLOR);
        } else if (Wealth.cardCount >= Wealth.ENOUGH_CARD_AMOUNT) {
            tvCard.setTextColor(Wealth.ENOUGH_WEALTH_COLOR);
        }
    }

}
