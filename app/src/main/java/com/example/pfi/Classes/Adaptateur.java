package com.example.pfi.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfi.R;

import java.util.ArrayList;

public class Adaptateur extends RecyclerView.Adapter<Adaptateur.MyViewHolder>  {
    Context context;
    ArrayList<Article> articlesPanier;

    public Adaptateur(Context context, ArrayList<Article> articlesPanier) {
        this.context = context;
        this.articlesPanier = articlesPanier;
    }
    @NonNull
    @Override
    public Adaptateur.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(context);
        View itemView = inflator.inflate(R.layout.une_item_panier, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull Adaptateur.MyViewHolder holder, int position) {
        Article article = articlesPanier.get(position);

        holder.icon.setImageResource(article.getIconId());
        holder.txtNom.setText(article.getNom());
        holder.txtPrix.setText(article.getPrix());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView txtNom;
        TextView txtPrix;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.item_panier_img);
            txtNom = itemView.findViewById(R.id.item_panier_nom);
            txtPrix = itemView.findViewById(R.id.item_panier_prix);
        }
    }
}
