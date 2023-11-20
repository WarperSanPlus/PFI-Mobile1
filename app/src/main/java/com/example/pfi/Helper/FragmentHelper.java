package com.example.pfi.Helper;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.function.Function;

public class FragmentHelper {
    /**
     * Creates fragments for every object in objects by executing the given action.
     */
    public static <T, U extends Fragment> void createFragments(
            @IdRes int parent,
            ArrayList<T> objects,
            Function<T, U> action,
            FragmentActivity src
    ) {
        createFragments(parent, objects, action, src.getSupportFragmentManager());
    }

    public static <T, U extends Fragment>  void createFragments(
            @IdRes int parent,
            ArrayList<T> objects,
            Function<T, U> action,
            FragmentManager fragmentManager
    ){
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Create fragments
        for (T obj : objects)
            transaction.add(parent, action.apply(obj));

        transaction.commit();
    }
}
