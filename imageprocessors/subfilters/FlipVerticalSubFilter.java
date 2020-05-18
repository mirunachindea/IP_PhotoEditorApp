package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class FlipVerticalSubFilter implements SubFilter {
    private static String tag = "";

    public FlipVerticalSubFilter() {

    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.flipVertically(inputImage);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        FlipVerticalSubFilter.tag = (String) tag;
    }

}
