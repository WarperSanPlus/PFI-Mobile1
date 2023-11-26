package com.example.pfi.Classes;

import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pfi.Abstract.AdaptorViewHolder;
import com.example.pfi.R;

public class ArticleViewHolder extends AdaptorViewHolder<Article> {
    ImageView icon;
    TextView txtNom;
    TextView txtPrix;

    ImageButton moreBtn;
    ImageButton minusBtn;
    TextView quantityTV;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);

        icon = itemView.findViewById(R.id.item_panier_img);
        txtNom = itemView.findViewById(R.id.item_panier_nom);
        txtPrix = itemView.findViewById(R.id.item_panier_prix);

        moreBtn = itemView.findViewById(R.id.item_panier_plus);
        minusBtn = itemView.findViewById(R.id.item_panier_moins);
        quantityTV = itemView.findViewById(R.id.item_panier_quantite);
    }

    @Override
    public void setItem(Article item, int position) {
        icon.setImageResource(item.getIconId());
        txtNom.setText(item.getNom());
        txtPrix.setText(item.getPrix());

        moreBtn.setOnClickListener(v -> updateItemAmount(v, item, 1));
        minusBtn.setOnClickListener(v -> updateItemAmount(v, item, -1));

        onAmountChanged(Client.getPanier().getItemAmount(item));
    }

    private void updateItemAmount(View v, Article item, int amount) {
        Client.getPanier().addItem(item, amount);
        int newAmount = Client.getPanier().getItemAmount(item);

        setAmountBtn(moreBtn, newAmount != item.getStockAmount());
        setAmountBtn(minusBtn, newAmount != 0);

        onAmountChanged(newAmount);
    }

    private void onAmountChanged(int newAmount) {
        quantityTV.setText(newAmount + "");

        //this.itemView.setVisibility(newAmount <= 0 ? View.GONE : View.VISIBLE);
    }

    private void setAmountBtn(ImageButton icon, boolean isEnabled) {
        icon.setEnabled(isEnabled);
        icon.setAlpha(isEnabled ? 1f : 0.5f);
    }
}
