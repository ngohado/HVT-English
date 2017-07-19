package com.hvt.english.util.font;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.hvt.english.util.font.Font.FIRASANS;
import static com.hvt.english.util.font.Font.FIRASANS_BOLD;
import static com.hvt.english.util.font.Font.FIRASANS_BOLD_ITALIC;
import static com.hvt.english.util.font.Font.FIRASANS_ITALIC;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        FIRASANS,
        FIRASANS_BOLD,
        FIRASANS_BOLD_ITALIC,
        FIRASANS_ITALIC
})

public @interface Font {
    String FIRASANS = "MuseoSansRounded-300.otf";
    String FIRASANS_BOLD = "MuseoSansRounded-700.otf";
    String FIRASANS_ITALIC = "FiraSans-Italic.ttf";
    String FIRASANS_BOLD_ITALIC = "FiraSans-BoldItalic.ttf";
}
