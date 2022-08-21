package com.facegroup.heroes.Games;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.facegroup.heroes.GoSomewhere;
import com.facegroup.heroes.R;
import com.facegroup.heroes.Sound;

public class JumperGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_jumper_game);
        Sound.initClickSoundPool(this);
    }

    @Override
    public void onBackPressed() {
        Sound.playSound();
        GoSomewhere.goSomewhere(this, Games.class);
    }
}