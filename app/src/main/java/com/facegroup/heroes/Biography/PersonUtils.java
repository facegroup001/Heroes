package com.facegroup.heroes.Biography;

import android.view.View;

public interface PersonUtils {

    void buyPerson(Person person);

    void selectPerson(View view, Person person);

    void addPerson(Person person);

    void showPurchaseDialog(Person person);

    void sendDataToBiography(View view, Person person);

    void showPeopleToList();

}
