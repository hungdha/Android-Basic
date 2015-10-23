package com.goosef.com.appfirst.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Hashtable;



import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Hashtable;

/**
 * Created by nam on 9/3/2015.
 */

public class AppFirstTextView extends TextView {
    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();
    public AppFirstTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public AppFirstTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppFirstTextView(Context context) {
        super(context);
    }
    public void setTypeface(Typeface tf, int style) {
        if (style == Typeface.BOLD) {
            super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),    "fonts/Roboto-Bold.ttf"));
        }
        else if(style == Typeface.ITALIC)
        {
            super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Italic.ttf"));
        }
        else
        {
            super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DK Face Your Fears II.ttf"));
        }
    }
}
