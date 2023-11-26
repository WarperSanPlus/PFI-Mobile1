package com.example.pfi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;

import com.example.pfi.Helper.ThreadHelper;
import com.example.pfi.R;

public class CardTransitionActivity extends AppCompatActivity {

    public static final String DESTINATION_ACTIVITY = "destActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_transition);

        ThreadHelper.startThread(() -> {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Bundle extraBundle = getIntent().getExtras();
            Class dest = (Class) extraBundle.getSerializable(DESTINATION_ACTIVITY);

            extraBundle.remove(DESTINATION_ACTIVITY); // Remove ad extra

            Intent c = new Intent(CardTransitionActivity.this, dest);

            c.replaceExtras(extraBundle);

            finish(); // Remove CardTransitionActivity from the callstack
            overridePendingTransition(R.anim.fade_in, R.anim.empty_anim);

            startActivity(c);
        });
    }
}