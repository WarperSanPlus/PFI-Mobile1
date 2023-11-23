package com.example.pfi.Classes;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.example.pfi.Helper.ResourcesManager;
import com.example.pfi.Helper.XMLHelper;
import com.example.pfi.Logger;
import com.example.pfi.R;

import org.xmlpull.v1.XmlPullParser;

import java.io.Serializable;

public class Article implements Comparable<Article>, Serializable {
    // region Nom
    private @StringRes int nomId = R.string.article_no_name;

    public String getNom() {
        return ResourcesManager.getString(nomId);
    }

    private void setNom(@StringRes int nomId) {
        if (nomId == 0)
            nomId = R.string.article_no_name;

        this.nomId = nomId;
    }

    @Override
    public int compareTo(Article article) {
        return this.getNom().compareTo(article.getNom());
    }
    // endregion
    // region Description
    private @StringRes int descriptionId = R.string.article_no_description;

    public String getDescription() { return ResourcesManager.getString(descriptionId); }

    private void setDescription(@StringRes int descriptionId) {
        if (descriptionId == 0)
            descriptionId = R.string.article_no_description;

        this.descriptionId = descriptionId;
    }
    // endregion
    // region Prix
    private String prix;
    // endregion
    // region Icon
    private @DrawableRes int iconId = R.drawable.logo_placeholder;

    public @DrawableRes int getIconId() { return iconId; }

    private void initializeIconId(String unlocalizedArticleName) {
        @DrawableRes int i = ResourcesManager.getDrawableRes(unlocalizedArticleName);

        if (i == 0) {
            Logger.error("No drawable with the name \"" + unlocalizedArticleName + "\" was found.");
            return;
        }

        iconId = i;
    }
    // endregion
    // region Quantité

    private int quantité;

    public void replaceArticle(int amount) {
        quantité += amount;
    }

    public void takeArticle(int amount) {
        quantité -= amount;
    }

    // endregion

    private Article(
            @StringRes int nomId,
            @StringRes int descriptionId,
            String iconName,
            String prix
    ){
        setNom(nomId);
        setDescription(descriptionId);

        this.initializeIconId(iconName);
        this.prix = prix;
    }

    // region Static
    public static final String MAIN_TAG = "articles";
    public static final String SECOND_TAG = "article";

    public static Article parseArticle(XmlPullParser parser) {
        try {
            parser.require(XmlPullParser.START_TAG, null, SECOND_TAG);

            String icon = "";
            String displayName = "";
            String description = "";
            String price = "";

            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG)
                    continue;

                String n = parser.getName();
                if (n.equals("icon")) {
                    icon = XMLHelper.readField(parser, "icon");
                } else if (n.equals("displayName")) {
                    displayName = XMLHelper.readField(parser, "displayName");
                } else if (n.equals("description")) {
                    description = XMLHelper.readField(parser, "description");
                } else if (n.equals("price")) {
                    price = XMLHelper.readField(parser, "price");
                }
            }

            return new Article(
                    ResourcesManager.getStringIdentifier(displayName),
                    ResourcesManager.getStringIdentifier(description),
                    icon,
                    price
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // endregion
}
