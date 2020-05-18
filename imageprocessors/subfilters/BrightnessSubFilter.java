package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class BrightnessSubFilter implements SubFilter {
    private static String tag = "";
    private int brightness;

    public BrightnessSubFilter(int brightness) {
        this.brightness = brightness;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.changeBrightness(inputImage, brightness);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        BrightnessSubFilter.tag = (String) tag;
    }

}
