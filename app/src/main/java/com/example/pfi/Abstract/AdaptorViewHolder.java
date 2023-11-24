package com.example.pfi.Abstract;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class AdaptorViewHolder<T> extends RecyclerView.ViewHolder {
    public AdaptorViewHolder(@NonNull View itemView) { super(itemView); }

    public abstract void setItem(T item, int position);
}
