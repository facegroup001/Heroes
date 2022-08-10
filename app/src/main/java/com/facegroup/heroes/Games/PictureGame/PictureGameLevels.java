package com.facegroup.heroes.Games.PictureGame;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facegroup.heroes.DisableViews;
import com.facegroup.heroes.Games.Games;
import com.facegroup.heroes.GoSomewhere;
import com.facegroup.heroes.R;
import com.facegroup.heroes.Sound;
import com.facegroup.heroes.Wealth.Wealth;
import com.facegroup.heroes.Wealth.WealthInitialization;

public class PictureGameLevels extends AppCompatActivity implements WealthInitialization, DisableViews {

    LinearLayout layoutLevels;
    Button btnStartGame;

    ImageButton btnSettings, btnStore;

    Wealth wealth;

    int gameLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_picture_game_levels);
        init();

        btnSettings.setOnClickListener(view -> {
            Sound.playSoundSound();
            GoSomewhere.goToSettings(PictureGameLevels.this, "PICTURE_GAME_LEVELS");
            disableAllViews();
        });
        btnStore.setOnClickListener(view -> {
            Sound.playSoundSound();
            GoSomewhere.goToStore(PictureGameLevels.this, "PICTURE_GAME_LEVELS");
            disableAllViews();
        });

        btnStartGame.setOnClickListener(view -> {
            Sound.playSoundSound();
            if (gameLevel == 0) {
                Toast.makeText(PictureGameLevels.this, "No level is selected.", Toast.LENGTH_SHORT).show();
            } else {
                disableAllViews();
                Toast.makeText(PictureGameLevels.this, "" + gameLevel, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initLayoutLevels() {
        layoutLevels = findViewById(R.id.layout_picture_game_levels);
        for (int i = 0; i < layoutLevels.getChildCount(); i++) {
            Button btn = (layoutLevels.getChildAt(i) instanceof Button ? (Button) layoutLevels.getChildAt(i) : null);
            if (btn == null) return;
            btn.setOnClickListener(view -> {
                Sound.playSoundSound();
                gameLevel = Integer.parseInt((String) view.getTag());
                for (int j = 0; j < layoutLevels.getChildCount(); j++) {
                    layoutLevels.getChildAt(j).animate().scaleY(1).scaleX(1).setDuration(300);
                }
                view.animate().scaleY(0.7f).scaleX(0.7f).setDuration(300);
            });
        }
    }

    public void init() {
        initWidgets();
        Sound.initSoundPool(this);
        initWealth();
    }

    public void initWidgets() {
        initLayoutLevels();
        btnStartGame = findViewById(R.id.btn_start_picture_game_picture_game_levels);
        btnSettings = findViewById(R.id.img_btn_settings);
        btnStore = findViewById(R.id.img_btn_store);
    }

    @Override
    public void initWealth() {
        wealth = new Wealth(this);
        wealth.initWealth();
    }

    @Override
    public void onBackPressed() {
        Sound.playSoundSound();
        GoSomewhere.goSomewhere(this, Games.class);
    }

    @Override
    public void disableAllViews() {
        for (int i = 0; i < layoutLevels.getChildCount(); i++) {
            layoutLevels.getChildAt(i).setEnabled(false);
        }
        btnStartGame.setEnabled(false);
        btnSettings.setEnabled(false);
        btnStore.setEnabled(false);
    }
}