package com.example.pfi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pfi.Classes.Client;
import com.example.pfi.R;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    private EditText edit_nom;
    private EditText edit_mdp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Récupération des views
        edit_nom = findViewById(R.id.login_edit_nom);
        edit_mdp = findViewById(R.id.login_edit_mdp);
        Button btn = findViewById(R.id.login_btn_connection);

        // Click bouton
        btn.setOnClickListener(this::onConnectionBtnClicked);
    }

    /**
     *
     * @param mdpDuClient  Parameter 1 -> mdp dans le editText
     * @return boolean si le mdp est correct
     */
    private boolean mdpCorrect(String mdpDuClient) {
        return mdpDuClient.equals("Password");
    }

    private void onConnectionBtnClicked(View view) {
        // Récupération nom & mot de passe du client
        String nom = edit_nom.getText().toString();
        String mdp = edit_mdp.getText().toString();

        // Vérification du mot de passe (par défaut: Password)
        if (!mdpCorrect(mdp)){
            Snackbar.make(
                    findViewById(R.id.login_layout1),
                    getString(R.string.login_error),
                    Snackbar.LENGTH_SHORT
            ).show();
            return;
        }

        Intent versListeÉpicerie = new Intent(this, MainActivity.class);

        Client client = new Client(nom, mdp);
        startActivity(versListeÉpicerie);
    }
}
