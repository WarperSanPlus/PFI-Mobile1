package com.example.pfi.Classes;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.example.pfi.Activities.MainActivity;
import com.example.pfi.R;

import java.util.ArrayList;
import java.util.Comparator;

public class Category implements Comparable<Category> {
    // region Nom
    private @StringRes int nomId;

    public String getNom() {
        return MainActivity.getStringValue(nomId);
    }
    private void setNom(@StringRes int nomId) {
        this.nomId = nomId;
    }

    public @StringRes int getNomId() { return nomId; }

    @Override
    public int compareTo(Category category) {
        return this.getNom().compareTo(category.getNom());
    }
    // endregion
    // region Articles
    private ArrayList<Article> articles = new ArrayList<>();

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void sortArticles() {
        articles.sort(Comparator.naturalOrder());
    }

    public ArrayList<Article> getArticles() { return articles; }

    // endregion

    public Category(
            @StringRes int nomId
    ) {
        this.setNom(nomId);
    }

    // region Static
    /**
     *
     * @return Categories loaded by the application.
     */
    public static ArrayList<Category> loadCategories() {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category(R.string.category_fruits));
        categories.add(new Category(R.string.category_cereals));
        categories.add(new Category(R.string.category_diary));
        categories.add(new Category(R.string.category_meat));

        return categories;
    }

    // endregion
    // region Override

    @NonNull
    @Override
    public String toString() {
        String text = "Nom: \"" + getNom() + "\", ";

        text += "Articles: [ ";
        for (Article a : articles)
            text += a.toString() + ", ";
        text += "]";

        return text;
    }

    // endregion
}
