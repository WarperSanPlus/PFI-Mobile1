package com.example.pfi.Fragments;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pfi.Classes.Article;
import com.example.pfi.Classes.Category;
import com.example.pfi.Helper.FragmentHelper;
import com.example.pfi.Helper.SoundHelper;
import com.example.pfi.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryListeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryListeFragment extends Fragment {
    private static final String CATEGORY_NAME = "CATEGORY_NAME";
    private static final String CATEGORY_ARTICLES = "CATEGORY_ARTICLES";

    private int name;
    private ArrayList<Article> articles;

    public CategoryListeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CategoryDisplay.
     */
    public static CategoryListeFragment newInstance(Category category) {
        CategoryListeFragment fragment = new CategoryListeFragment();
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

        // Create fragments
        FragmentHelper.createFragments(
            R.id.categoryDisplay_articlesGrid,
            articles,
            ArticleListeFragment::newInstance,
            getChildFragmentManager()
        );

        articlesGrid = v.findViewById(R.id.categoryDisplay_articlesGrid);

        //click on arrow
        ImageView btn_see_Articles = v.findViewById(R.id.category_btn_seeArticles);

        View.OnClickListener onClickListener = v1 -> {
            // Sound effect
            SoundHelper.playSound(mp, R.raw.liste_see_more_less);

            int visibility;
            int backgroundId;

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
        };


        v.findViewById(R.id.category_title).setOnClickListener(onClickListener);
        //btn_see_Articles.setOnClickListener(onClickListener);

        // Hide empty categories
        if (articles.size() == 0) {
            v.setVisibility(View.GONE);
        }

        return v;
    }
}