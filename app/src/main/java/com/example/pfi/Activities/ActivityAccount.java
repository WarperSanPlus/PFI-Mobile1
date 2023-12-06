package com.example.pfi.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.pfi.Client;
import com.example.pfi.Helper.IntentHelper;
import com.example.pfi.Helper.ResourcesManager;
import com.example.pfi.R;
import com.example.pfi.databinding.ActivityAccountBinding;


public class ActivityAccount extends AppCompatActivity {
   ActivityAccountBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_account);

        binding = DataBindingUtil.setContentView (ActivityAccount.this, R.layout.activity_account);
        binding.setClient(Client.getInstance());

        binding.accountBtnAdd.setOnClickListener(v ->{
            binding.accountActivityVideoView.setVisibility(View.VISIBLE);
            videoSet(binding.accountActivityVideoView, R.raw.account_add);
        });

        binding.accountBtnPassword.setOnClickListener(v ->{
            showToast(binding.getClient().getPassword());
        });

        binding.accountBtnAddReview.setOnClickListener(v -> {
            binding.accountLayoutRating.setVisibility(View.VISIBLE);

        });
        binding.accountBtnBadRating.setOnClickListener(viewBadRating ->{
            showToast(ResourcesManager.getString(R.string.account_rate_review_bad));
        });
        binding.accountBtnGoodRating.setOnClickListener(viewGoodRating ->{
            showToast(ResourcesManager.getString(R.string.account_rate_review_good));
        });
        binding.btnRetour.setOnClickListener(v -> {
            IntentHelper.closeAndMove(this, ActivityListe.class, null);
        });
    }
    private void showToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void videoSet (VideoView video, int id){
        //MediaController mediaController = new MediaController(this);
        //mediaController.setAnchorView(video);
        Uri url = Uri.parse("android.resource://com.example.pfi/" + id);

        //video.setMediaController(mediaController);
        video.setVideoURI(url);
        video.requestFocus();
        video.start();

        video.setOnCompletionListener(mp -> video.setVisibility(View.INVISIBLE));
    }
}