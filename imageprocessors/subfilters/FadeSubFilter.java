package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class FadeSubFilter implements SubFilter {
    private static String tag = "";

    public FadeSubFilter() {

    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.fade(inputImage);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        FadeSubFilter.tag = (String) tag;
    }

}
