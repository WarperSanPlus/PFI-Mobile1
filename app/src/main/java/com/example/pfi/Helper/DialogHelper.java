package com.example.pfi.Helper;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.pfi.Config;
import com.example.pfi.Logger;

import java.util.concurrent.Callable;

public abstract class DialogHelper {
    /**
     * Opens the dialog created by the callable and shows it.
     * @param createDialog
     * @param fragmentManager
     * @param tag
     */
    public static void openDialog(
            Callable<DialogFragment> createDialog,
            FragmentManager fragmentManager,
            String tag
    ) {
        try {
            // Get the dialog
            DialogFragment dialog = createDialog.call();

            // Show the dialog
            dialog.show(fragmentManager, tag);

            // Audit if wanted
            if (Config.AUDIT_DIALOG_OPEN)
                Logger.log("Dialog \"" + dialog.getClass().getName() + "\" opened with the tag \"" + tag + "\".");
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }
}
