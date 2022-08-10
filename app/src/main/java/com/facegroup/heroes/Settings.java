package com.facegroup.heroes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.facegroup.heroes.Biography.Biographies;
import com.facegroup.heroes.Games.Games;
import com.facegroup.heroes.Games.PictureGame.PictureGameLevels;

public class Settings extends AppCompatActivity {

    Button btnUpdateProfile, btnAboutUs;
    String cameFromWhere;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
        init();
        btnUpdateProfile.setOnClickListener(view -> {
            GoSomewhere.goSomewhere(Settings.this, Registration.class, "SETTINGS", "CAME_FROM_SETTINGS");
            Sound.playSoundSound();
        });
        btnAboutUs.setOnClickListener(view -> {
            Sound.playSoundSound();
            GoSomewhere.goSomewhere(Settings.this, AboutUs.class);
        });
    }

    public void initCameFromWhere() {
        sharedPreferences = getSharedPreferences("SETTINGS_PREFERENCES", MODE_PRIVATE);
        cameFromWhere = sharedPreferences.getString("SETTINGS", "");
    }

    public void init() {
        initWidgets();
        Sound.initSoundPool(this);
        initCameFromWhere();
    }

    public void initWidgets() {
        btnUpdateProfile = findViewById(R.id.btn_update_profile_settings);
        btnAboutUs = findViewById(R.id.btn_about_us_settings);
    }

    @Override
    public void onBackPressed() {
        Sound.playSoundSound();
        switch (cameFromWhere) {
            case "HOME":
                GoSomewhere.goSomewhere(this, Home.class);
                break;
            case "GAMES":
                GoSomewhere.goSomewhere(this, Games.class);
                break;
            case "STORE":
                GoSomewhere.goSomewhere(this, Store.class);
                break;
            case "PICTURE_GAME_LEVELS":
                GoSomewhere.goSomewhere(this, PictureGameLevels.class);
                break;
            case "BIOGRAPHIES":
                GoSomewhere.goSomewhere(this, Biographies.class);
                break;
        }
    }
}