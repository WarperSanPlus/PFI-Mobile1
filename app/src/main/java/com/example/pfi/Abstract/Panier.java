package com.example.pfi.Abstract;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Panier<T> {
    protected ArrayList<T> items = new ArrayList<>();

    // region Add Item(s)
    public void addItem(T item) {
        items.add(item);
    }

    public void addItems(Collection<? extends T> items){
        this.items.addAll(items);
    }
    // endregion
}
