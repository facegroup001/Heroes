package com.facegroup.heroes;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facegroup.heroes.Biography.Biographies;
import com.facegroup.heroes.Database.Database;
import com.facegroup.heroes.Games.Games;
import com.facegroup.heroes.Guide.Guide;
import com.facegroup.heroes.Guide.GuideInitialization;
import com.facegroup.heroes.Wealth.Wealth;
import com.facegroup.heroes.Wealth.WealthInitialization;

public class Home extends AppCompatActivity implements WealthInitialization, Animation, DisableViews, GuideInitialization {

    ImageButton btnSettings, btnStore;
    ImageButton btnPartBiography, btnPartGame;
    TextView tvUsername;
    ImageView imgProfile;

    int backPressedCounter = 0;

    Wealth wealth;
    Guide guide;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        init();

        btnSettings.setOnClickListener(view -> {
            Sound.playSound();
            GoSomewhere.goToSettings(Home.this, ActivitiesNames.HOME.name());
            disableAllViews();
        });
        btnStore.setOnClickListener(view -> {
            Sound.playSound();
            GoSomewhere.goToStore(Home.this, ActivitiesNames.HOME.name());
            disableAllViews();
        });

        btnPartBiography.setOnClickListener(view -> {
            Sound.playSound();
            disableAllViews();
            decreaseViewSize(view);
            new Handler().postDelayed(() -> increaseViewSize(view), 300);
            new Handler().postDelayed(this::goToBiographies, 500);
        });
        btnPartGame.setOnClickListener(view -> {
            Sound.playSound();
            disableAllViews();
            decreaseViewSize(view);
            new Handler().postDelayed(() -> increaseViewSize(view), 300);
            new Handler().postDelayed(this::goToGames, 500);
        });

        tvUsername.setOnClickListener(view -> view.startAnimation(AnimationUtils.loadAnimation(Home.this, R.anim.bounce)));
    }

    public void init() {
        initWidgets();
        initWealth();
        Sound.initClickSoundPool(this);
        database = new Database(this);
        setUsername();
        setProfileImage();
        initGuide();
        showGuide();
    }

    public void setUsername() {
        tvUsername.setText(database.getUsername());
    }

    public void setProfileImage() {
        int profileImageIndex = database.getImageIndex();
        imgProfile.setImageResource(Profile.getArrCharactersImages()[profileImageIndex]);
    }

    public void goToGames() {
        GoSomewhere.goSomewhere(this, Games.class);
    }

    public void goToBiographies() {
        GoSomewhere.goSomewhere(this, Biographies.class);
    }

    @Override
    public void initWealth() {
        wealth = new Wealth(this);
        wealth.initWealth();
    }

    public void initWidgets() {
        btnSettings = findViewById(R.id.img_btn_settings);
        btnStore = findViewById(R.id.img_btn_store);

        btnPartBiography = findViewById(R.id.btn_part_biography_home);
        btnPartGame = findViewById(R.id.btn_part_game_home);

        tvUsername = findViewById(R.id.tv_username_home);
        imgProfile = findViewById(R.id.img_profile_home);
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
        Sound.playSound();
        if (backPressedCounter == 1) {
            finish();
        } else {
            Toast.makeText(this, "Press again to exit.", Toast.LENGTH_SHORT).show();
        }
        new Handler().postDelayed(() -> backPressedCounter = 0, 1000);
        backPressedCounter++;
    }

    @Override
    public void disableAllViews() {
        btnSettings.setEnabled(false);
        btnPartBiography.setEnabled(false);
        btnStore.setEnabled(false);
        btnPartGame.setEnabled(false);
    }

    @Override
    public void initGuide() {
        guide = new Guide(this, ActivitiesNames.HOME.name(), new String[]{"Eye", "Timer", "English Word"}, new String[]{"چشم", "تایمر", "لغت انگلیسی"}, new int[]{R.drawable.guide_eye, R.drawable.guide_timer, R.drawable.guide_english_word});
        guide.initGuide(guide);
    }

    @Override
    public void showGuide() {
        guide.showGuide(guide);
    }
}