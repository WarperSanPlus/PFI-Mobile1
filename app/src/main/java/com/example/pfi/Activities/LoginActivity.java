package com.example.pfi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pfi.Classes.Client;
import com.example.pfi.Config;
import com.example.pfi.Helper.IntentHelper;
import com.example.pfi.Helper.ResourcesManager;
import com.example.pfi.Helper.SoundHelper;
import com.example.pfi.Helper.ThreadHelper;
import com.example.pfi.Logger;
import com.example.pfi.R;

public class LoginActivity extends AppCompatActivity {
    private EditText edit_nom;
    private EditText edit_mdp;
    MediaPlayer mp = new MediaPlayer();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ResourcesManager.Context = this;

        // if automatic login is checked
        if (Config.ENABLE_AUTOMATIC_LOGIN) {
            Logger.log("*** Automatically logged in ***");
            onSuccessfulLogin("DEBUG_CLIENT", "DEFAULT");
        }

        // Récupération des views
        edit_nom = findViewById(R.id.login_edit_nom);
        edit_mdp = findViewById(R.id.login_edit_mdp);
        Button btn = findViewById(R.id.login_btn_connection);

        // Click bouton
        btn.setOnClickListener(this::onConnectionBtnClicked);
    }

    Thread loginThread = null;
    private void onConnectionBtnClicked(View view) {
        // Récupération nom & mot de passe du client
        String username = edit_nom.getText().toString();
        String password = edit_mdp.getText().toString();

        if (loginThread != null)
            return;

        loginThread = ThreadHelper.startThread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    onLoginTry();
                } catch (InterruptedException e) {
                    Logger.error(e);
                }
            }

            private void onLoginTry() throws InterruptedException {
                if (Config.ENABLE_ARTIFICIAL_FETCH_TIME) {
                    showToast("Artificial fetch time...");
                    Thread.sleep(3000); // "loading" ...
                }

                // Vérification du mot de passe
                boolean isPasswordValid = Client.isPasswordValid(username, password);

                // Play sound
                mp = SoundHelper.playSound(mp, isPasswordValid ? R.raw.login_access_accepted : R.raw.login_access_denied);

                if (isPasswordValid) // Login in
                    onSuccessfulLogin(username, password);
                else // Show error
                    showToast(ResourcesManager.getString(R.string.login_error));

                loginThread = null;
            }
        });
    }

    private void showToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void onSuccessfulLogin(String name, String password) {
        Client.setClient(name);
        IntentHelper.moveToActivity(this, ActivityListe.class, null);
        mp = null;
    }
}
