package com.example.pfi.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pfi.Classes.Client;
import com.example.pfi.Helper.CardUIHelper;
import com.example.pfi.R;

public class ActivityPanier extends AppCompatActivity {
    TextView totalTV;
    View panierCard;
    View recyclerView;
    View viderPanierBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        // Set 'retour' button
        ImageButton retourBtn = findViewById(R.id.panier_btnRetour);
        retourBtn.setOnClickListener(v -> CardUIHelper.endActivity(this, panierCard));

        // Set 'vider panier' button
        viderPanierBtn = findViewById(R.id.panier_btnVider);
        viderPanierBtn.setOnClickListener(this::onClearBasketClicked);

        // Set 'passer à la caisse' button
        Button finishedBuyingBtn = findViewById(R.id.panier_btnCaisse);
        finishedBuyingBtn.setOnClickListener(this::onFinishBuyingClicked);

        recyclerView = findViewById(R.id.recyclePanier);
        Client.getPanier().setArticleAdapteur(this, (RecyclerView) recyclerView);

        totalTV = findViewById(R.id.panier_txt_prixTotal);
        Client.getPanier().addOnUpdateListener(this::onPanierUpdated);

        onPanierUpdated();

        panierCard = findViewById(R.id.panier_card);
        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_card);
        panierCard.startAnimation(aniSlide);
    }

    private void onClearBasketClicked(View v) {
        Client.getPanier().clear();
        ((ViewGroup) recyclerView).removeAllViews(); // TODO : INVESTIGATE WHY IT WORKS

    }

    private void onFinishBuyingClicked(View v) {
        // ...
    }

    private void onPanierUpdated() {
        totalTV.setText(Client.getPanier().getTotal());

        boolean isPanierEmpty = Client.getPanier().getItemCount() == 0;
        viderPanierBtn.setAlpha(isPanierEmpty ? 0.5f : 1.0f);
        viderPanierBtn.setClickable(!isPanierEmpty);
    }
}