package com.facegroup.heroes;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUs extends AppCompatActivity {

    MediaPlayer mpBackgroundMusic;
    LinearLayout layoutAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about_us);
        layoutAboutUs = findViewById(R.id.layout_about_us);
        layoutAboutUs.setEnabled(false);
        layoutAboutUs.setScaleX(7);
        layoutAboutUs.setScaleY(7);
        layoutAboutUs.animate().scaleX(1).scaleY(1).setDuration(1000);
        new Handler().postDelayed(() -> layoutAboutUs.setEnabled(true), 1500);
        initBackgroundMusic();
        startMusic();
    }

    public void goToInstagram(View view) {
        switch ((String) view.getTag()) {
            case "najib":
                GoSomewhere.goToInstagram(this, "_najibhazara");
                break;
            case "hossein":
                GoSomewhere.goToInstagram(this, "rezayan_code");
                break;
        }
    }

    public void initBackgroundMusic() {
        mpBackgroundMusic = MediaPlayer.create(this, R.raw.music2);
    }

    public void startMusic() {
        if (mpBackgroundMusic != null) {
            mpBackgroundMusic.start();
        }
    }

    public void stopMusic() {
        mpBackgroundMusic.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMusic();
    }

    @Override
    public void onBackPressed() {
        Sound.playSoundSound();
        stopMusic();
        GoSomewhere.goSomewhere(this, Settings.class);
    }
}