package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class HueSubFilter implements SubFilter {
    private static String tag = "";
    private int hue;

    public HueSubFilter(int hue) {
        this.hue = hue;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.changeHue(inputImage, hue);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        HueSubFilter.tag = (String) tag;
    }

}
