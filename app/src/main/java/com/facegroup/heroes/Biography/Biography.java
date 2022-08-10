package com.facegroup.heroes.Biography;


import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facegroup.heroes.Database.Database;
import com.facegroup.heroes.DisableViews;
import com.facegroup.heroes.GoSomewhere;
import com.facegroup.heroes.R;
import com.facegroup.heroes.Sound;

import java.util.Locale;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Biography extends AppCompatActivity implements DisableViews {

    ImageButton btnChangeBiographyLanguage;
    ImageButton btnNextPage, btnPreviousPage;
    ImageButton btnZoomInText, btnZoomOutText;
    ImageButton btnPlayMusic, btnReadBiography;
    ImageButton btnChangeBiographyTextColor;
    ImageButton btnChangeTextStyle;
    TextView tvBiographyPageCount;
    ImageView imgBiography;
    TextView tvName, tvBiography;

    MediaPlayer mpMusic;

    private static final int MINIMUM_TEXT_SIZE = 18;
    private static final int MAXIMUM_TEXT_SIZE = 26;

    int biographyTextSize;
    int biographyTextColor;
    int biographyTextStyle;   //    Normal = 0   bold = 1    Italic = 2    Bold-Italic = 3
    AmbilWarnaDialog colorPickerDialog;
    TextToSpeech textToSpeech;

    Person person;

    int currentPage = 0;
    int pagesCount;
    boolean isBiographyEnglish = true;

    String[] smallVowels = {"a", "e", "i", "o", "u"};
    String[] capitalVowels = {"A", "E", "I", "O", "U"};

    Animation animationBlink, animationBounce;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_biography);
        init();
        btnPreviousPage.setOnClickListener(view -> {
            blinkAnimation();
            disableAllViews();
            previousPage();
            new Handler().postDelayed(this::enableViews, 200);
        });
        btnNextPage.setOnClickListener(view -> {
            blinkAnimation();
            disableAllViews();
            nextPage();
            new Handler().postDelayed(this::enableViews, 200);
        });
        btnChangeBiographyLanguage.setOnClickListener(view -> {
            Sound.playSoundSound();
            disableAllViews();
            view.animate().rotationYBy(360).setDuration(500);
            new Handler().postDelayed(this::enableViews, 500);
            changeBiographyLanguage();
        });
        btnPlayMusic.setOnClickListener(view -> {
            Sound.playSoundSound();
            if (mpMusic.isPlaying()) {
                pauseMusic();
            } else {
                playMusic();
            }
        });
        btnZoomOutText.setOnClickListener(view -> {
            Sound.playSoundSound();
            if (database.getBiographyTextSize() > MINIMUM_TEXT_SIZE) {
                database.updateBiographyTextSize((database.getBiographyTextSize() - 1));
                tvBiography.setTextSize(TypedValue.COMPLEX_UNIT_SP, database.getBiographyTextSize());
            }
        });
        btnZoomInText.setOnClickListener(view -> {
            Sound.playSoundSound();
            if (database.getBiographyTextSize() < MAXIMUM_TEXT_SIZE) {
                database.updateBiographyTextSize((database.getBiographyTextSize() + 1));
                tvBiography.setTextSize(TypedValue.COMPLEX_UNIT_SP, database.getBiographyTextSize());
            }
        });
        btnReadBiography.setOnClickListener(v -> {
            if (isBiographyEnglish) {
                if (!textToSpeech.isSpeaking()) {
                    readBiographyText();
                } else {
                    stopReadingBiographyText();
                }
            }
        });
        btnChangeTextStyle.setOnClickListener(view -> {
            Sound.playSoundSound();
            if (biographyTextStyle == 3) {
                biographyTextStyle = -1;
            }
            biographyTextStyle++;
            database.updateBiographyTextStyle(biographyTextStyle);
            tvBiography.setTypeface(Typeface.DEFAULT, biographyTextStyle);
        });
        btnChangeBiographyTextColor.setOnClickListener(view -> {
            Sound.playSoundSound();
            initColorPickerDialog();
            colorPickerDialog.show();
        });

        tvName.setOnClickListener(view -> view.startAnimation(animationBounce));
        tvBiography.setOnClickListener(view -> view.startAnimation(animationBounce));
    }

    public void initColorPickerDialog() {
        colorPickerDialog = new AmbilWarnaDialog(Biography.this, biographyTextColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Sound.playSoundSound();
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                Sound.playSoundSound();
                database.updateBiographyTextColor(color);
                tvBiography.setTextColor(database.getBiographyTextColor());
            }
        });
    }

    public void blinkAnimation() {
        imgBiography.startAnimation(animationBlink);
        tvBiography.startAnimation(animationBlink);
    }

    public void bounceAnimation() {
        tvName.startAnimation(animationBounce);
        tvBiography.startAnimation(animationBounce);
        imgBiography.startAnimation(animationBounce);
    }

    public void initAnimation() {
        animationBlink = AnimationUtils.loadAnimation(this, R.anim.blink);
        animationBounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
    }

    public void changeBiographyLanguage() {
        stopReadingBiographyText();
        btnChangeBiographyLanguage.setBackgroundResource(isBiographyEnglish ? R.drawable.btn_english : R.drawable.btn_farsi);
        isBiographyEnglish = !isBiographyEnglish;
        tvName.setText(isBiographyEnglish ? person.getEnglishName() : person.getPersianName());
        tvName.startAnimation(animationBlink);
        refreshBiographyPage();
    }

    public void previousPage() {
        if (currentPage == 0) {
            currentPage = pagesCount;
        }
        currentPage--;
        refreshBiographyPage();
    }

    public void nextPage() {
        if (currentPage == pagesCount - 1) {
            currentPage = -1;
        }
        currentPage++;
        refreshBiographyPage();
    }

    public void refreshBiographyPage() {
        if (isBiographyEnglish) {
            tvBiography.setText(person.getEnglishBiography()[currentPage]);
            tvName.setText(person.getEnglishName());
        } else {
            tvBiography.setText(person.getPersianBiography()[currentPage]);
            tvName.setText(person.getPersianName());
        }
        imgBiography.setImageResource(person.getImages()[currentPage]);
        refreshPageCount();
        stopReadingBiographyText();
    }

    @SuppressLint("SetTextI18n")
    public void refreshPageCount() {
        tvBiographyPageCount.setText((currentPage + 1) + " / " + pagesCount);
    }

    public void initPlayMusic() {
        int backgroundMusic = database.getBiographyBackgroundMusic();
        mpMusic = MediaPlayer.create(this, backgroundMusic);
    }

    public void pauseMusic() {
        if (mpMusic != null) {
            if (mpMusic.isPlaying()) {
                mpMusic.pause();
            }
        }
        setMusicPlayButtonBackground();
    }

    public void playMusic() {
        if (mpMusic != null) {
            if (!mpMusic.isPlaying()) {
                mpMusic.start();
            }
        }
        setMusicPlayButtonBackground();
        stopReadingBiographyText();
    }

    public void setMusicPlayButtonBackground() {
        btnPlayMusic.setBackgroundResource(mpMusic.isPlaying() ? R.drawable.audio_pause : R.drawable.audio_play);
    }

    private void initTextToSpeech() {
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == RESULT_OK) {
                textToSpeech.setLanguage(Locale.US);
            }
        });
    }

    public void initReadBio() {
        initTextToSpeech();
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {

            }

            @Override
            public void onDone(String utteranceId) {
                stopReadingBiographyText();
            }

            @Override
            public void onError(String utteranceId) {

            }
        });
    }

    public void stopReadingBiographyText() {
        textToSpeech.stop();
        btnReadBiography.setBackgroundResource(R.drawable.audio_play);
    }

    public void readBiographyText() {
        pauseMusic();
        btnReadBiography.setBackgroundResource(R.drawable.audio_pause);
        textToSpeech.speak(person.getEnglishBiography()[currentPage], TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
    }

    public void initWords() {
        LinearLayout layoutWords = findViewById(R.id.layout_words_biography);
        for (int iterator = 0; iterator < person.getEnglishWords().length; iterator++) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.word, layoutWords, false);
            TextView wordView = view.findViewById(R.id.tv_word);
            wordView.setText(person.getEnglishWords()[iterator]);
            int finalIterator = iterator;
            wordView.setOnLongClickListener(view12 -> {
                String word = ((TextView) view12).getText().toString();
                boolean isEnglish = false;
                for (int i = 0; i < smallVowels.length; i++) {
                    if (word.contains(smallVowels[i]) || word.contains(capitalVowels[i])) {
                        isEnglish = true;
                        break;
                    }
                }

                if (isEnglish) {
                    wordView.setText(person.getPersianWords()[finalIterator]);
                    wordView.setBackgroundResource(R.drawable.selected_word_background);
                } else {
                    wordView.setText(person.getEnglishWords()[finalIterator]);
                    wordView.setBackgroundResource(R.drawable.word_background);
                }
                return true;
            });
            wordView.setOnClickListener(view1 -> {
                String word = ((TextView) view1).getText().toString();
                boolean isEnglish = false;
                for (int i = 0; i < smallVowels.length; i++) {
                    if (word.contains(smallVowels[i]) || word.contains(capitalVowels[i])) {
                        isEnglish = true;
                        break;
                    }
                }
                if (isEnglish) {
                    textToSpeech.speak(((TextView) view1).getText().toString(), TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
                }
            });
            layoutWords.addView(view);
        }
    }

    public void init() {
        initWidgets();
        database = new Database(this);

        biographyTextStyle = database.getBiographyTextStyle();
        tvBiography.setTypeface(Typeface.DEFAULT, biographyTextStyle);

        biographyTextSize = database.getBiographyTextSize();
        tvBiography.setTextSize(biographyTextSize);

        biographyTextColor = database.getBiographyTextColor();
        tvBiography.setTextColor(biographyTextColor);

        initAnimation();
        getPersonInfo();
        initReadBio();
        refreshBiographyPage();
        bounceAnimation();
        initPlayMusic();
        initWords();
    }

    public void initWidgets() {
        btnChangeBiographyLanguage = findViewById(R.id.btn_change_text_language_biography);
        btnNextPage = findViewById(R.id.btn_next_page_biography);
        btnPreviousPage = findViewById(R.id.btn_previous_page_biography);
        tvBiographyPageCount = findViewById(R.id.tv_biography_page_count_biography);
        imgBiography = findViewById(R.id.img_biography);
        tvName = findViewById(R.id.tv_person_name_biography);
        tvBiography = findViewById(R.id.tv_person_biography);
        btnZoomInText = findViewById(R.id.img_btn_zoom_in_text_biography);
        btnZoomOutText = findViewById(R.id.img_btn_zoom_out_text_biography);
        btnReadBiography = findViewById(R.id.img_btn_read_biography_text);
        btnPlayMusic = findViewById(R.id.img_btn_play_music_biography);
        btnChangeTextStyle = findViewById(R.id.img_btn_change_biography_text_style);
        btnChangeBiographyTextColor = findViewById(R.id.img_btn_change_biography_text_color);
    }

    public void getPersonInfo() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            person = new Person();
            person.setImages(bundle.getIntArray("images"));
            person.setPersianName(bundle.getString("persian_name"));
            person.setEnglishName(bundle.getString("english_name"));
            person.setPersianWords(bundle.getStringArray("persian_words_list"));
            person.setPersianBiography(bundle.getStringArray("persian_biography_list"));
            person.setEnglishWords(bundle.getStringArray("english_words_list"));
            person.setEnglishBiography(bundle.getStringArray("english_biography_list"));
            pagesCount = person.getImages().length;
        }
    }

    @Override
    public void onBackPressed() {
        pauseMusic();
        Sound.playSoundSound();
        GoSomewhere.goSomewhere(this, Biographies.class);
    }

    @Override
    public void disableAllViews() {
        btnPreviousPage.setEnabled(false);
        btnNextPage.setEnabled(false);
        btnChangeBiographyLanguage.setEnabled(false);
    }

    public void enableViews() {
        btnPreviousPage.setEnabled(true);
        btnNextPage.setEnabled(true);
        btnChangeBiographyLanguage.setEnabled(true);
    }
}