package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class ToneSubFilter implements SubFilter {
    private static String tag = "";
    private int tone;

    public ToneSubFilter(int tone) {
        this.tone = tone;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.changeTone(inputImage, tone);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        ToneSubFilter.tag = (String) tag;
    }
}
