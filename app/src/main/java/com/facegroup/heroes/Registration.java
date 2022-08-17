package com.facegroup.heroes;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facegroup.heroes.Database.Database;

public class Registration extends AppCompatActivity implements DisableViews {

    TextView tvUsername;
    ImageButton btnLetsGo, imgBtnPreviousImage, imgBtnNextImage;
    EditText etUsername;
    ImageView imgProfile;
    CheckBox cbUseMyName;

    Database database;

    int imgPosition = 0;

    int backPressedCounter = 0;

    String cameFromSettings = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);
        init();
        etUsername.setEnabled(false);
        setCharacterName();
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvUsername.setText(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        cbUseMyName.setOnCheckedChangeListener((compoundButton, b) -> {
            Sound.playClickSound();
            etUsername.setEnabled(b);
            setCharacterName();
            etUsername.setTextColor(b ? Profile.MY_NAME_COLOR : Profile.DEFAULT_NAME_COLOR);
        });

        setCharacterImage(imgPosition);
        imgBtnNextImage.setOnClickListener(view -> nextCharacter());
        imgBtnPreviousImage.setOnClickListener(view -> previousCharacter());

        btnLetsGo.setOnClickListener(view -> {
            if (isUsernameValid()) {
                updateUser();
                Sound.playClickSound();
                disableAllViews();
                letsGoAnimation();
                if (cameFromSettings.equals("CAME_FROM_SETTINGS")) {
                    new Handler().postDelayed(() -> GoSomewhere.goSomewhere(Registration.this, Settings.class), 1000);
                } else {
                    new Handler().postDelayed(this::goToHome, 1000);
                }
            } else {
                Toast.makeText(Registration.this, "Registration failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void nextCharacter() {
        Sound.playClickSound();
        if (imgPosition == Profile.getArrCharactersImages().length - 1) {
            imgPosition = -1;
        }
        imgPosition++;
        setCharacterImage(imgPosition);
        setCharacterName();
    }

    public void previousCharacter() {
        Sound.playClickSound();
        if (imgPosition == 0) {
            imgPosition = Profile.getArrCharactersImages().length;
        }
        imgPosition--;
        setCharacterImage(imgPosition);
        setCharacterName();
    }

    public void setCharacterImage(int position) {
        imgProfile.setBackgroundResource(Profile.getArrCharactersImages()[position]);
    }

    public void setCharacterName() {
        if (!cbUseMyName.isChecked()) {
            tvUsername.setText(Profile.getArrCharactersDefaultNames()[imgPosition]);
        } else {
            tvUsername.setText(etUsername.getText().toString().trim());
        }
    }

    public void init() {
        initWidgets();
        database = new Database(this);
        Sound.initClickSoundPool(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            cameFromSettings = bundle.getString("SETTINGS");
        }
        if (isUserRegistered()) {
            if (cameFromSettings.equals("CAME_FROM_SETTINGS")) {
                imgPosition = database.getImageIndex();
                boolean usedMyName = database.hasUsedOwnName();
                if (usedMyName) {
                    String username = database.getUsername();
                    etUsername.setText(username);
                    tvUsername.setText(username);
                } else {
                    tvUsername.setText(Profile.getArrCharactersDefaultNames()[database.getImageIndex()]);
                }
                etUsername.setEnabled(usedMyName);
                cbUseMyName.setChecked(database.hasUsedOwnName());
                imgProfile.setBackgroundResource(Profile.getArrCharactersImages()[database.getImageIndex()]);
            } else {
                GoSomewhere.goSomewhere(Registration.this, Home.class);
            }
        } else {
            insertProgramInitialization();
        }
    }

    public void insertProgramInitialization() {
        database.insertProfile();
        database.insertWealth();
        database.insertBiographySettings();
        database.insertAllGuides();
        database.insertAllBiographies();
        database.updateBiographyPerson(0);
        insertAllBackgroundMusics();
    }

    public void updateUser() {
        database.updateImageIndex(imgPosition);
        database.updateUsername(tvUsername.getText().toString());
        database.updateHasUsedOwnName(cbUseMyName.isChecked());
        database.updateIsUserRegistered();
    }

    public void insertAllBackgroundMusics() {
        database.insertBiographyBackgroundMusic();
    }

    public void initWidgets() {
        tvUsername = findViewById(R.id.tv_username_registration);
        btnLetsGo = findViewById(R.id.btn_lets_go_registration);
        imgBtnPreviousImage = findViewById(R.id.img_btn_previous_image_registration);
        imgBtnNextImage = findViewById(R.id.img_btn_next_image_registration);
        etUsername = findViewById(R.id.et_username_registration);
        imgProfile = findViewById(R.id.img_profile_registration);
        cbUseMyName = findViewById(R.id.cb_use_my_name_registration);
    }

    public boolean isUsernameValid() {
        boolean isValid = true;
        if (cbUseMyName.isChecked()) {
            isValid = etUsername.getText().toString().trim().length() >= 3;
        }
        return isValid;
    }


    public boolean isUserRegistered() {
        return database.isUserRegistered();
    }

    @Override
    public void disableAllViews() {
        etUsername.setEnabled(false);
        cbUseMyName.setEnabled(false);
        btnLetsGo.setEnabled(false);
        imgBtnNextImage.setEnabled(false);
        imgBtnPreviousImage.setEnabled(false);
    }

    public void goToHome() {
        GoSomewhere.goSomewhere(this, Home.class);
    }

    public void letsGoAnimation() {
        btnLetsGo.animate().rotationY(-720).setDuration(1000).start();
    }

    @Override
    public void onBackPressed() {
        Sound.playClickSound();
        if (cameFromSettings.equals("CAME_FROM_SETTINGS")) {
            GoSomewhere.goSomewhere(this, Settings.class);
        } else {
            if (backPressedCounter == 1) {
                finish();
            } else {
                Toast.makeText(this, "Press again to exit.", Toast.LENGTH_SHORT).show();
            }
            new Handler().postDelayed(() -> backPressedCounter = 0, 1000);
            backPressedCounter++;
        }
    }
}









