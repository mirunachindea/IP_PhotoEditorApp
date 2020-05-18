package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.example.project2.imageprocessors.ImageProcessor;

public class PuzzleSubFilter implements SubFilter {
    private static String tag = "";
    private int numLevels;

    public PuzzleSubFilter(int numLevels) {
        this.numLevels = numLevels;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.puzzle(inputImage, numLevels);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        PuzzleSubFilter.tag = (String) tag;
    }


}
