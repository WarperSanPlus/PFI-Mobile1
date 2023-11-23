package com.example.pfi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pfi.Helper.IntentHelper;
import com.example.pfi.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentHelper.closeAndMove(this, ActivityListe.class);
    }
}