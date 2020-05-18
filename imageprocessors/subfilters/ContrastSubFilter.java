package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class ContrastSubFilter implements SubFilter {
    private static String tag = "";
    private float contrast;

    public ContrastSubFilter(float contrast) {
        this.contrast = contrast;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.changeContrast(inputImage, contrast);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        ContrastSubFilter.tag = (String) tag;
    }

}
