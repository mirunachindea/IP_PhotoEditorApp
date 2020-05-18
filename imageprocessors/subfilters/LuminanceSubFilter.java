package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class LuminanceSubFilter implements SubFilter {
    private static String tag = "";
    private float gamma = 0;

    public LuminanceSubFilter(float gamma) {
        this.gamma = gamma;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.gammaCorrection(inputImage, gamma);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        LuminanceSubFilter.tag = (String) tag;
    }


}
