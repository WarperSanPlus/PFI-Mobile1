package com.example.pfi.Fragments;

import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pfi.Classes.Article;
import com.example.pfi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleDisplay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleDisplay extends Fragment {

    private static final String ARG_PARAM1 = "ARTICLE_NAME";
    private static final String ARG_PARAM2 = "ARTICLE_ICON";

    private String name;
    private @DrawableRes int iconId;

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

        args.putString(ARG_PARAM1, article.getNom());
        args.putInt(ARG_PARAM2, article.getIconId());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            iconId = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_article_display, container, false);

        TextView nameTV = v.findViewById(R.id.articleDisplay_nameTV);
        nameTV.setText(name);

        return v;
    }
}