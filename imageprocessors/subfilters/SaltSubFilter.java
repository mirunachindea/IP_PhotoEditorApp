package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class SaltSubFilter implements SubFilter {
    private static String tag = "";

    public SaltSubFilter() {

    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.salt(inputImage);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        SaltSubFilter.tag = (String) tag;
    }

}
