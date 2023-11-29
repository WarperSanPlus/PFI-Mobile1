package com.example.pfi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pfi.Classes.Article;
import com.example.pfi.Classes.Client;
import com.example.pfi.Classes.PanierArticle;
import com.example.pfi.Helper.CardUIHelper;
import com.example.pfi.Helper.IntentHelper;
import com.example.pfi.Logger;
import com.example.pfi.R;

public class ActivityProductDetails extends AppCompatActivity {
    public static final String SELECTED_ARTICLE = "Selected_Article";

    private View productDetailsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // HeaderBarHelper.setHeaderBar(this, R.string.activity_product_details_name);

        //Article envoyé par ProductPreviewDialog
        Article article = (Article) getIntent().getSerializableExtra(SELECTED_ARTICLE);

        if (article != null)
            setArticle(article);

        // Btn RETOUR
        ImageButton btn_retour = findViewById(R.id.details_btnRetour);
        btn_retour.setOnClickListener(view -> CardUIHelper.endActivity(this, productDetailsCard));

        // Btn Panier
        ImageButton panierBtn = findViewById(R.id.details_panierBtn);
        panierBtn.setOnClickListener(v -> IntentHelper.closeAndMove(ActivityProductDetails.this, ActivityPanier.class, null));

        // L'autre btn
        Button btn_actionPanier = findViewById(R.id.details_actionPanier);

        int btnPanierText = Client.getPanier().hasItem(article) ? R.string.remove_from_panier : R.string.add_to_panier;
        btn_actionPanier.setText(btnPanierText);

        btn_actionPanier.setOnClickListener(view -> {
            int amount = Client.getPanier().hasItem(article) ? -Client.getPanier().getItemAmount(article) : 1;

            Client.getPanier().addItem(article, amount);

            IntentHelper.closeAndMove(ActivityProductDetails.this, ActivityPanier.class, null);
        });

        productDetailsCard = findViewById(R.id.details_card);
        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_card);
        productDetailsCard.startAnimation(aniSlide);
    }

    @SuppressLint("SetTextI18n")
    private void setArticle(Article article) {
        // Set icon imageview
        ImageView icon = findViewById(R.id.details_icon);
        icon.setImageResource(article.getIconId());

        // Set name textview
        TextView nom = findViewById(R.id.details_nom);
        nom.setText(article.getNom());

        // Set description textview
        TextView description = findViewById(R.id.details_description);
        description.setText(article.getDescription());

        // Set price textview
        TextView prix = findViewById(R.id.details_prix);
        prix.setText("(" + article.getPrix() + ")");
    }
}