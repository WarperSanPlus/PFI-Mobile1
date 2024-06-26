package com.example.pfi.Helper;

import android.app.Activity;
import android.content.Context;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfi.Abstract.Adaptor;
import com.example.pfi.Abstract.AdaptorViewHolder;

import java.util.ArrayList;

import kotlin.jvm.functions.Function2;

/**
 * Useful for setting a recycler view
 */
public abstract class RecyclerViewHelper {
    public static <T, U extends AdaptorViewHolder<T>> Adaptor<T, U> setRecyclerView(
            Activity activity,
            RecyclerView recyclerView,
            ArrayList<T> items,
            Function2<Context, ArrayList<T>, Adaptor<T, U>> getAdaptor
    ) {
        Adaptor<T, U> adaptor = getAdaptor.invoke(activity, items);
        recyclerView.setAdapter(adaptor);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);

        return adaptor;
    }
}
