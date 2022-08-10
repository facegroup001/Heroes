package com.facegroup.heroes;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;

public class Sound {

    private static SoundPool soundPoolSound;
    private static int resSound;
    public static int sound = R.raw.click_sound;

    public static void initSoundPool(Activity activity) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();
        soundPoolSound = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(2).build();
        resSound = soundPoolSound.load(activity, sound, 1);
    }

    public static void playSoundSound() {
        soundPoolSound.play(resSound, 1, 1, 1, 0, 1);
    }

}
