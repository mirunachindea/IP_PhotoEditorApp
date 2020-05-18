package com.example.project2.imageprocessors;

import android.content.Context;

import com.example.project2.imageprocessors.subfilters.Binary2SubFilter;
import com.example.project2.imageprocessors.subfilters.Binary3SubFilter;
import com.example.project2.imageprocessors.subfilters.BinarySubFilter;
import com.example.project2.imageprocessors.subfilters.NoiseSubFilter;
import com.example.project2.imageprocessors.subfilters.FadeSubFilter;
import com.example.project2.imageprocessors.subfilters.GaussianSubFilter;
import com.example.project2.imageprocessors.subfilters.GrayscaleSubFilter;
import com.example.project2.imageprocessors.subfilters.HighPassSubFilter;
import com.example.project2.imageprocessors.subfilters.LaplaceSubFilter;
import com.example.project2.imageprocessors.subfilters.LuminanceSubFilter;
import com.example.project2.imageprocessors.subfilters.NegativeSubFilter;
import com.example.project2.imageprocessors.subfilters.PuzzleSubFilter;
import com.example.project2.imageprocessors.subfilters.RiseSubFilter;
import com.example.project2.imageprocessors.subfilters.SaltSubFilter;
import com.example.project2.imageprocessors.subfilters.VintageSubFilter;

import java.util.ArrayList;
import java.util.List;

public final class FilterPack {

    private FilterPack() {
    }

    /***
     * the filter pack,
     * @param context
     * @return list of filters
     */
    public static List<Filter> getFilterPack(Context context) {
        List<Filter> filters = new ArrayList<>();
        filters.add(getGrayscaleFilter(context));
        filters.add(getNegativeFilter(context));
        filters.add(getBinaryFilter(context));
        filters.add(getBinary2Filter(context));
        filters.add(getBinary3Filter(context));
        filters.add(getLuminantFilter(context));
        filters.add(getRiseFilter(context));
        filters.add(getVintageFilter(context));
        filters.add(getFadeFilter(context));
        filters.add(getPuzzleFilter(context));
        filters.add(getDissolveFilter(context));
        filters.add(getSaltFilter(context));
        filters.add(getGaussianFilter(context));
        filters.add(getLaplaceFilter(context));
        filters.add(gethighPassFilter(context));
        return filters;
    }

    public static Filter getSaltFilter(Context context) {
        Filter filter = new Filter();
        filter.setName("SALT");
        filter.addSubFilter(new SaltSubFilter());
        return filter;
    }

    public static Filter getLaplaceFilter(Context context) {
        Filter filter = new Filter();
        filter.setName("LAPLACE");
        filter.addSubFilter(new LaplaceSubFilter());
        return filter;
    }

    public static Filter gethighPassFilter(Context context) {
        Filter filter = new Filter();
        filter.setName("HIPASS");
        filter.addSubFilter(new HighPassSubFilter());
        return filter;
    }
    public static Filter getGaussianFilter(Context context) {
        Filter filter = new Filter();
        filter.setName("GAUSSIAN");
        filter.addSubFilter(new GaussianSubFilter());
        return filter;
    }

    public static Filter getDissolveFilter(Context context) {
        Filter filter = new Filter();
        filter.setName("NOISE");
        filter.addSubFilter(new NoiseSubFilter());
        return filter;
    }

    public static Filter getPuzzleFilter(Context context) {
        Filter filter = new Filter();
        filter.setName("PUZZLE");
        filter.addSubFilter(new PuzzleSubFilter(6));
        return filter;
    }

    public static Filter getFadeFilter(Context context) {
        Filter filter = new Filter();
        filter.setName("FADE");
        filter.addSubFilter(new FadeSubFilter());
        return filter;
    }

    public static Filter getVintageFilter(Context context) {
        Filter filter = new Filter();
        filter.setName("VINTAGE");
        filter.addSubFilter(new VintageSubFilter());
        return filter;
    }

    public static Filter getRiseFilter(Context context) {
        Filter filter = new Filter();
        filter.setName("RISE");
        filter.addSubFilter(new RiseSubFilter());
        return filter;
    }

    public static Filter getLuminantFilter(Context context) {
        Filter filter = new Filter();
        filter.setName("LUMINANT");
        filter.addSubFilter(new LuminanceSubFilter(0.5f));
        return filter;
    }

    public static Filter getBinaryFilter(Context context) {
        Filter filter = new Filter();
        filter.setName("BINARY I");
        filter.addSubFilter(new BinarySubFilter());
        return filter;
    }

    public static Filter getBinary2Filter(Context context) {
        Filter filter = new Filter();
        filter.setName("BINARY II");
        filter.addSubFilter(new Binary2SubFilter());
        return filter;
    }

    public static Filter getBinary3Filter(Context context) {
        Filter filter = new Filter();
        filter.setName("BINARY III");
        filter.addSubFilter(new Binary3SubFilter());
        return filter;
    }

    public static Filter getGrayscaleFilter(Context context) {
        Filter filter = new Filter();
        filter.setName("GRAYSCALE");
        filter.addSubFilter(new GrayscaleSubFilter());
        return filter;
    }

    public static Filter getNegativeFilter(Context context) {
        Filter filter = new Filter();
        filter.setName("NEGATIVE");
        filter.addSubFilter(new NegativeSubFilter());
        return filter;
    }
}

