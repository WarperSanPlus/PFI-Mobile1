package com.example.pfi.Activities;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.example.pfi.R;

public class MainActivity extends AppCompatActivity {
    private static MainActivity Instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Instance = this;

        Intent toActivityList = new Intent(this, ActivityListe.class);

        startActivity(toActivityList);
    }

    // region Static

    public static String getStringValue(@StringRes int id) { return Instance.getString(id); }

    // endregion
}