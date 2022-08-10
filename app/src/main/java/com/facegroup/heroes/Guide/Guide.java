package com.facegroup.heroes.Guide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facegroup.heroes.Database.Database;
import com.facegroup.heroes.Profile;
import com.facegroup.heroes.R;
import com.facegroup.heroes.Sound;

public class Guide {

    String activityName;
    String[] persianGuides, englishGuides;
    int[] guidesImages;

    static int guidePage = 0;
    static boolean isGuideEnglish = true;

    ImageView imgCharacter, imgGuide;
    TextView btnNextGuide, btnChangeGuideLanguage;
    TextView tvGuide, tvActivityName;

    Database database;

    public Guide(Context context, String activityName, String[] persianGuides, String[] englishGuides, int[] guidesImages) {
        database = new Database(context);
        this.activityName = activityName;
        this.persianGuides = persianGuides;
        this.englishGuides = englishGuides;
        this.guidesImages = guidesImages;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String[] getPersianGuides() {
        return persianGuides;
    }

    public void setPersianGuides(String[] persianGuides) {
        this.persianGuides = persianGuides;
    }

    public String[] getEnglishGuides() {
        return englishGuides;
    }

    public void setEnglishGuides(String[] englishGuides) {
        this.englishGuides = englishGuides;
    }

    public int[] getGuidesImages() {
        return guidesImages;
    }

    public void setGuidesImages(int[] guidesImages) {
        this.guidesImages = guidesImages;
    }

    @SuppressLint("SetTextI18n")
    public void initGuide(Activity activity, Guide guide) {
        Sound.initSoundPool(activity);
        LayoutInflater inflater = LayoutInflater.from(activity);
        RelativeLayout layoutGuide = activity.findViewById(R.id.layout_guide);
        View guideView = inflater.inflate(R.layout.layout_guide, layoutGuide, false);

        imgCharacter = guideView.findViewById(R.id.img_character_guide);
        imgGuide = guideView.findViewById(R.id.img_guide_image);
        tvActivityName = guideView.findViewById(R.id.tv_activity_name_guide);
        tvGuide = guideView.findViewById(R.id.tv_guide_text);
        btnNextGuide = guideView.findViewById(R.id.img_btn_next_guide);
        btnChangeGuideLanguage = guideView.findViewById(R.id.img_btn_change_guide_language);

        btnNextGuide.setText("NEXT");
        btnChangeGuideLanguage.setText("رهنمای فارسی");

        btnNextGuide.setOnClickListener(view1 -> {
            Sound.playSoundSound();
            nextGuide(guide, layoutGuide);
        });
        btnChangeGuideLanguage.setOnClickListener(view12 -> {
            Sound.playSoundSound();
            changeLanguage(guide);
        });

        layoutGuide.addView(guideView);
        layoutGuide.setVisibility(View.GONE);
    }

    public void setGuideData(Guide guideData) {
        tvGuide.setText(isGuideEnglish ? guideData.getEnglishGuides()[guidePage] : guideData.getPersianGuides()[guidePage]);
        tvActivityName.setText(guideData.getActivityName());
        imgGuide.setImageResource(guideData.getGuidesImages()[guidePage]);
        imgCharacter.setImageResource(Profile.getArrCharactersImages()[database.getImageIndex()]);
    }

    public void showGuide(Activity activity, Guide guide) {
        guidePage = 0;
        setGuideData(guide);
        RelativeLayout layoutGuide = activity.findViewById(R.id.layout_guide);
        layoutGuide.setVisibility(View.VISIBLE);
    }

    public void changeLanguage(Guide guide) {
        isGuideEnglish = !isGuideEnglish;
        btnChangeGuideLanguage.setText(isGuideEnglish ? "رهنمای فارسی" : "ENGLISH GUIDE");
        btnNextGuide.setText(isGuideEnglish ? "NEXT" : "بعدی");
        setGuideData(guide);
    }

    public void nextGuide(Guide guide, RelativeLayout layoutGuide) {
        if (guidePage == guide.getEnglishGuides().length - 2) {
            btnNextGuide.setText(isGuideEnglish ? "DONE" : "تمام");
            btnNextGuide.setBackgroundResource(R.drawable.done_button_draw);
        }
        if (guidePage == guide.getEnglishGuides().length - 1) {
            skipGuide(layoutGuide);
            return;
        }
        guidePage++;
        setGuideData(guide);
    }

    public void skipGuide(RelativeLayout layoutGuide) {
        // todo update this to database
        layoutGuide.setVisibility(View.GONE);
    }
}
