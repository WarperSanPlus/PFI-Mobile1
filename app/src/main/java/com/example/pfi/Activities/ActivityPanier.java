package com.example.pfi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pfi.Classes.Article;
import com.example.pfi.Classes.PanierArticle;
import com.example.pfi.R;

import java.util.ArrayList;

public class ActivityPanier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        PanierArticle p = new PanierArticle();

        // Set 'retour' button
        ImageButton retourBtn = findViewById(R.id.panier_btnRetour);
        retourBtn.setOnClickListener(v -> this.finish());

        // Set 'vider panier' button
        Button viderPanierBtn = findViewById(R.id.panier_btnVider);
        viderPanierBtn.setOnClickListener(this::onClearBasketClicked);

        // Set 'passer à la caisse' button
        Button finishedBuyingBtn = findViewById(R.id.panier_btnCaisse);
        finishedBuyingBtn.setOnClickListener(this::onFinishBuyingClicked);

        /*ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article(R.string.article_name_pomme, R.string.article_pomme_desc, "article_pomme", R.string.article_pomme_prix));
        articles.add(new Article(R.string.article_name_pomme, R.string.article_pomme_desc, "article_pomme", R.string.article_pomme_prix));
        articles.add(new Article(R.string.article_name_pomme, R.string.article_pomme_desc, "article_pomme", R.string.article_pomme_prix));
        articles.add(new Article(R.string.article_name_pomme, R.string.article_pomme_desc, "article_pomme", R.string.article_pomme_prix));
        articles.add(new Article(R.string.article_name_pomme, R.string.article_pomme_desc, "article_pomme", R.string.article_pomme_prix));
        articles.add(new Article(R.string.article_name_pomme, R.string.article_pomme_desc, "article_pomme", R.string.article_pomme_prix));
        p.addItems(articles);*/

        p.addItem(new Article(R.string.article_name_pomme, R.string.article_pomme_desc, "article_pomme", R.string.article_pomme_prix, 2));

        p.setArticleAdapteur(this, findViewById(R.id.recyclePanier));

        TextView totalTV = findViewById(R.id.panier_txt_prixTotal);
        totalTV.setText(p.getTotal());
    }

    private void onClearBasketClicked(View v) {
        // ...
    }

    private void onFinishBuyingClicked(View v) {
        // ...
    }
}