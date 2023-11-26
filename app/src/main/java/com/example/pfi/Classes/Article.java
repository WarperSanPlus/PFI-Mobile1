package com.example.pfi.Classes;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.example.pfi.Abstract.Product;
import com.example.pfi.Helper.ResourcesManager;
import com.example.pfi.Helper.StringHelper;
import com.example.pfi.Helper.XMLHelper;
import com.example.pfi.Logger;
import com.example.pfi.R;
import com.example.pfi.Translator;

import org.xmlpull.v1.XmlPullParser;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Article extends Product implements Comparable<Article> {
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

    public String getPrix() { return convertPrix(getPrixDouble()); }

    private void setPrix(@StringRes int prixId) {
        this.prix = StringHelper.checkStringId(prixId, R.string.article_no_price);
    }

    public double getPrixDouble() {
        if (prix == R.string.article_no_price)
            return 0;

        String prixS = ResourcesManager.getString(prix);
        return  prixS == null ? 0 : Double.parseDouble(prixS);
    }

    public static String convertPrix(double price) {
        DecimalFormat f = new DecimalFormat("##.00");

        return Translator.formatString(R.string.price_display, f.format(price));
    }
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

    public Article(
            @StringRes int nomId,
            @StringRes int descriptionId,
            String iconName,
            @StringRes int prixId,
            int quantity
    ){
        super(quantity);

        setNom(nomId);
        setDescription(descriptionId);
        setPrix(prixId);

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
