package com.example.pfi.Abstract;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @param <T> Objets gérés
 * @param <U> View Holder
 */
public abstract class Adaptor<T, U extends AdaptorViewHolder<T>> extends RecyclerView.Adapter<U> {
    private Context ctx;
    private ArrayList<T> items;

    public Adaptor(Context ctx, ArrayList<T> items) {
        this.ctx = ctx;
        this.items = items;
    }

    @Override
    public void onBindViewHolder(@NonNull U holder, int position) { holder.setItem(items.get(position), position); }

    @Override
    public int getItemCount() { return items.size(); }

    @NonNull
    @Override
    public U onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(ctx);
        return createViewHolder(parent, inflator);
    }

    public abstract U createViewHolder(@NonNull ViewGroup parent, LayoutInflater inflator);
}
