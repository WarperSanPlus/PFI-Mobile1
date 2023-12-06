package com.example.pfi.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.pfi.Client;
import com.example.pfi.Dialogs.ConfirmationAchatsDialog;
import com.example.pfi.Helper.CardUIHelper;
import com.example.pfi.Helper.DialogHelper;
import com.example.pfi.R;
import com.example.pfi.databinding.ActivityPanierBinding;

public class ActivityPanier extends AppCompatActivity {
    TextView totalTV;
    View panierCard;
    View recyclerView;
    View viderPanierBtn;
    View finishedBuyingBtn;
    ActivityPanierBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_panier);

        binding = DataBindingUtil.setContentView (ActivityPanier.this, R.layout.activity_panier);
        //Animation of layout
        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_card);
        binding.panierCard.startAnimation(aniSlide);

        // Set 'retour' button

        binding.btnRetour.setOnClickListener(v -> CardUIHelper.endActivity(this, binding.panierCard));

        // Set 'vider panier' button
        binding.panierBtnVider.setOnClickListener(this::onClearBasketClicked);

        // Set 'passer à la caisse' button
        binding.panierBtnCaisse.setOnClickListener(this::onFinishBuyingClicked);

        Client.getPanier().setArticleAdapteur(this, binding.recyclePanier);

        //Set 'prix total'
        Client.getPanier().addOnUpdateListener(this::onPanierUpdated);

        onPanierUpdated();
    }

    private void onClearBasketClicked(View v) {
        Client.getPanier().clear();
        ((ViewGroup) binding.recyclePanier).removeAllViews(); // TODO : INVESTIGATE WHY IT WORKS
    }

    private void onFinishBuyingClicked(View v) {
        DialogHelper.openDialog(() -> new ConfirmationAchatsDialog(Client.getPanier()), getSupportFragmentManager(), this.toString());
    }

    private void onPanierUpdated() {
        binding.panierTxtPrixTotal.setText(Client.getPanier().getTotal());

        boolean isPanierEmpty = Client.getPanier().getItemCount() == 0;

        //if panier empty -> cannot buy (panierbtnCaisse) and cannot empty panier(panierBtnVider)

        binding.panierBtnCaisse.setAlpha(isPanierEmpty ? 0.5f : 1.0f);
        binding.panierBtnCaisse.setClickable(!isPanierEmpty);

        binding.panierBtnVider.setAlpha(isPanierEmpty ? 0.5f : 1.0f);
        binding.panierBtnVider.setClickable(!isPanierEmpty);

    }
}