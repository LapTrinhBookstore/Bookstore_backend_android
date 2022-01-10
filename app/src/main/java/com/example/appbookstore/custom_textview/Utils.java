package com.example.appbookstore.custom_textview;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {

    private static Typeface DroidSansMono;
    public static Typeface getDroidSansMono(Context context) {
        if(DroidSansMono == null){
            DroidSansMono = Typeface.createFromAsset(context.getAssets(), "fonts/DroidSansMono.ttf");
        }
        return DroidSansMono;
    }
}