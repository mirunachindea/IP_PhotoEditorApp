package com.example.project2.imageprocessors;


import android.graphics.Bitmap;

import com.example.project2.imageprocessors.subfilters.SubFilter;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    private List<SubFilter> subFilters = new ArrayList<>();
    private String name;

    public Filter(Filter filter) {
        this.subFilters = filter.subFilters;
    }

    public Filter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a Subfilter to the Main Filter
     *
     * @param subFilter Subfilter like contrast, brightness, tone Curve etc. subfilter
     * @see com.example.project2.imageprocessors.subfilters.BrightnessSubFilter
     * @see com.example.project2.imageprocessors.subfilters.ContrastSubFilter
     * @see com.example.project2.imageprocessors.subfilters.ToneSubFilter
     * @see com.example.project2.imageprocessors.subfilters.SaturationSubFilter
     * @see com.example.project2.imageprocessors.subfilters.NegativeSubFilter
     * @see com.example.project2.imageprocessors.subfilters.BinarySubFilter
     * @see com.example.project2.imageprocessors.subfilters.Binary2SubFilter
     * @see com.example.project2.imageprocessors.subfilters.Binary3SubFilter
     * @see com.example.project2.imageprocessors.subfilters.LuminanceSubFilter
     * @see com.example.project2.imageprocessors.subfilters.RiseSubFilter
     * @see com.example.project2.imageprocessors.subfilters.VintageSubFilter
     * @see com.example.project2.imageprocessors.subfilters.FadeSubFilter
     * @see com.example.project2.imageprocessors.subfilters.PuzzleSubFilter
     * @see com.example.project2.imageprocessors.subfilters.NoiseSubFilter
     * @see com.example.project2.imageprocessors.subfilters.RotateRightSubFilter
     * @see com.example.project2.imageprocessors.subfilters.RotateLeftSubFilter
     * @see com.example.project2.imageprocessors.subfilters.FlipHorizontalSubFilter
     * @see com.example.project2.imageprocessors.subfilters.FlipVerticalSubFilter
     * @see com.example.project2.imageprocessors.subfilters.GaussianSubFilter
     * @see com.example.project2.imageprocessors.subfilters.LaplaceSubFilter
     * @see com.example.project2.imageprocessors.subfilters.SaltSubFilter
     * @see com.example.project2.imageprocessors.subfilters.HighPassSubFilter
     */
    public void addSubFilter(SubFilter subFilter) {
        subFilters.add(subFilter);
    }

    /**
     * Give the output Bitmap by applying the defined filter
     *
     * @param inputImage Input Bitmap on which filter is to be applied
     * @return filtered Bitmap
     */
    public Bitmap processFilter(Bitmap inputImage) {
        Bitmap outputImage = inputImage;
        if (outputImage != null) {
            for (SubFilter subFilter : subFilters) {
                try {
                    outputImage = subFilter.process(outputImage);
                } catch (OutOfMemoryError oe) {
                    System.gc();
                    try {
                        outputImage = subFilter.process(outputImage);
                    } catch (OutOfMemoryError ignored) {
                    }
                }
            }
        }

        return outputImage;
    }

}