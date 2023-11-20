package com.example.pfi.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pfi.Classes.Article;
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
    private static final String ARG_PARAM1 = "CATEGORY_NAME";

    private int name;

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

        args.putInt(ARG_PARAM1, category.getNomId());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_category_display, container, false);

        TextView categoryName = v.findViewById(R.id.categoryNameTV);
        categoryName.setText(name);

        // categoryDisplay_articlesGrid
        // Get articles
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article(R.string.category_meat, R.string.category_diary));
        articles.add(new Article(R.string.category_cereals, R.string.category_diary));
        articles.add(new Article(R.string.category_cereals, R.string.category_diary));
        articles.add(new Article(R.string.category_cereals, R.string.category_diary));
        articles.add(new Article(R.string.category_cereals, R.string.category_diary));

        // Create fragments
        FragmentHelper.createFragments(
                R.id.categoryDisplay_articlesGrid,
                articles,
                ArticleDisplay::newInstance,
                getChildFragmentManager()
        );

        return v;
    }
}