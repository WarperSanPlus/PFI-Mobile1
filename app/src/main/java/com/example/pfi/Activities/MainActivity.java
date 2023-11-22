package com.example.pfi.Activities;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pfi.Helper.IntentHelper;
import com.example.pfi.R;

public class MainActivity extends AppCompatActivity {
    private static MainActivity Instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Instance = this;

        IntentHelper.closeAndMove(this, ActivityListe.class);
    }

    // region Static

    public static String getStringValue(@StringRes int id) { return Instance.getString(id); }

    // endregion
}