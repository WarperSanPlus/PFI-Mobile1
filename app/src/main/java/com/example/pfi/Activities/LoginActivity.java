package com.example.pfi.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.example.pfi.Client;
import com.example.pfi.Config;
import com.example.pfi.Helper.IntentHelper;
import com.example.pfi.Helper.ResourcesManager;
import com.example.pfi.Helper.SoundHelper;
import com.example.pfi.Helper.ThreadHelper;
import com.example.pfi.Logger;
import com.example.pfi.R;
import com.example.pfi.databinding.ActivityLoginBinding;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private EditText edit_nom;
    private EditText edit_mdp;
    MediaPlayer mp = new MediaPlayer();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        binding = DataBindingUtil.setContentView (LoginActivity.this, R.layout.activity_login);
       // binding.setClient(new Client("kjnbh", "kjhb"));
        ResourcesManager.Context = this;

        // if automatic login is checked
         /*   if (Config.ENABLE_AUTOMATIC_LOGIN) {
            Logger.log("*** Automatically logged in ***");
            onSuccessfulLogin("DEBUG_CLIENT", "DEFAULT");
        }*/

        edit_nom = findViewById(R.id.login_edit_nom);
        edit_mdp = findViewById(R.id.login_edit_mdp);


        // Add onclick btn connexion
        binding.loginBtnConnection.setOnClickListener(this::onConnectionBtnClicked);

        createMenu();
    }

    Thread loginThread = null;

    private void onConnectionBtnClicked(View view) {

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

                // Verify the password
                boolean isPasswordValid = Client.isPasswordValid(binding.loginEditNom.getText().toString(), binding.loginEditMdp.getText().toString());
                binding.setClient(new Client(binding.loginEditNom.getText().toString(), binding.loginEditMdp.getText().toString()));
                // Play sound
                mp = SoundHelper.playSound(mp, isPasswordValid ? R.raw.login_access_accepted : R.raw.login_access_denied);

                if (isPasswordValid) // Login in
                    onSuccessfulLogin(binding.getClient().getUsername(), binding.loginEditMdp.getText().toString());
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
        Client.setClient(binding.getClient().getUsername());
        IntentHelper.moveToActivity(this, ActivityListe.class, null);
        mp = null;
    }


    // --- MENU --- //
    private void createMenu() {
        // Set buttons

        binding.loginMenuBtn.setOnClickListener(this::showMenu);
    }

    private void showMenu(View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.getMenuInflater().inflate(R.menu.more_options_menu, popupMenu.getMenu());

        popupMenu.getMenu()
                .findItem(R.id.more_options_lang_fr_en)
                .setTitle(getNextLang().getDisplayName());

        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.more_options_lang_fr_en) {
                switchLang(item);
                return true;
            }
            return false;
        });

        popupMenu.show();
    }

    @SuppressLint("UnsafeIntentLaunch")
    private void switchLang(MenuItem item) {
        setLang(getNextLang());

        finish();
        startActivity(getIntent());
    }

    private Locale getNextLang() {
        return getCurrentLang().toLanguageTag().equals("en") ? Locale.getDefault() : new Locale("en");
    }

    private Locale getCurrentLang() {
        return ResourcesManager.getResources().getConfiguration().getLocales().get(0);
    }

    private void setLang(Locale locale) {
        Resources res = ResourcesManager.getResources();
        Configuration conf = res.getConfiguration();
        conf.setLocale(locale);
        res.updateConfiguration(conf, null);
    }
}