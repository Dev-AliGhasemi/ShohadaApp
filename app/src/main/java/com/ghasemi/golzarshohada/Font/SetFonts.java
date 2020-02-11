package com.ghasemi.golzarshohada.Font;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Computer Freak on 7/17/2018.
 */

public class SetFonts {
    private Context context;
    private String fontPath;
    private Typeface font;
    public SetFonts(Context context, String fontPath) {
        this.context = context;
        this.fontPath = fontPath;
        font = Typeface.createFromAsset(context.getAssets(),fontPath);
    }
    public void setFont(String Object,View view)
    {
        switch (Object)
        {
            case "TextView":
                TextView textView = (TextView) view;
                textView.setTypeface(font);
                break;
            case "Button":
                Button button = (Button) view;
                button.setTypeface(font);
                break;
            case "EditText":
                EditText editText = (EditText) view;
                editText.setTypeface(font);
                break;
        }
    }
}
