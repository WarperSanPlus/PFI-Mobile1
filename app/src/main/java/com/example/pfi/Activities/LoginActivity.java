package com.example.pfi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pfi.Classes.Client;
import com.example.pfi.Config;
import com.example.pfi.Helper.IntentHelper;
import com.example.pfi.Helper.ResourcesManager;
import com.example.pfi.Logger;
import com.example.pfi.R;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    private EditText edit_nom;
    private EditText edit_mdp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ResourcesManager.Context = this;

        // if automatic login is checked
        if (Config.AUTOMATIC_LOGIN) {
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

    private void onConnectionBtnClicked(View view) {
        // Récupération nom & mot de passe du client
        String username = edit_nom.getText().toString();
        String password = edit_mdp.getText().toString();

        // Vérification du mot de passe (par défaut: Password)
        if (!Client.isPasswordValid(username, password)){
            Snackbar.make(
                    findViewById(R.id.login_layout1),
                    getString(R.string.login_error),
                    Snackbar.LENGTH_SHORT
            ).show();
            return;
        }

        onSuccessfulLogin(username, password);
    }

    private void onSuccessfulLogin(String name, String password) {
        Client.setClient(name);

        IntentHelper.moveToActivity(this, ActivityListe.class, null);
    }
}
