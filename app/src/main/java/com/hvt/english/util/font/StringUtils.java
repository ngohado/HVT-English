package com.hvt.english.util.font;

import android.widget.TextView;

import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;

import java.util.ArrayList;

/**
 * Created by Hado on 7/5/17.
 */

public class StringUtils {
    public static void setText(TextView textView, String string) {
        if (string == null) {
            textView.setText("");
        } else {
            textView.setText(string);
        }
    }

    //separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "..."
    public static String wordsToString(ArrayList<Word> words) {
        String separator = ", ";
        String prefix = "";
        String postfix = "";
        int limit = -1;
        StringBuilder builder = new StringBuilder();
        builder.append(prefix);
        int count = 0;
        for (Word w : words) {
            if (++count > 1) builder.append(separator);
            if (limit < 0 || count <= limit) {
                builder.append(w.content);
            } else break;
        }
        builder.append(postfix);
        return builder.toString();
    }

    public static String sentencesToString(ArrayList<Sentence> words) {
        String separator = ", ";
        String prefix = "";
        String postfix = "";
        int limit = -1;
        StringBuilder builder = new StringBuilder();
        builder.append(prefix);
        int count = 0;
        for (Sentence w : words) {
            if (++count > 1) builder.append(separator);
            if (limit < 0 || count <= limit) {
                builder.append(w.content);
            } else break;
        }
        builder.append(postfix);
        return builder.toString();
    }


}
