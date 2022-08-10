package com.facegroup.heroes.Games;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.facegroup.heroes.Animation;
import com.facegroup.heroes.DisableViews;
import com.facegroup.heroes.GoSomewhere;
import com.facegroup.heroes.Home;
import com.facegroup.heroes.Games.PictureGame.PictureGameLevels;
import com.facegroup.heroes.R;
import com.facegroup.heroes.Sound;
import com.facegroup.heroes.Wealth.Wealth;
import com.facegroup.heroes.Wealth.WealthInitialization;

public class Games extends AppCompatActivity implements WealthInitialization, Animation, DisableViews {

    ImageButton btnJumperGame, btnPictureGame;
    ImageButton btnSettings, btnStore;
    Wealth wealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_games);
        init();

        btnStore.setOnClickListener(view -> {
            Sound.playSoundSound();
            GoSomewhere.goToStore(Games.this, "GAMES");
            disableAllViews();
        });
        btnSettings.setOnClickListener(view -> {
            Sound.playSoundSound();
            GoSomewhere.goToSettings(Games.this, "GAMES");
            disableAllViews();
        });

        btnPictureGame.setOnClickListener(view -> {
            Sound.playSoundSound();
            disableAllViews();
            decreaseViewSize(view);
            new Handler().postDelayed(() -> increaseViewSize(view), 300);
            new Handler().postDelayed(() -> GoSomewhere.goSomewhere(Games.this, PictureGameLevels.class), 500);
        });
        btnJumperGame.setOnClickListener(view -> {
            Sound.playSoundSound();
            disableAllViews();
            decreaseViewSize(view);
            new Handler().postDelayed(() -> increaseViewSize(view), 300);
            new Handler().postDelayed(() -> GoSomewhere.goSomewhere(Games.this, JumperGame.class), 500);
        });

    }

    public void init() {
        initWidgets();
        Sound.initSoundPool(this);
        initWealth();
    }

    public void initWidgets() {
        btnJumperGame = findViewById(R.id.img_btn_jumper_game_games);
        btnPictureGame = findViewById(R.id.img_btn_picture_game_games);

        btnSettings = findViewById(R.id.img_btn_settings);
        btnStore = findViewById(R.id.img_btn_store);
    }

    @Override
    public void increaseViewSize(View view) {
        view.animate().scaleY(1).scaleX(1).setDuration(200);
    }

    @Override
    public void decreaseViewSize(View view) {
        view.animate().scaleY(0.8f).scaleX(0.8f).setDuration(200);
    }

    @Override
    public void onBackPressed() {
        Sound.playSoundSound();
        GoSomewhere.goSomewhere(this, Home.class);
    }

    @Override
    public void initWealth() {
        wealth = new Wealth(this);
        wealth.initWealth();
    }

    @Override
    public void disableAllViews() {
        btnJumperGame.setEnabled(false);
        btnSettings.setEnabled(false);
        btnStore.setEnabled(false);
        btnPictureGame.setEnabled(false);
    }
}