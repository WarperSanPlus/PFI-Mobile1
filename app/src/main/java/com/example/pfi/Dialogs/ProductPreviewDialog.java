package com.example.pfi.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.pfi.Article;
import com.example.pfi.R;
import com.example.pfi.databinding.ActivityProductPreviewDialogBinding;

public class ProductPreviewDialog extends DialogFragment {
    private Article article;

    public ProductPreviewDialog(Article article) {
        this.article = article;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater.
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Get databinding
        ActivityProductPreviewDialogBinding b = DataBindingUtil.inflate(inflater, R.layout.activity_product_preview_dialog, null, false);

        b.setArticle(article);

        View v = b.getRoot(); //inflater.inflate(R.layout.activity_product_preview_dialog, null);

        v.findViewById(R.id.productPreview_retourBtn).setOnClickListener(view -> this.dismiss());

        // Inflate and set the layout for the dialog.
        // Pass null as the parent view because it's going in the dialog layout.
        builder.setView(v);

        return builder.create();

    }
}
