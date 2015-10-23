package com.goosef.com.appfirst.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import java.util.Hashtable;

/**
 * Created by nam on 10/5/2015.
 */


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.Hashtable;

public class AppFirstButton extends Button {

    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();
    public AppFirstButton( Context context, AttributeSet attrs, int defStyle ){
        super(context, attrs, defStyle);
    }
    public AppFirstButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppFirstButton(Context context) {
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
