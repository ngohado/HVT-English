package com.hvt.english.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Hado on 8/5/17.
 */

public class SoundUtils {
    public static void playSound(Context context, String url, PlayCompleteListener listener) {
        Observable.fromCallable(() -> {
            MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                Uri uri = Uri.parse(url);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 1;
        }).subscribeOn(Schedulers.io()).subscribe(integer -> {
            System.out.println(integer);
        });

    }

    public interface PlayCompleteListener {
        void onComplete();
    }
}
