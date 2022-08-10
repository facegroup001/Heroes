package com.facegroup.heroes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class GoSomewhere {

    public static void goSomewhere(Activity activity, Class<?> destination, String codeName, String value) {
        Intent intent = new Intent(activity, destination);
        intent.putExtra(codeName, value);
        activity.startActivity(intent);
        Animatoo.animateZoom(activity);
        activity.finish();
    }

    public static void goSomewhere(Activity activity, Class<?> destination) {
        Intent intent = new Intent(activity, destination);
        activity.startActivity(intent);
        Animatoo.animateZoom(activity);
        activity.finish();
    }

    public static void goToSettings(Activity activity, String className) {
        Intent intent = new Intent(activity, Settings.class);
        SharedPreferences sharedPreferences = activity.getSharedPreferences("SETTINGS_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("SETTINGS", className);
        editor.apply();
        activity.startActivity(intent);
        Animatoo.animateZoom(activity);
        activity.finish();
    }

    public static void goToStore(Activity activity, String className) {
        Intent intent = new Intent(activity, Store.class);
        SharedPreferences sharedPreferences = activity.getSharedPreferences("STORE_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("STORE", className);
        editor.apply();
        activity.startActivity(intent);
        Animatoo.animateZoom(activity);
        activity.finish();
    }

    public static void goToInstagram(Activity activity, String username) {
        Sound.initSoundPool(activity);
        Sound.playSoundSound();
        Uri uri = Uri.parse("https://www.instagram.com/" + username + "/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);
    }


}
