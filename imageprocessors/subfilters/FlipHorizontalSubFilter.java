package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class FlipHorizontalSubFilter implements SubFilter {
    private static String tag = "";

    public FlipHorizontalSubFilter() {

    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.flipHorizontally(inputImage);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        FlipHorizontalSubFilter.tag = (String) tag;
    }

}
