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
import com.example.pfi.R;

public class ProductPreviewDialog extends DialogFragment {
    private Article article;

    public ProductPreviewDialog(Article article) {
        this.article = article;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Get the layout inflater.
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.activity_product_preview_dialog, null);

        setArticle(v, article);

        // Set buttons
        v.findViewById(R.id.productPreview_retourBtn).setOnClickListener(view -> this.dismiss());
        v.findViewById(R.id.productPreview_moreDetailsBtn).setOnClickListener(view -> {
            this.dismiss(); // Close dialog

            // Redirect to target
            Bundle extras = new Bundle();
            extras.putSerializable(ActivityProductDetails.SELECTED_ARTICLE, article);

            IntentHelper.moveToActivity(this.getActivity(), ActivityProductDetails.class, extras);
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Inflate and set the layout for the dialog.
        // Pass null as the parent view because it's going in the dialog layout.
        builder.setView(v);

        return builder.create();
    }

    private void setArticle(View v, Article targetArticle) {
        // Set values
        TextView productNameTV = v.findViewById(R.id.productPreview_productName);
        productNameTV.setText(targetArticle.getNom());

        TextView productDescriptionTV = v.findViewById(R.id.productPreview_productDescription);
        productDescriptionTV.setText(targetArticle.getDescription());

        ImageView productImage = v.findViewById(R.id.productPreview_image);
        productImage.setImageResource(targetArticle.getIconId());
    }
}
