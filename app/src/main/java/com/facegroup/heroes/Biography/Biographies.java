package com.facegroup.heroes.Biography;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.facegroup.heroes.ActivitiesNames;
import com.facegroup.heroes.Animation;
import com.facegroup.heroes.Database.Biography.Biographies.BiographiesDatabase;
import com.facegroup.heroes.Database.Database;
import com.facegroup.heroes.DisableViews;
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

public class Biographies extends AppCompatActivity implements WealthInitialization, DisableViews, PersonUtils, Animation, GuideInitialization {

    LinearLayout layoutPeople;

    int selectedPersonFromLayout = 0;

    ImageButton btnSettings, btnStore;
    Wealth wealth;
    Guide guide;

    List<Person> peopleList;
    List<Boolean> peopleUnlockStatusList;

    //    Purchase Person
    TextView tvPersonNamePP;
    TextView tvPersonPricePP;
    TextView tvPersonPageCountPP;

    ImageView imgPersonProfileImagePP;
    ImageView imgPersonPriceUnitPP;

    ImageButton btnPurchasePersonPP;
    ImageButton btnCancelPurchasePersonPP;

    RelativeLayout layoutPurchasePerson;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_biographies);
        init();

        btnStore.setOnClickListener(view -> {
            disableAllViews();
            Sound.playClickSound();
            GoSomewhere.goToStore(Biographies.this, ActivitiesNames.BIOGRAPHIES.name());
        });
        btnSettings.setOnClickListener(view -> {
            disableAllViews();
            Sound.playClickSound();
            GoSomewhere.goToSettings(Biographies.this, ActivitiesNames.BIOGRAPHIES.name());
        });

    }

    public void init() {
        initWidgets();
        database = new Database(this);
        Sound.initClickSoundPool(this);
        addPeople();
        showPeopleToList();
        initWealth();
        initLayoutPurchasePerson();
        initGuide();
        showGuide();
    }

    public void getPeopleStatusFromDatabase() {
        peopleUnlockStatusList = new ArrayList<>();
        for (int i = 0; i < BiographiesDatabase.DB_PEOPLE.length; i++) {
            peopleUnlockStatusList.add(database.getBiographyPerson(i));
        }
    }

    public void initWidgets() {
        btnSettings = findViewById(R.id.img_btn_settings);
        btnStore = findViewById(R.id.img_btn_store);
        layoutPeople = findViewById(R.id.layout_people_biographies);
    }

    public void person1() {
        Person person = new Person();
        person.setEnglishName("Bill Gates");
        person.setPersianName("بیل گیتس");
        person.setEnglishBiography(new String[]{"Bill Gates 1, Bill Gates 1, Bill Gates 1, Bill Gates 1, Bill Gates 1, Bill Gates 1, Bill Gates 1, Bill Gates 1, Bill Gates 1, ", "Bill Gates 2", "Bill Gates 3", "Bill Gates 4"});
        person.setPersianBiography(new String[]{"بیل گیتس 1, بیل گیتس 1, بیل گیتس 1, بیل گیتس 1, بیل گیتس 1, بیل گیتس 1, بیل گیتس 1, بیل گیتس 1, بیل گیتس 1, ", "بیل گیتس 2", "بیل گیتس 3", "بیل گیتس 4"});
        person.setEnglishWords(new String[]{"Word 1", "Word 2", "Word 3", "Word 4", "Word 5", "Word 6", "Word 7"});
        person.setPersianWords(new String[]{"لغت 1", "لغت 2", "لغت 3", "لغت 4", "لغت 5", "لغت 6", "لغت 7"});
        person.setImages(new int[]{R.drawable.bill_gates1, R.drawable.bill_gates2, R.drawable.bill_gates3, R.drawable.bill_gates4});
        person.setLockedProfileImage(R.drawable.bill_gates_lock_profile);
        person.setUnlockedProfileImage(R.drawable.bill_gates_unlock_profile);
        person.setPrice(10);
        person.setPriceUnit("COIN");
        addPerson(person);
    }

    public void person2() {
        Person person = new Person();
        person.setEnglishName("Mark Zuckerberg");
        person.setPersianName("مارک زاکربرگ");
        person.setEnglishBiography(new String[]{"Mark Zuckerberg 1", "Mark Zuckerberg 2", "Mark Zuckerberg 3", "Mark Zuckerberg 4"});
        person.setPersianBiography(new String[]{"مارک زاکربرگ 1", "مارک زاکربرگ 2", "مارک زاکربرگ 3", "مارک زاکربرگ 4"});
        person.setEnglishWords(new String[]{"Word 1", "Word 2", "Word 3", "Word 4", "Word 5", "Word 6", "Word 7"});
        person.setPersianWords(new String[]{"لغت 1", "لغت 2", "لغت 3", "لغت 4", "لغت 5", "لغت 6", "لغت 7"});
        person.setImages(new int[]{R.drawable.mark_zuckerberg1, R.drawable.mark_zuckerberg2, R.drawable.mark_zuckerberg3, R.drawable.mark_zuckerberg4});
        person.setLockedProfileImage(R.drawable.mark_zuckerberg_lock_profile);
        person.setUnlockedProfileImage(R.drawable.mark_zuckerberg_unlock_profile);
        person.setPrice(20);
        person.setPriceUnit("COIN");
        addPerson(person);
    }

    public void person3() {
        Person person = new Person();
        person.setEnglishName("Elon Musk");
        person.setPersianName("ایلان ماسک");
        person.setEnglishBiography(new String[]{"Elon Musk 1", "Elon Musk 2", "Elon Musk 3", "Elon Musk 4"});
        person.setPersianBiography(new String[]{"ایلان ماسک 1", "ایلان ماسک 2", "ایلان ماسک 3", "ایلان ماسک 4"});
        person.setEnglishWords(new String[]{"Word 1", "Word 2", "Word 3", "Word 4", "Word 5", "Word 6", "Word 7"});
        person.setPersianWords(new String[]{"لغت 1", "لغت 2", "لغت 3", "لغت 4", "لغت 5", "لغت 6", "لغت 7"});
        person.setImages(new int[]{R.drawable.elon_musk1, R.drawable.elon_musk2, R.drawable.elon_musk3, R.drawable.elon_musk4});
        person.setLockedProfileImage(R.drawable.elon_musk_lock_profile);
        person.setUnlockedProfileImage(R.drawable.elon_musk_unlock_profile);
        person.setPrice(2);
        person.setPriceUnit("CARD");
        addPerson(person);
    }

    public void addPeople() {
        peopleList = new ArrayList<>();
        person1();
        person2();
        person3();
        person1();
        person2();
        person3();
        person1();
        person2();
        person3();
        person1();
    }

    public void initLayoutPurchasePerson() {
        layoutPurchasePerson = findViewById(R.id.layout_purchase_person);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.layout_buy_person, layoutPurchasePerson, false);

        tvPersonNamePP = view.findViewById(R.id.tv_person_name_purchase_person);
        tvPersonPricePP = view.findViewById(R.id.tv_person_price_purchase_person);
        tvPersonPageCountPP = view.findViewById(R.id.tv_person_page_count_purchase_person);

        imgPersonProfileImagePP = view.findViewById(R.id.img_person_profile_image_purchase_person);
        imgPersonPriceUnitPP = view.findViewById(R.id.img_person_price_unit_purchase_person);

        btnPurchasePersonPP = view.findViewById(R.id.img_btn_purchase_person);
        btnCancelPurchasePersonPP = view.findViewById(R.id.img_btn_cancel_purchase_person);

        layoutPurchasePerson.addView(view);
        layoutPurchasePerson.setVisibility(View.GONE);
    }

    @SuppressLint("SetTextI18n")
    public void fillLayoutPurchasePersonData(Person person) {
        tvPersonNamePP.setText(person.getEnglishName());
        tvPersonPricePP.setText(person.getPrice() + "");
        tvPersonPageCountPP.setText(person.getImages().length + "");
        int priceUnitImage = 0;
        switch (person.getPriceUnit()) {
            case "CARD":
                priceUnitImage = R.drawable.card;
                break;
            case "COIN":
                priceUnitImage = R.drawable.coin;
                break;
        }
        imgPersonPriceUnitPP.setBackgroundResource(priceUnitImage);
        imgPersonProfileImagePP.setBackgroundResource(person.getUnlockedProfileImage());
        btnPurchasePersonPP.setOnClickListener(view -> {
            Sound.playClickSound();
            buyPerson(person);
        });
        btnCancelPurchasePersonPP.setOnClickListener(view -> {
            Sound.playClickSound();
            layoutPurchasePerson.setVisibility(View.GONE);
        });
    }

    @Override
    public void onBackPressed() {
        Sound.playClickSound();
        GoSomewhere.goSomewhere(this, Home.class);
    }

    @Override
    public void initWealth() {
        wealth = new Wealth(this);
        wealth.initWealth();
    }

    @Override
    public void disableAllViews() {
        btnSettings.setEnabled(false);
        btnStore.setEnabled(false);
        for (int i = 0; i < layoutPeople.getChildCount(); i++) {
            layoutPeople.getChildAt(i).setEnabled(false);
        }
    }

    @Override
    public void buyPerson(Person person) {
        int coinCount = database.getCoinCount();
        int cardCount = database.getCardCount();
        switch (person.getPriceUnit()) {
            case "CARD":
                if (cardCount >= person.getPrice()) {
                    database.updateCardCount((database.getCardCount() - person.getPrice()));
                    wealth.assignWealth();
                    database.updateBiographyPerson(selectedPersonFromLayout);
                } else {
                    Toast.makeText(this, "You can not buy.", Toast.LENGTH_SHORT).show();
                }
                break;
            case "COIN":
                if (coinCount >= person.getPrice()) {
                    database.updateCoinCount((database.getCoinCount() - person.getPrice()));
                    wealth.assignWealth();
                    database.updateBiographyPerson(selectedPersonFromLayout);
                } else {
                    Toast.makeText(this, "You can not buy.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        layoutPurchasePerson.setVisibility(View.GONE);
        showPeopleToList();
    }

    @Override
    public void sendDataToBiography(View view, Person person) {
        Intent intent = new Intent(Biographies.this, Biography.class);
        intent.putExtra("english_biography_list", person.getEnglishBiography());
        intent.putExtra("english_words_list", person.getEnglishWords());
        intent.putExtra("persian_biography_list", person.getPersianBiography());
        intent.putExtra("persian_words_list", person.getPersianWords());

        intent.putExtra("images", person.getImages());

        intent.putExtra("english_name", person.getEnglishName());
        intent.putExtra("persian_name", person.getPersianName());
        decreaseViewSize(view);
        new Handler().postDelayed(() -> increaseViewSize(view), 300);
        new Handler().postDelayed(() -> {
            startActivity(intent);
            Animatoo.animateZoom(Biographies.this);
            finish();
        }, 500);
    }

    @Override
    public void selectPerson(View view, Person person) {
        Sound.playClickSound();
        if (peopleUnlockStatusList.get(selectedPersonFromLayout)) {
            disableAllViews();
            sendDataToBiography(view, person);
        } else {
            showPurchaseDialog(person);
        }
    }

    @Override
    public void addPerson(Person person) {
        peopleList.add(person);
    }

    @Override
    public void showPurchaseDialog(Person person) {
        fillLayoutPurchasePersonData(person);
        layoutPurchasePerson.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPeopleToList() {
        getPeopleStatusFromDatabase();
        layoutPeople.removeAllViews();
        for (int i = 0; i < (peopleList.size() - 1); i++) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.person_profile_image, layoutPeople, false);
            ImageButton img = view.findViewById(R.id.img_btn_profile_image);
            int h = layoutPeople.getLayoutParams().height;
            img.setLayoutParams(new LinearLayout.LayoutParams(h, h));
            img.setBackgroundResource(peopleUnlockStatusList.get(i) ? peopleList.get(i).getUnlockedProfileImage() : peopleList.get(i).getLockedProfileImage());
            int finalI = i;
            img.setOnClickListener(view1 -> {
                selectedPersonFromLayout = finalI;
                selectPerson(view1, peopleList.get(finalI));
            });
            layoutPeople.addView(view);
        }
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
    public void initGuide() {
        guide = new Guide(this, ActivitiesNames.BIOGRAPHIES.name(), new String[]{"Eye", "Timer", "English Word"}, new String[]{"چشم", "تایمر", "لغت انگلیسی"}, new int[]{R.drawable.guide_eye, R.drawable.guide_timer, R.drawable.guide_english_word});
        guide.initGuide(guide);
    }

    @Override
    public void showGuide() {
        guide.showGuide(guide);
    }
}






