package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class RotateLeftSubFilter implements SubFilter {
    private static String tag = "";

    public RotateLeftSubFilter() {

    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.rotateLeft(inputImage);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        RotateLeftSubFilter.tag = (String) tag;
    }

}
