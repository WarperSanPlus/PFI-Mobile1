package com.example.pfi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pfi.Helper.HeaderBarHelper;
import com.example.pfi.R;

public class ActivityProductDetails extends AppCompatActivity {

    public static final String SELECTED_ARTICLE = "SelectedArticle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        //HeaderBarHelper.setHeaderBar(this, R.string.category_fruits);
    }
}