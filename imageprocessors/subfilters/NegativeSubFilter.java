package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class NegativeSubFilter implements SubFilter {
    private static String tag = "";

    public NegativeSubFilter() {

    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.toNegative(inputImage);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        NegativeSubFilter.tag = (String) tag;
    }

}
