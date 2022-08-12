package com.facegroup.heroes;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;

public class Sound {

    private static SoundPool soundPoolClick, soundPoolGuide;
    private static int resClickSound, resGuideSound;
    public static int soundClick = R.raw.click_sound;
    public static int soundGuide = R.raw.card_sound;

    public static void initClickSoundPool(Activity activity) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();
        soundPoolClick = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(2).build();
        resClickSound = soundPoolClick.load(activity, soundClick, 1);
    }

    public static void initGuideSoundPool(Activity activity) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();
        soundPoolGuide = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(2).build();
        resGuideSound = soundPoolGuide.load(activity, soundGuide, 1);
    }

    public static void playClickSound() {
        soundPoolClick.play(resClickSound, 1, 1, 1, 0, 1);
    }

    public static void playGuideSound() {
        soundPoolGuide.play(resGuideSound, 1, 1, 1, 0, 1);
    }

}
