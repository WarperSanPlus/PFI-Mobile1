package com.example.pfi.Classes;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.example.pfi.Helper.ResourcesManager;
import com.example.pfi.Helper.XMLHelper;
import com.example.pfi.Logger;
import com.example.pfi.R;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Comparator;

public class Category implements Comparable<Category> {
    // region Nom
    private @StringRes int nomId;

    public String getNom() {
        return ResourcesManager.getString(nomId);
    }
    private void setNom(@StringRes int nomId) {
        this.nomId = nomId == 0 ? R.string.article_no_name : nomId;
    }

    public @StringRes int getNomId() { return nomId; }

    @Override
    public int compareTo(Category category) {
        return this.getNom().compareTo(category.getNom());
    }
    // endregion
    // region Articles
    private ArrayList<Article> articles = new ArrayList<>();

    public void sortArticles() {
        articles.sort(Comparator.naturalOrder());
    }

    public ArrayList<Article> getArticles() { return articles; }

    // endregion

    public Category(
            @StringRes int nomId,
            ArrayList<Article> articles
    ) {
        this.setNom(nomId);

        this.articles.addAll(articles);
        this.sortArticles();

        Logger.log("\"" + getNom() + "\" has " + this.articles.size() + " articles.");
    }

    // region Static
    /**
     *
     * @return Categories loaded by the application.
     */
    public static ArrayList<Category> loadCategories() {
        ArrayList<Category> categories = XMLHelper.initializeObjects("categories.xml", MAIN_TAG, SECOND_TAG, Category::parseCategory);

        // Sort categories
        categories.sort(Comparator.naturalOrder());

        return categories;
    }

    private static final String MAIN_TAG = "categories";
    private static final String SECOND_TAG = "category";
    private static Category parseCategory(XmlPullParser parser) {
        try {
            parser.require(XmlPullParser.START_TAG, null, SECOND_TAG);

            String name = "";

            ArrayList<Article> articles = new ArrayList<>();
            boolean lookingForArticles = false;

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_TAG && eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG){
                    String n = parser.getName();

                    if (n.equals("name")) {
                        name = XMLHelper.readField(parser, "name");
                    }
                    else if (n.equals(Article.MAIN_TAG)){
                        lookingForArticles = !lookingForArticles;
                    }
                    else if(lookingForArticles && n.equals(Article.SECOND_TAG)) {
                        articles.add(Article.parseArticle(parser));
                    }
                }

                eventType = parser.next();
            }
            return new Category(ResourcesManager.getStringIdentifier(name), articles);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // endregion
    // region Override

    @NonNull
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder("Nom: \"" + getNom() + "\", ");

        text.append("Articles: [ ");
        for (Article a : articles)
            text.append(a.toString()).append(", ");
        text.append("]");

        return text.toString();
    }

    // endregion
}
