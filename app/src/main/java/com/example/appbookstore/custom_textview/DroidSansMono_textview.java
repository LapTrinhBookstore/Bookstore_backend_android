package com.example.appbookstore.custom_textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class DroidSansMono_textview extends AppCompatTextView {

    public DroidSansMono_textview(@NonNull Context context) {
        super(context);
        setFontsTextview();
    }

    public DroidSansMono_textview(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFontsTextview();
    }

    public DroidSansMono_textview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontsTextview();
    }

    private void setFontsTextview(){
        Typeface typeface = Utils.getDroidSansMono(getContext());
        setTypeface(typeface);
    }
}