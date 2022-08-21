package com.facegroup.heroes;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;

public class Sound {

    private static SoundPool soundPoolClick;

    public static final int clickSound = R.raw.click_sound;

    static int resClickSound = 0;

    public static void initClickSoundPool(Activity activity) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();
        soundPoolClick = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(2).build();
        resClickSound = soundPoolClick.load(activity, clickSound, 1);
    }

    public static void playSound() {
        soundPoolClick.play(resClickSound, 1, 1, 1, 0, 1);
    }

}
