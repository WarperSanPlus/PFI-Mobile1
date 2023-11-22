package com.example.pfi;

import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

import com.example.pfi.Activities.MainActivity;
import com.example.pfi.Classes.Category;

import java.util.ArrayList;

public class Article implements Comparable<Article> {
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
    private @IdRes int imageId;
    // endregion
    // region Icon
    private @IdRes int iconId;

    public @IdRes int getIconId() { return iconId; }
    // endregion
    // region Categories
    private @StringRes int[] categories;

    public boolean isArticleInCategory(Category category){
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] == category.getNomId())
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
