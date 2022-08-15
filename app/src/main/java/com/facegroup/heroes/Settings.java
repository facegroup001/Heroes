package com.facegroup.heroes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.facegroup.heroes.Biography.Biographies;
import com.facegroup.heroes.Database.Database;
import com.facegroup.heroes.Games.Games;
import com.facegroup.heroes.Games.PictureGame.PictureGameLevels;
import com.facegroup.heroes.Guide.Guide;
import com.facegroup.heroes.Guide.GuideInitialization;

public class Settings extends AppCompatActivity implements GuideInitialization {

    Button btnUpdateProfile, btnAboutUs, btnResetGuides;
    String cameFromWhere;
    SharedPreferences sharedPreferences;

    Database database;
    Guide guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
        init();
        btnUpdateProfile.setOnClickListener(view -> {
            GoSomewhere.goSomewhere(Settings.this, Registration.class, ActivitiesNames.SETTINGS.name(), "CAME_FROM_SETTINGS");
            Sound.playClickSound();
        });
        btnAboutUs.setOnClickListener(view -> {
            Sound.playClickSound();
            GoSomewhere.goSomewhere(Settings.this, AboutUs.class);
        });

        btnResetGuides.setOnClickListener(view -> resetGuide());
    }

    public void resetGuide() {
        database.resetAllGuides();
        initGuide();
        showGuide();
    }

    public void initCameFromWhere() {
        sharedPreferences = getSharedPreferences("SETTINGS_PREFERENCES", MODE_PRIVATE);
        cameFromWhere = sharedPreferences.getString(ActivitiesNames.SETTINGS.name(), "");
    }

    public void init() {
        initWidgets();
        database = new Database(this);
        Sound.initClickSoundPool(this);
        initCameFromWhere();
        initGuide();
        showGuide();
    }

    public void initWidgets() {
        btnUpdateProfile = findViewById(R.id.btn_update_profile_settings);
        btnAboutUs = findViewById(R.id.btn_about_us_settings);
        btnResetGuides = findViewById(R.id.btn_reset_guide_settings);
    }

    @Override
    public void onBackPressed() {
        Sound.playClickSound();
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

    @Override
    public void initGuide() {
        guide = new Guide(this, ActivitiesNames.SETTINGS.name(), new String[]{"Eye", "Timer", "English Word"}, new String[]{"چشم", "تایمر", "لغت انگلیسی"}, new int[]{R.drawable.guide_eye, R.drawable.guide_timer, R.drawable.guide_english_word});
        guide.initGuide(guide);
    }

    @Override
    public void showGuide() {
        guide.showGuide(guide);
    }
}