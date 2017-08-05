package com.hvt.english.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by Hado on 8/5/17.
 */

public class SoundUtils {
    public static void playSound(Context context, String url, PlayCompleteListener listener) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        Uri uri = Uri.parse(url);
        try {
            mediaPlayer.setOnCompletionListener(mp -> {
                mediaPlayer.reset();
                mediaPlayer.release();
                listener.onComplete();
            });
            mediaPlayer.setDataSource(context, uri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface PlayCompleteListener {
        void onComplete();
    }
}
