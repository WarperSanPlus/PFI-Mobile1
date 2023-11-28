package com.example.pfi.Fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pfi.Classes.Article;
import com.example.pfi.Classes.Category;
import com.example.pfi.Helper.FragmentHelper;
import com.example.pfi.Logger;
import com.example.pfi.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryDisplay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryDisplay extends Fragment {
    private static final String CATEGORY_NAME = "CATEGORY_NAME";
    private static final String CATEGORY_ARTICLES = "CATEGORY_ARTICLES";

    private int name;
    private ArrayList<Article> articles;

    public CategoryDisplay() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CategoryDisplay.
     */
    public static CategoryDisplay newInstance(Category category) {
        CategoryDisplay fragment = new CategoryDisplay();
        Bundle args = new Bundle();

        // Put the name id of the given category in the bundle
        args.putInt(CATEGORY_NAME, category.getNomId());

        // Put the articles of the given category in the bundle
        args.putSerializable(CATEGORY_ARTICLES, category.getArticles());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            name = getArguments().getInt(CATEGORY_NAME);
            articles = (ArrayList<Article>) getArguments().getSerializable(CATEGORY_ARTICLES);
        }
    }


    View articlesGrid;
    boolean isOpened = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MediaPlayer mp = new MediaPlayer();
        View v = inflater.inflate(R.layout.fragment_category_display, container, false);

        TextView categoryName = v.findViewById(R.id.categoryNameTV);
        categoryName.setText(name);
        ImageButton btn_see_Articles = v.findViewById(R.id.category_btn_seeArticles);

        // Create fragments
        FragmentHelper.createFragments(
                R.id.categoryDisplay_articlesGrid,
                articles,
                ArticleDisplay::newInstance,
                getChildFragmentManager()
        );

        articlesGrid = v.findViewById(R.id.categoryDisplay_articlesGrid);

        //click on arrow
        btn_see_Articles.setOnClickListener(view ->{
            //Sound effect
            mp.reset();
            try {
                mp.setDataSource(v.getContext(), Uri.parse("android.resource://" +v.getContext().getPackageName()+ "/raw/liste_see_more_less"));
                mp.prepare();
                mp.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int visibility;
            int backgroundId;

            Logger.log(btn_see_Articles.getBackground().getConstantState());

            //If the button is to see more -> make visible the articles grid and change the image background
            if (!isOpened) {
                visibility = View.VISIBLE;
                backgroundId = R.drawable.category_see_less_article;
            } else { //If not -> make the articles grid invisible and change the image background
                visibility = View.GONE;
                backgroundId = R.drawable.category_see_more_article;
            }

            isOpened = !isOpened;

            articlesGrid.setVisibility(visibility);
            btn_see_Articles.setBackground(getResources().getDrawable(backgroundId));
        });


        // Hide empty categories
        if (articles.size() == 0) {
            v.setVisibility(View.GONE);
        }

        return v;
    }
}