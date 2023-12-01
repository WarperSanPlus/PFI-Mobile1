package com.example.pfi.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.example.pfi.Classes.PanierArticle;
import com.example.pfi.Config;
import com.example.pfi.Helper.ResourcesManager;
import com.example.pfi.Helper.StringHelper;
import com.example.pfi.R;

public class ConfirmationAchatsDialog extends DialogFragment  {
    private final PanierArticle panier;
    public ConfirmationAchatsDialog(PanierArticle panier) { this.panier = panier;}

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Get the layout inflater.
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Create layout
        View v = inflater.inflate(R.layout.activity_confirmation_achats, null);


        //---------------Buttons-------------------//
        // Add onClick for 'Non' button   ->user not buying
        v.findViewById(R.id.confirmation_btn_non).setOnClickListener(view -> this.dismiss());

        // Add onClick for 'Oui' button   ->user buying
        v.findViewById(R.id.confirmation_btn_oui).setOnClickListener(view -> {
            String total = panier.getTotal();
            panier.consume();

            FragmentActivity src = getActivity();

            Toast.makeText(
                    src,
                    StringHelper.formatString(R.string.purchase_confirmed, total),
                    Toast.LENGTH_LONG
            ).show();

            if (src == null)
                return;

            if (Config.ENABLE_CONFIRM_PURCHASE_VIDEO) {
                VideoView video = v.findViewById(R.id.confirmation_video);
                videoSet(video, src);
                video.setVisibility(View.VISIBLE);
            }
            else
                src.finish();
        });

        //------------Create Dialog---------------//
        // Create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set the view as the dialog's view
        builder.setView(v);
        return builder.create();
    }
    private void videoSet (VideoView video, Activity src){

        MediaController mediaController = new MediaController(src.getApplicationContext());
        mediaController.setAnchorView(video);
        Uri url = Uri.parse("android.resource://com.example.pfi/" + R.raw.confirmation_achat_success);

        video.setMediaController(mediaController);
        video.setVideoURI(url);
        video.requestFocus();
        video.start();

        video.setOnCompletionListener(mp -> src.finish());
    }
}


