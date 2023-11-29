package com.example.pfi.Abstract;

import java.io.Serializable;
import java.util.UUID;

public abstract class Product implements Serializable, Comparable<Product> {
    // region UUID
    private final String uuid; // Unique ID
    // endregion
    // region Stock
    private int stockRemaining;

    public int getStockAmount() { return stockRemaining; }
    // endregion

    public Product(int stockRemaining) {
        this.stockRemaining = stockRemaining;
        this.uuid = UUID.randomUUID().toString();
    }

    // region Override
    @Override
    public int compareTo(Product o) {
        return this.uuid.compareTo(o.uuid);
    }
    // endregion
}
