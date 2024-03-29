package com.example.todo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class BaseActivity extends AppCompatActivity {

    protected AppCompatActivity activity;
    MaterialDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;

    }


    public MaterialDialog showMessage(int titleResId, int messageResId, int posResText) {
        dialog = new MaterialDialog.Builder(this)
                .title(titleResId)
                .content(messageResId)
                .positiveText(posResText)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
        return dialog;
    }

    public MaterialDialog showConfirmationMessage(int titleResId, int messageResId, int posResText , MaterialDialog.SingleButtonCallback onPosAction) {
        dialog = new MaterialDialog.Builder(this)
                .title(titleResId)
                .content(messageResId)
                .positiveText(posResText)
                .onPositive(onPosAction)
                .show();
        return dialog;
    }


    public MaterialDialog showMessage(String title, String message, String posResText) {
        dialog = new MaterialDialog.Builder(this)
                .title(title)
                .content(message)
                .positiveText(posResText)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();

        return dialog;
    }

    public MaterialDialog showProgressBar() {
        dialog = new MaterialDialog.Builder(this)
                .progress(true, 0)
                .cancelable(false)
                .show();

        return dialog;
    }

    public void hideProgressBar(){
        if(dialog!=null&&dialog.isShowing()){
            dialog.dismiss();

        }

    }


}
