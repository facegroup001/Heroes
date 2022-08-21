package com.facegroup.heroes.Store;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.facegroup.heroes.ActivitiesNames;
import com.facegroup.heroes.Biography.Biographies;
import com.facegroup.heroes.Database.Database;
import com.facegroup.heroes.Games.Games;
import com.facegroup.heroes.Games.PictureGame.PictureGameLevels;
import com.facegroup.heroes.GoSomewhere;
import com.facegroup.heroes.Guide.Guide;
import com.facegroup.heroes.Guide.GuideInitialization;
import com.facegroup.heroes.Home;
import com.facegroup.heroes.R;
import com.facegroup.heroes.Sound;
import com.facegroup.heroes.Wealth.Wealth;
import com.facegroup.heroes.Wealth.WealthInitialization;

import java.util.ArrayList;
import java.util.List;

public class Store extends AppCompatActivity implements WealthInitialization, GuideInitialization {

    String cameFromWhere;
    Wealth wealth;
    ImageButton btnSettings;
    SharedPreferences sharedPreferences;

    RecyclerView recyclerViewCoin, recyclerViewHeart, recyclerViewEye, recyclerViewCard;
    WealthAdapter coinAdapter, heartAdapter, eyeAdapter, cardAdapter;
    Database database;
    Guide guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_store);
        init();
        btnSettings.setOnClickListener(view -> {
            Sound.playSound();
            GoSomewhere.goToSettings(Store.this, ActivitiesNames.STORE.name());
        });
    }

    public void initCameFromWhere() {
        sharedPreferences = getSharedPreferences("STORE_PREFERENCES", MODE_PRIVATE);
        cameFromWhere = sharedPreferences.getString("STORE", "");
    }

    public void init() {
        initWidgets();
        Sound.initClickSoundPool(this);
        database = new Database(this);
        initWealth();
        initCameFromWhere();
        initGuide();
        showGuide();
        initRecyclerViews();
        initAdapters();
    }

    public void initRecyclerViews() {
        recyclerViewCoin = findViewById(R.id.recycler_view_coin);
        recyclerViewHeart = findViewById(R.id.recycler_view_heart);
        recyclerViewEye = findViewById(R.id.recycler_view_eye);
        recyclerViewCard = findViewById(R.id.recycler_view_card);
    }

    public void initAdapters() {
        initCoinAdapter();
        initHeartAdapter();
        initEyeAdapter();
        initCardAdapter();
    }

    public void initWidgets() {
        btnSettings = findViewById(R.id.img_btn_settings);
//        initAllWealthLayouts();
    }

    public void initCoinAdapter() {
        List<WealthModel> coinList = new ArrayList<>();
        coinList.add(new WealthModel(R.drawable.buy_coin, 1, 1));
        coinList.add(new WealthModel(R.drawable.buy_coin, 2, 1));
        coinList.add(new WealthModel(R.drawable.buy_coin, 3, 1));
        coinList.add(new WealthModel(R.drawable.buy_coin, 4, 1));
        coinAdapter = new WealthAdapter("COIN", this, coinList);
        recyclerViewCoin.setAdapter(coinAdapter);
    }

    public void initHeartAdapter() {
        List<WealthModel> heartList = new ArrayList<>();
        heartList.add(new WealthModel(R.drawable.buy_heart_store, 1, 1));
        heartList.add(new WealthModel(R.drawable.buy_heart_store, 2, 1));
        heartList.add(new WealthModel(R.drawable.buy_heart_store, 3, 1));
        heartList.add(new WealthModel(R.drawable.buy_heart_store, 4, 1));
        heartAdapter = new WealthAdapter("HEART", this, heartList);
        recyclerViewHeart.setAdapter(heartAdapter);
    }

    public void initEyeAdapter() {
        List<WealthModel> eyeList = new ArrayList<>();
        eyeList.add(new WealthModel(R.drawable.buy_eye_store, 1, 1));
        eyeList.add(new WealthModel(R.drawable.buy_eye_store, 2, 1));
        eyeList.add(new WealthModel(R.drawable.buy_eye_store, 3, 1));
        eyeList.add(new WealthModel(R.drawable.buy_eye_store, 4, 1));
        eyeAdapter = new WealthAdapter("EYE", this, eyeList);
        recyclerViewEye.setAdapter(eyeAdapter);
    }

    public void initCardAdapter() {
        List<WealthModel> cardList = new ArrayList<>();
        cardList.add(new WealthModel(R.drawable.buy_card_with_coin, 1, 1));
        cardList.add(new WealthModel(R.drawable.buy_card_with_coin, 2, 1));
        cardList.add(new WealthModel(R.drawable.buy_card_with_coin, 3, 1));
        cardList.add(new WealthModel(R.drawable.buy_card_with_coin, 4, 1));
        cardAdapter = new WealthAdapter("CARD", this, cardList);
        recyclerViewCard.setAdapter(cardAdapter);
    }

    @Override
    public void onBackPressed() {
        Sound.playSound();
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
        guide = new Guide(this, ActivitiesNames.STORE.name(), new String[]{"Eye", "Timer", "English Word"}, new String[]{"چشم", "تایمر", "لغت انگلیسی"}, new int[]{R.drawable.guide_eye, R.drawable.guide_timer, R.drawable.guide_english_word});
        guide.initGuide(guide);
    }

    @Override
    public void showGuide() {
        guide.showGuide(guide);
    }
}