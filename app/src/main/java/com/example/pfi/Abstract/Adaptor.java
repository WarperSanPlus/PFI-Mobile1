package com.example.pfi.Abstract;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfi.Classes.ArticleViewHolder;
import com.example.pfi.Logger;
import com.example.pfi.R;

import java.util.ArrayList;

/**
 * @param <T> Objets gérés
 * @param <U> View Holder
 */
public abstract class Adaptor<T, U extends AdaptorViewHolder<T>> extends RecyclerView.Adapter<U> {
    private Context ctx;
    private ArrayList<T> items = new ArrayList<>();

    public Adaptor(Context ctx, ArrayList<T> items) {
        this.ctx = ctx;
        this.items.addAll(items);
    }

    @Override
    public void onBindViewHolder(@NonNull U holder, int position) { holder.setItem(items.get(position), position); }

    @Override
    public int getItemCount() { return items.size(); }

    @NonNull
    @Override
    public U onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(ctx);
        View itemView = inflator.inflate(getLayout(), parent, false);
        return getViewHolder(itemView);
    }

    protected abstract @LayoutRes int getLayout();
    protected abstract U getViewHolder(View itemView);
}
