package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class SaturationSubFilter implements SubFilter {
    private static String tag = "";
    private int saturation;

    public SaturationSubFilter(int saturation) {
        this.saturation = saturation;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.changeSaturation(inputImage, saturation);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        SaturationSubFilter.tag = (String) tag;
    }

}
