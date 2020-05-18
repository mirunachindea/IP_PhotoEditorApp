package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class LaplaceSubFilter implements SubFilter {
    private static String tag = "";

    public LaplaceSubFilter() {

    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.laplaceFilter(inputImage);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        LaplaceSubFilter.tag = (String) tag;
    }

}
