package com.example.pfi.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.pfi.Activities.ActivityProductDetails;
import com.example.pfi.Classes.Article;
import com.example.pfi.Helper.IntentHelper;
import com.example.pfi.Helper.ResourcesManager;
import com.example.pfi.Logger;
import com.example.pfi.R;
import com.example.pfi.Translator;

public class ProductPreviewDialog extends DialogFragment {
    private Article article;

    public ProductPreviewDialog(Article article) {
        this.article = article;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Get the layout inflater.
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Create layout
        View v = inflater.inflate(R.layout.activity_product_preview_dialog, null);

        // Set up the UI
        setArticle(v, article);

        // Add onClick for 'Retour' button
        v.findViewById(R.id.productPreview_retourBtn).setOnClickListener(view -> this.dismiss());

        // Add onClick for 'Plus de détails' button
        v.findViewById(R.id.productPreview_moreDetailsBtn).setOnClickListener(view -> {
            this.dismiss(); // Close dialog

            // Redirect to target
            Bundle extras = new Bundle();
            extras.putSerializable(ActivityProductDetails.SELECTED_ARTICLE, article);

            IntentHelper.moveToActivityWithTransition(this.getActivity(), ActivityProductDetails.class, extras);
        });

        // Create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set the view as the dialog's view
        builder.setView(v);
        return builder.create();
    }

    /**
     * Finds and sets the desired UI from given view for the given article.
     */
    private void setArticle(View v, Article targetArticle) {
        // Set product name textview
        TextView productNameTV = v.findViewById(R.id.productPreview_productName);
        productNameTV.setText(targetArticle.getNom());

        // Set product price textview
        TextView productPriceTV = v.findViewById(R.id.productPreview_productPrice);
        productPriceTV.setText(targetArticle.getPrix());

        // Set product description textview
        TextView productDescriptionTV = v.findViewById(R.id.productPreview_productDescription);
        productDescriptionTV.setText(targetArticle.getDescription());

        // Set product icon imageview
        ImageView productImage = v.findViewById(R.id.productPreview_image);
        productImage.setImageResource(targetArticle.getIconId());

        // Set stock remaining textview
        TextView stockTV = v.findViewById(R.id.productPreview_productStockAmount);
        int quantity = targetArticle.getStockAmount();
        stockTV.setText(Translator.formatString(R.string.article_quantity_remaining, quantity));

        stockTV.setTextColor(ResourcesManager.getColor(quantity <= 3 ? R.color.stock_running_out : R.color.sufficient_stock));

    }
}
