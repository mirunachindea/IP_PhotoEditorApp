package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

public interface SubFilter {
    Bitmap process(Bitmap inputImage);

    Object getTag();

    void setTag(Object tag);
}
