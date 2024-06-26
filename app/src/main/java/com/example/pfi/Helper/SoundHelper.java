package com.example.pfi.Helper;

import android.media.MediaPlayer;
import android.net.Uri;

import androidx.annotation.RawRes;

import com.example.pfi.Config;
import com.example.pfi.Logger;

import java.io.IOException;

/**
 * Useful to play audio files
 */
public abstract class SoundHelper {

    public static MediaPlayer playSound(MediaPlayer mp, @RawRes int sound_file_id) {
        if (mp != null)
            mp.reset();
        else
            mp = new MediaPlayer();

        if (!Config.ENABLE_AUDIO)
            return mp;

        Uri uri = Uri.parse("android.resource://" + ResourcesManager.getContext().getPackageName() + "/raw/" + ResourcesManager.getResources().getResourceEntryName(sound_file_id));

        try {
            mp.setDataSource(ResourcesManager.getContext(), uri);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            Logger.error(e);
        }
        return mp;
    }
}
