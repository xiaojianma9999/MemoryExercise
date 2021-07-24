package com.xiaojianma.memoryexercise;

import android.graphics.drawable.Drawable;

public class NumberImage {

    private String numberText;

    private Drawable drawable;

    public NumberImage(String numberText, Drawable drawable) {
        this.numberText = numberText;
        this.drawable = drawable;
    }

    public String getNumberText() {
        return numberText;
    }

    public void setNumberText(String numberText) {
        this.numberText = numberText;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
