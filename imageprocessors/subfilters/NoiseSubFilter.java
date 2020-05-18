package com.example.project2.imageprocessors.subfilters;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.project2.imageprocessors.ImageProcessor;

public class NoiseSubFilter implements SubFilter {
    private static String tag = "";

    public NoiseSubFilter() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.noise(inputImage);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        NoiseSubFilter.tag = (String) tag;
    }

}
