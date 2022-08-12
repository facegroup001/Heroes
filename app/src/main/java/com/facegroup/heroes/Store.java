package com.facegroup.heroes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.facegroup.heroes.Biography.Biographies;
import com.facegroup.heroes.Database.Database;
import com.facegroup.heroes.Games.Games;
import com.facegroup.heroes.Games.PictureGame.PictureGameLevels;
import com.facegroup.heroes.Guide.Guide;
import com.facegroup.heroes.Guide.GuideInitialization;
import com.facegroup.heroes.Wealth.Wealth;
import com.facegroup.heroes.Wealth.WealthInitialization;

public class Store extends AppCompatActivity implements WealthInitialization, GuideInitialization {

    String cameFromWhere;
    Wealth wealth;
    ImageButton btnSettings;
    SharedPreferences sharedPreferences;

    LinearLayout layoutBuyCoinStore;
    Database database;
    Guide guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_store);
        init();
        btnSettings.setOnClickListener(view -> {
            Sound.playClickSound();
            GoSomewhere.goToSettings(Store.this, "STORE");
        });
    }

    public void initCameFromWhere() {
        sharedPreferences = getSharedPreferences("STORE_PREFERENCES", MODE_PRIVATE);
        cameFromWhere = sharedPreferences.getString("STORE", "");
    }

    public void init() {
        initWidgets();
        database = new Database(this);
        Sound.initClickSoundPool(this);
        initWealth();
        initCameFromWhere();
        initGuide();
        showGuide();
    }

    public void initLayoutBuyCoinStore() {
        layoutBuyCoinStore = findViewById(R.id.layout_buy_coin_store);
        for (int i = 0; i < layoutBuyCoinStore.getChildCount(); i++) {
            ImageView img = (layoutBuyCoinStore.getChildAt(i) instanceof ImageView ? (ImageView) layoutBuyCoinStore.getChildAt(i) : null);
            if (img == null) return;
            img.setOnClickListener(view -> {
                view.startAnimation(AnimationUtils.loadAnimation(Store.this, R.anim.bounce));
                database.updateCoinCount((database.getCoinCount() + 1));
                wealth.assignWealth();
            });
        }
    }

    public void initWidgets() {
        btnSettings = findViewById(R.id.img_btn_settings);
        initLayoutBuyCoinStore();
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
            case "PICTURE_GAME_LEVELS":
                GoSomewhere.goSomewhere(this, PictureGameLevels.class);
                break;
            case "BIOGRAPHIES":
                GoSomewhere.goSomewhere(this, Biographies.class);
                break;
        }

    }

    @Override
    public void initWealth() {
        wealth = new Wealth(this);
        wealth.initWealth();
    }

    @Override
    public void initGuide() {
        guide = new Guide(this, "STORE", new String[]{"Eye", "Timer", "English Word"}, new String[]{"چشم", "تایمر", "لغت انگلیسی"}, new int[]{R.drawable.guide_eye, R.drawable.guide_timer, R.drawable.guide_english_word});
        guide.initGuide(guide);
    }

    @Override
    public void showGuide() {
        guide.showGuide(guide);
    }
}