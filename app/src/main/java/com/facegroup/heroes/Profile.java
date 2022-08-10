package com.facegroup.heroes;

import android.graphics.Color;

public class Profile {

    private static final String[] arrCharactersDefaultNames = {"Alex", "Chris", "Alexandra", "Martin", "Hossein", "Christina", "Najib", "Jack", "Angel", "James", "Thomas", "Emily", "Leo", "Michael", "Zahra"};

    private static final int[] arrCharactersImages = {
            R.drawable.character_male_1, R.drawable.character_male_2,
            R.drawable.character_female_1,
            R.drawable.character_male_3, R.drawable.character_male_4,
            R.drawable.character_female_2,
            R.drawable.character_male_5, R.drawable.character_male_6,
            R.drawable.character_female_3,
            R.drawable.character_male_7, R.drawable.character_male_8,
            R.drawable.character_female_4,
            R.drawable.character_male_9, R.drawable.character_male_10,
            R.drawable.character_female_5};

    final static int MY_NAME_COLOR = Color.WHITE;
    final static int DEFAULT_NAME_COLOR = Color.GRAY;

    public static String[] getArrCharactersDefaultNames() {
        return arrCharactersDefaultNames;
    }

    public static int[] getArrCharactersImages() {
        return arrCharactersImages;
    }
}
