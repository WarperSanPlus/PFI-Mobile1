package com.example.pfi.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pfi.Classes.Article;
import com.example.pfi.Client;
import com.example.pfi.Helper.CardUIHelper;
import com.example.pfi.Helper.IntentHelper;
import com.example.pfi.R;
import com.example.pfi.databinding.ActivityProductDetailsBinding;

public class ActivityProductDetails extends AppCompatActivity {
    public static final String SELECTED_ARTICLE = "Selected_Article";


    ActivityProductDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_product_details);
        binding = DataBindingUtil.setContentView (ActivityProductDetails.this, R.layout.activity_product_details);

        //Animation
        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_card);
        binding.detailsCard.startAnimation(aniSlide);

        //Article send by ProductPreviewDialog
        Article article = (Article) getIntent().getSerializableExtra(SELECTED_ARTICLE);

        if (article != null)
            setArticle(article);

        // Btn RETOUR (arrow)
        binding.detailsBtnRetour.setOnClickListener(view -> CardUIHelper.endActivity(this, binding.detailsCard));

        // Btn Panier (icon panier)
        binding.detailsPanierBtn.setOnClickListener(v -> IntentHelper.closeAndMove(ActivityProductDetails.this, ActivityPanier.class, null));

        // Btn du bas (add to basket OR emptying basket)

        //basket already has item?
        int btnPanierText = Client.getPanier().hasItem(article) ? R.string.remove_from_panier : R.string.add_to_panier;
        binding.detailsActionPanier.setText(btnPanierText);
        binding.detailsActionPanier.setVisibility(article.getStockAmount() == 0 ? View.INVISIBLE : View.VISIBLE);

        // Click btn_actionPanier
        binding.detailsActionPanier.setOnClickListener(view -> {
            int amount = Client.getPanier().hasItem(article) ? -Client.getPanier().getItemAmount(article) : 1;

            Client.getPanier().addItem(article, amount);

            IntentHelper.closeAndMove(ActivityProductDetails.this, ActivityPanier.class, null);
        });


    }

    @SuppressLint("SetTextI18n")
    private void setArticle(Article article) {
        // Set icon imageview
        binding.detailsIcon.setImageResource(article.getIconId());

        // Set name textview
        binding.detailsNom.setText(article.getNom());

        // Set description textview
        binding.detailsDescription.setText(article.getDescription());

        // Set price textview
        binding.detailsPrix.setText("(" + article.getPrix() + ")");
    }
}