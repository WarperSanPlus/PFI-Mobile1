package com.example.pfi.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pfi.Article;
import com.example.pfi.Classes.Category;
import com.example.pfi.Helper.FragmentHelper;
import com.example.pfi.R;

import java.util.ArrayList;

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

        args.putInt(CATEGORY_NAME, category.getNomId());
        args.putSerializable(CATEGORY_ARTICLES, category.getArticles());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() == null)
            return;

        name = getArguments().getInt(CATEGORY_NAME);
        articles = (ArrayList<Article>) getArguments().getSerializable(CATEGORY_ARTICLES);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_category_display, container, false);

        TextView categoryName = v.findViewById(R.id.categoryNameTV);
        categoryName.setText(name);

        // Create fragments
        FragmentHelper.createFragments(
                R.id.categoryDisplay_articlesGrid,
                articles,
                ArticleDisplay::newInstance,
                getChildFragmentManager()
        );

        // Hide empty categories
        if (articles.size() == 0) {
            v.setVisibility(View.GONE);
        }

        return v;
    }
}