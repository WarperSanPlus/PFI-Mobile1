package com.example.pfi.Classes;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.example.pfi.Activities.MainActivity;
import com.example.pfi.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Article implements Comparable<Article>, Serializable {
    // region Nom
    private @StringRes int nomId = R.string.article_no_name;

    public String getNom() {
        return MainActivity.getStringValue(nomId);
    }

    @Override
    public int compareTo(Article article) {
        return this.getNom().compareTo(article.getNom());
    }
    // endregion
    // region Description
    private @StringRes int descriptionId = R.string.article_no_description;

    public String getDescription() { return MainActivity.getStringValue(descriptionId); }
    // endregion
    // region Prix
    private String prix;
    // endregion
    // region Image
    private @DrawableRes int imageId = R.drawable.logo_placeholder;

    public @DrawableRes int getImageId() { return imageId; }
    // endregion
    // region Icon
    private @DrawableRes int iconId = R.drawable.logo_placeholder;

    public @DrawableRes int getIconId() { return iconId; }
    // endregion
    // region Categories
    private final @StringRes int[] categories;

    public boolean isArticleInCategory(Category category){
        for (int j : categories) {
            if (j == category.getNomId())
                return true;
        }
        return false;
    }
    // endregion

    public Article(
            @StringRes int nomId,
            @StringRes int... categories
    ){
        this.nomId = nomId;
        this.categories = categories;
    }

    // region Static
    /**
     * @return Articles loaded by the application.
     */
    public static ArrayList<Article> loadArticles() {
        ArrayList<Article> articles = new ArrayList<>();

        articles.add(new Article(R.string.category_cereals, R.string.category_fruits));
        articles.add(new Article(R.string.category_fruits, R.string.category_fruits, R.string.category_meat));

        return articles;
    }

    // endregion
}
