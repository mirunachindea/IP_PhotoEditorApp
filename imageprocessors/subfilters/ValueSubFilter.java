package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class ValueSubFilter implements SubFilter {
    private static String tag = "";
    private int value;

    public ValueSubFilter(int value) {
        this.value = value;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.changeValue(inputImage, value);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        ValueSubFilter.tag = (String) tag;
    }

}
