package com.example.pfi.Classes;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.example.pfi.Helper.IntentHelper;
import com.example.pfi.Helper.ResourcesManager;
import com.example.pfi.Helper.StringHelper;
import com.example.pfi.Helper.XMLHelper;
import com.example.pfi.Logger;
import com.example.pfi.R;

import org.xmlpull.v1.XmlPullParser;

import java.io.Serializable;
import java.util.Random;

public class Article implements Comparable<Article>, Serializable {
    // region Nom
    private @StringRes int nomId = R.string.article_no_name;

    public String getNom() {
        return ResourcesManager.getString(nomId);
    }

    private void setNom(@StringRes int nomId) {
        this.nomId = StringHelper.checkStringId(nomId, R.string.article_no_name);
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
    private @StringRes int prix = R.string.article_no_price;

    public String getPrix() { return ResourcesManager.getString(prix); }

    private void setPrix(@StringRes int prixId) {
        this.prix = StringHelper.checkStringId(prixId, R.string.article_no_price);
    }

    public double getPrixDouble() { return 1; }
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

    public int getQuantity() { return quantité; }

    // endregion

    public Article(
            @StringRes int nomId,
            @StringRes int descriptionId,
            String iconName,
            @StringRes int prixId,
            int quantity
    ){
        setNom(nomId);
        setDescription(descriptionId);
        setPrix(prixId);

        quantité = quantity;

        this.initializeIconId(iconName);
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
            String quantity = "3"; // Default

            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG)
                    continue;

                String n = parser.getName();
                switch (n) {
                    case "icon":
                        icon = XMLHelper.readField(parser, n);
                        break;
                    case "displayName":
                        displayName = XMLHelper.readField(parser, n);
                        break;
                    case "description":
                        description = XMLHelper.readField(parser, n);
                        break;
                    case "price":
                        price = XMLHelper.readField(parser, n);
                        break;
                    case "quantity":
                        quantity = XMLHelper.readField(parser, n);
                        break;
                }
            }

            return new Article(
                    ResourcesManager.getStringIdentifier(displayName),
                    ResourcesManager.getStringIdentifier(description),
                    icon,
                    ResourcesManager.getStringIdentifier(price),
                    Integer.parseInt(quantity)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // endregion
}
