package com.example.pfi.Abstract;

import com.example.pfi.Classes.Article;

import java.io.Serializable;
import java.util.UUID;

public abstract class Product implements Serializable, Comparable<Product> {
    // region UUID
    private String uuid;

    // endregion

    // region Stock

    private int stockRemaining;

    public int getStockAmount() { return stockRemaining; }

    // endregion

    public Product(int stockRemaining) {
        this.stockRemaining = stockRemaining;
        this.uuid = UUID.randomUUID().toString();
    }

    @Override
    public int compareTo(Product o) {
        return this.uuid.compareTo(o.uuid);
    }

}
