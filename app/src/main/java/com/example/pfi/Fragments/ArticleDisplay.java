package com.example.pfi.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pfi.Activities.ActivityListe;
import com.example.pfi.Classes.Article;
import com.example.pfi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleDisplay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleDisplay extends Fragment {

    private static final String ARG_PARAM1 = "ARTICLE";

    public Article article;

    public ArticleDisplay() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ArticleDisplay.
     */
    public static ArticleDisplay newInstance(Article article) {
        ArticleDisplay fragment = new ArticleDisplay();
        Bundle args = new Bundle();

        args.putSerializable(ARG_PARAM1, article);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            article = (Article) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_article_display, container, false);

        // Set values
        TextView nameTV = v.findViewById(R.id.articleDisplay_nameTV);
        nameTV.setText(article.getNom());

        ImageView iconImage = v.findViewById(R.id.articleDisplay_iconImage);
        iconImage.setImageResource(article.getIconId());

        // Set onClick
        v.setOnClickListener(view -> {
            Activity a = this.getActivity();

            //if (a == null)
            //    return;
            if (!(a instanceof ActivityListe))
                return;

            //a.openDialog(article);
            ((ActivityListe) a).openDialog(article);
        });

        return v;
    }
}