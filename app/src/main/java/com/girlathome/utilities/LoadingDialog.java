package com.girlathome.utilities;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.girlathome.R;

/**
 * Created by steve on 4/24/17.
 */

public class LoadingDialog {
    AlertDialog alertDialog;

    public void loadingDialog(Activity activity, String title) {
        // custom dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.myDialog));
        LayoutInflater inflater = LayoutInflater.from(activity);
        View dialogView = inflater.inflate(R.layout.loading_layout, null);
        dialogBuilder.setView(dialogView);
        TextView tvTitlte = (TextView) dialogView.findViewById(R.id.title);
        tvTitlte.setText(title);
        alertDialog = dialogBuilder.create();
      /*  btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSTK(tillNumber, inv.getAmount());
                alertDialog.cancel();
            }
        });*/
        alertDialog.show();

    }

    public void dismissDialog() {
        alertDialog.dismiss();
    }
}

