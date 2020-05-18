package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class GrayscaleSubFilter implements SubFilter {
    private static String tag = "";

    public GrayscaleSubFilter() {

    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.toGrayScale(inputImage);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        GrayscaleSubFilter.tag = (String) tag;
    }

}
