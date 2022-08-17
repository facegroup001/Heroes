package com.facegroup.heroes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ChooseBackgroundMusicDialog {

    public static void showDialog(Context context, int checkedItem, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setSingleChoiceItems(Music.musicNames, checkedItem, listener);
        builder.setTitle("Musics");
        builder.show();
    }

}
