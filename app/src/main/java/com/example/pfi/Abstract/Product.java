package com.example.pfi.Abstract;

import com.example.pfi.Classes.Article;

import java.io.Serializable;

public abstract class Product implements Serializable {
    // region Stock

    private int stockRemaining;

    public int getStockAmount() { return stockRemaining; }

    // endregion

    public Product(int stockRemaining) {
        this.stockRemaining = stockRemaining;
    }
}
