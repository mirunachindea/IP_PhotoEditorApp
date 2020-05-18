package com.example.project2.imageprocessors;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.Random;

public class ImageProcessor{

    /**
     * Grayscale filter
     * @param bitmap input image
     * @return modified image
     **/
    public static Bitmap toGrayScale(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int pixel = bitmap.getPixel(i,j);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);
                int gray = (r + g + b) / 3;
                bmpGrayscale.setPixel(i,j, Color.argb(a, gray, gray, gray));
            }
        }

        return bmpGrayscale;

    }

    /**
     * Negative filter
     * @param bitmap input image
     * @return modified image
     **/
    public static Bitmap toNegative(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap bmpNegative = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int pixel = bitmap.getPixel(i,j);
                int a = Color.alpha(pixel);
                int r = 255 - Color.red(pixel);
                int g = 255 - Color.green(pixel);
                int b = 255 - Color.blue(pixel);

                bmpNegative.setPixel(i,j, Color.argb(a, r, g, b));
            }
        }

        return bmpNegative;
    }

    /**
     * Binary filter I
     * @param bitmap input image
     * @return modified image
     **/
    public static Bitmap toBinary(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap grayScale = ImageProcessor.toGrayScale(bitmap);
        Bitmap bmpBin = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);


        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int pixel  = bitmap.getPixel(i,j);
                if(Color.blue(pixel) < 150) {
                    bmpBin.setPixel(i, j, Color.argb(Color.alpha(pixel), 0, 0, 0));
                }
                else {
                    bmpBin.setPixel(i, j, Color.argb(Color.alpha(pixel), 255,255,255));
                }
            }
        }

        return bmpBin;
    }

    /**
     * Binary filter II
     * @param bitmap input image
     * @return modified image
     **/
    public static Bitmap toBinary2(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap grayScale = ImageProcessor.toGrayScale(bitmap);
        Bitmap bmpBin = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);


        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int pixel  = bitmap.getPixel(i,j);
                if(Color.blue(pixel) < 150) {
                    bmpBin.setPixel(i, j, Color.argb(Color.alpha(pixel), 137, 54, 181));
                }
                else {
                    bmpBin.setPixel(i, j, Color.argb(Color.alpha(pixel), 252,220,56));
                }
            }
        }

        return bmpBin;
    }

    /**
     * Binary filter III
     * @param bitmap input image
     * @return modified image
     **/
    public static Bitmap toBinary3(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap grayScale = ImageProcessor.toGrayScale(bitmap);
        Bitmap bmpBin = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);


        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int pixel  = bitmap.getPixel(i,j);
                if(Color.blue(pixel) < 150) {
                    bmpBin.setPixel(i, j, Color.argb(Color.alpha(pixel), 56, 85, 252));
                }
                else {
                    bmpBin.setPixel(i, j, Color.argb(Color.alpha(pixel), 255,188,117));
                }
            }
        }

        return bmpBin;
    }

    /**
     * Gamma correction
     * @param bitmap input image
     * @param gamma gamma coefficient
     * @return modified image
     **/
    public static Bitmap gammaCorrection(Bitmap bitmap, float gamma){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap bmpNew = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int pixel = bitmap.getPixel(i,j);
                int a = Color.alpha(pixel);
                int r = minimum((int)(Math.pow((Color.red(pixel)/255.0), gamma) * 255), 255);
                int g = minimum((int)(Math.pow((Color.green(pixel)/255.0), gamma) * 255), 255);
                int b = minimum((int)(Math.pow((Color.blue(pixel)/255.0), gamma) * 255), 255);

                bmpNew.setPixel(i,j, Color.argb(a, r, g, b));

            }
        }

        return bmpNew;
    }

    /**
     * RGB to BGR
     * @param bitmap input image
     * @return modified image
     **/
    public static Bitmap toBGR(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap bmpNegative = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int pixel = bitmap.getPixel(i,j);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);

                bmpNegative.setPixel(i,j, Color.argb(a, b, g, r));
            }
        }

        return bmpNegative;
    }

    /**
     * Vintage filter
     * @param bitmap input image
     * @return modified image
     **/
    public static Bitmap vintageFilter(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap bmpNegative = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int pixel = bitmap.getPixel(i,j);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);

                int nr = minimum(255, (int)(r * 0.393 + g * 0.769 + b * 0.189));
                int ng = minimum(255, (int)(r * 0.349 + g * 0.686 + b * 0.168));
                int nb = minimum(255, (int)(r * 0.272 + g * 0.534 + b * 0.131));

                bmpNegative.setPixel(i,j, Color.argb(a, nr, ng, nb));
            }
        }

        return bmpNegative;
    }

    /**
     * Faded filter
     * @param bitmap input image
     * @return modified image
     **/
    public static Bitmap fade(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap bmpNegative = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int pixel = bitmap.getPixel(i,j);
                int a = Color.alpha(pixel);
                int r = (255 + Color.red(pixel))/2;
                int g = (255 + Color.green(pixel))/2;
                int b = (255 + Color.blue(pixel))/2;

                bmpNegative.setPixel(i,j, Color.argb(a, r, g, b));
            }
        }

        return bmpNegative;
    }

    /**
     * Level reduction filter
     * @param bitmap input image
     * @param numLevels number of levels
     * @return modified image
     **/
    public static Bitmap puzzle(Bitmap bitmap, int numLevels){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        Bitmap bmpNegative = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        int[] levels = new int[256];
        if (numLevels != 1)
            for (int i = 0; i < 256; i++)
                levels[i] = 255 * (numLevels*i / 256) / (numLevels-1);

        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int pixel = bitmap.getPixel(i,j);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);
                r = levels[r];
                g = levels[g];
                b = levels[b];
                bmpNegative.setPixel(i,j, Color.argb(a, r, g, b));
            }
        }

        return bmpNegative;
    }

    /**
     * Noise filter
     * @param bitmap input image
     * @return modified image
     **/
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Bitmap noise(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        Bitmap dst = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        double min = 0.8;
        double max = 1.2;

        Random rand = new Random();
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int pixel = bitmap.getPixel(i,j);
                double randNoise = min + rand.nextFloat() * (max - min);
                double r = minimum(255, Color.red(pixel) * randNoise);
                double g = minimum(255, Color.green(pixel) * randNoise);
                double b = minimum(255, Color.blue(pixel) * randNoise);
                dst.setPixel(i,j, Color.rgb((float) r, (float) g, (float) b));
            }
        }
        return dst;
    }

    /**
     * Salt & pepper noise filter
     * @param bitmap input image
     * @return modified image
     **/
    public static Bitmap salt(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        Bitmap dst  = bitmap.copy(Bitmap.Config.RGB_565, true);

        double noiseProbability = 0.2;
        double noisePoints = noiseProbability * height * width;
        Random rand = new Random();
        for(int i=0; i<noisePoints; i+=1){
            int row = rand.nextInt(height);
            int col = rand.nextInt(width);
            int val = (rand.nextBoolean()) ? 255 : 0;
            dst.setPixel(col, row, Color.rgb(val, val, val));
        }
        return dst;
    }

    /**
     * Gaussian filter for blurring
     * @param bitmap input image
     * @return modified image
     **/
    public static Bitmap gaussianFilter(Bitmap bitmap){
        int[][] gaussianFilterData = {
                {1,2,1},
                {2,4,2},
                {1,2,1}};

        return convolution(gaussianFilterData, bitmap);
    }

    /**
     * Laplace filter for edge detection
     * @param bitmap input image
     * @return modified image
     **/
    public static Bitmap laplaceFilter(Bitmap bitmap){
        int[][] laplaceFilterData = {
                {-1,-1,-1},
                {-1,8,-1},
                {-1,-1,-1}};

        return convolution(laplaceFilterData, bitmap);
    }

    /**
     * High-pass filter for sharpening
     * @param bitmap input image
     * @return modified image
     **/
    public static Bitmap highPassFilter(Bitmap bitmap){
        int[][] highPassFilterData = {
                {-1,-1,-1},
                {-1,9,-1},
                {-1,-1,-1}};

        return convolution(highPassFilterData, bitmap);
    }

    /**
     * Convolution operation
     * @param src input image
     * @param filter convolution kernel
     * @return modified image
     **/
    // Convolution
    public static Bitmap convolution(int[][] filter, Bitmap src){
        int height = src.getHeight();
        int width = src.getWidth();
        //Bitmap dst = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Bitmap dst = src.copy(src.getConfig(), true);
        int n = filter.length;

        // decide if the filter is high-pass or low-pass
        // low-passs 0
        // high-pass 1
        int passType = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (filter[i][j]<0)
                    passType = 1;
            }
        }

        // LOW PASS
        if (passType == 0) {
            // compute scaling coefficient and addition factor for low pass and high pass
            // low pass: additionFactor = 0, scalingCoeff = sum of all elements
            int scalingCoeff = 0;
            int additionFactor = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    scalingCoeff += filter[i][j];
                }
            }

            // do not forget to divide with the scaling factor and add the addition factor in order to have values between [0, 255]
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int sumr = 0, sumg = 0, sumb = 0;
                    for (int u = 0; u < n; u++) {
                        for (int v = 0; v < n; v++) {
                            if (i + u < width && j + v < height) {
                                int pixel = src.getPixel(i + u - additionFactor,j + v - additionFactor);
                                int r = Color.red(pixel);
                                int g = Color.green(pixel);
                                int b = Color.blue(pixel);
                                sumr += filter[u][v] * r;
                                sumg += filter[u][v] * g;
                                sumb += filter[u][v] * b;
                            }
                        }
                    }
                    sumr /= scalingCoeff;
                    sumg /= scalingCoeff;
                    sumb /= scalingCoeff;
                    dst.setPixel(i,j, Color.rgb(sumr, sumg, sumb));
                }
            }
        }
        else {
            int additionFactor = 0;

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int sumr = 0, sumg = 0, sumb = 0;
                    for (int u = 0; u < n; u++) {
                        for (int v = 0; v < n; v++) {
                            if (i + u < width && j + v < height) {
                                int pixel = src.getPixel(i + u - additionFactor,j + v - additionFactor);
                                int r = Color.red(pixel);
                                int g = Color.green(pixel);
                                int b = Color.blue(pixel);
                                sumr += filter[u][v] * r;
                                sumg += filter[u][v] * g;
                                sumb += filter[u][v] * b;
                            }
                        }
                    }
                    sumr =  maximum(0, minimum(255, sumr));
                    sumg =  maximum(0, minimum(255, sumr));
                    sumb =  maximum(0, minimum(255, sumr));
                    dst.setPixel(i,j, Color.rgb(sumr, sumg, sumb));
                }
            }
        }

        return dst;
    }

    // -----------------------------------------------------------------------------------

    // Edit mode

    /**
     * Change brightness
     * @param bitmap input image
     * @param value constant
     * @return modified image
     */
    public static Bitmap changeBrightness(Bitmap bitmap, int value){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap bmpNew = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int pixel = bitmap.getPixel(i,j);
                int a = Color.alpha(pixel);
                if(value > 0) {
                    int r = minimum(Color.red(pixel) + value, 255);
                    int g = minimum(Color.green(pixel) + value, 255);
                    int b = minimum(Color.blue(pixel) + value, 255);
                    bmpNew.setPixel(i,j, Color.argb(a, r, g, b));
                }
                else {
                    int r = maximum(Color.red(pixel) + value, 0);
                    int g = maximum(Color.green(pixel) + value, 0);
                    int b = maximum(Color.blue(pixel) + value, 0);
                    bmpNew.setPixel(i,j, Color.argb(a, r, g, b));
                }

            }
        }

        return bmpNew;
    }

    /**
     * Change contrast
     * @param bitmap input image
     * @param value constant
     * @return modified image
     */
    public static Bitmap changeContrast(Bitmap bitmap, float value){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap bmpNew = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int pixel = bitmap.getPixel(i,j);
                int a = Color.alpha(pixel);
                int r = minimum((int)(Color.red(pixel) * value), 255);
                int g = minimum((int)(Color.green(pixel) * value), 255);
                int b = minimum((int)(Color.blue(pixel) * value), 255);

                bmpNew.setPixel(i,j, Color.argb(a, r, g, b));

            }
        }

        return bmpNew;
    }

    /**
     * Change tone
     * @param bitmap input image
     * @param value constant
     * @return modified image
     */
    public static Bitmap changeTone(Bitmap bitmap, int value) {
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap bmpNew = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = bitmap.getPixel(i, j);
                int a = Color.alpha(pixel);
                int g = Color.green(pixel);
                int r;
                int b;
                // warm
                if (value > 0) {
                    r = minimum(255, Color.red(pixel) + value);
                    b = maximum(0, Color.blue(pixel) - value);
                }
                else {
                    r = maximum(0, Color.red(pixel) + value);
                    b = minimum(255, Color.blue(pixel) - value);
                }

                bmpNew.setPixel(i, j, Color.argb(a, r, g, b));
            }
        }

        return bmpNew;
    }

    /**
     * Change saturation
     * @param bitmap input image
     * @param value constant
     * @return modified image
     */
    public static Bitmap changeSaturation(Bitmap bitmap, int value){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Mat src_rgba = new Mat(height, width, CvType.CV_8UC4);
        Utils.bitmapToMat(bitmap, src_rgba);

        Mat src = new Mat(height, width, CvType.CV_8UC3);
        Imgproc.cvtColor(src_rgba, src, Imgproc.COLOR_RGBA2RGB);


        Mat hsv = new Mat(height, width, CvType.CV_8UC3);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                double[] pixel = src.get(i,j);
                double b = pixel[0] / 255;
                double g = pixel[1] / 255;
                double r = pixel[2] / 255;


                double h = 0,  s, v;
                double M = maximum(r,g);
                M = maximum(M,b);
                double m = minimum(r,g);
                m = minimum(m,b);
                double C = M - m;

                // Value
                v = M;

                // Saturation
                s = (M != 0) ? (C / M) : 0;

                // Hue
                if (C != 0) {
                    if (M == r) h = 60 * (g - b) / C;
                    if (M == g) h = 120 + 60 * (b - r) / C;
                    if (M == b) h = 240 + 60 * (r - g) / C;
                }
                else { // grayscale
                    h = 0;
                }
                if (h < 0) {
                    h += 360;
                }

//                int vFin = Math.round(v * 255);
//                int sFin = minimum(Math.round(s * 255), 255);
//                int hFin = Math.round(h * 180 / 360);
//                bmpNew.setPixel(i,j, Color.rgb(v * 255, s * 255, h * 180 / 360));

                double[] data = {h * 180 / 360,s * 255 + value,v * 255};
                hsv.put(i,j,data);
            }
        }

        // hsv mat RGBA


        // hsv mat RGB
        //Mat hsvMat = new Mat(height, width, CvType.CV_8UC3);
        //Imgproc.cvtColor(hsvMata, hsvMat, Imgproc.COLOR_RGBA2RGB);

        // rgb mat
        Mat finalMat = new Mat(height, width, CvType.CV_8UC3);
        Imgproc.cvtColor(hsv, finalMat, Imgproc.COLOR_HSV2BGR, 0);

        // bitmap
        Bitmap finalBmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Utils.matToBitmap(finalMat, finalBmp);

        return finalBmp;
    }

    /**
     * Change hue
     * @param bitmap input image
     * @param value constant
     * @return modified image
     */
    public static Bitmap changeHue(Bitmap bitmap, int value){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Mat src_rgba = new Mat(height, width, CvType.CV_8UC4);
        Utils.bitmapToMat(bitmap, src_rgba);

        Mat src = new Mat(height, width, CvType.CV_8UC3);
        Imgproc.cvtColor(src_rgba, src, Imgproc.COLOR_RGBA2RGB);


        Mat hsv = new Mat(height, width, CvType.CV_8UC3);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                double[] pixel = src.get(i,j);
                double b = pixel[0] / 255;
                double g = pixel[1] / 255;
                double r = pixel[2] / 255;


                double h = 0,  s, v;
                double M = maximum(r,g);
                M = maximum(M,b);
                double m = minimum(r,g);
                m = minimum(m,b);
                double C = M - m;

                // Value
                v = M;

                // Saturation
                s = (M != 0) ? (C / M) : 0;

                // Hue
                if (C != 0) {
                    if (M == r) h = 60 * (g - b) / C;
                    if (M == g) h = 120 + 60 * (b - r) / C;
                    if (M == b) h = 240 + 60 * (r - g) / C;
                }
                else { // grayscale
                    h = 0;
                }
                if (h < 0) {
                    h += 360;
                }

//                int vFin = Math.round(v * 255);
//                int sFin = minimum(Math.round(s * 255), 255);
//                int hFin = Math.round(h * 180 / 360);
//                bmpNew.setPixel(i,j, Color.rgb(v * 255, s * 255, h * 180 / 360));

                double[] data = {h * 180 / 360 + value,s * 255,v * 255};
                hsv.put(i,j,data);
            }
        }

        // hsv mat RGBA


        // hsv mat RGB
        //Mat hsvMat = new Mat(height, width, CvType.CV_8UC3);
        //Imgproc.cvtColor(hsvMata, hsvMat, Imgproc.COLOR_RGBA2RGB);

        // rgb mat
        Mat finalMat = new Mat(height, width, CvType.CV_8UC3);
        Imgproc.cvtColor(hsv, finalMat, Imgproc.COLOR_HSV2BGR, 0);

        // bitmap
        Bitmap finalBmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Utils.matToBitmap(finalMat, finalBmp);

        return finalBmp;
    }

    /**
     * Change value
     * @param bitmap input image
     * @param value constant
     * @return modified image
     */
    public static Bitmap changeValue(Bitmap bitmap, int value){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Mat src_rgba = new Mat(height, width, CvType.CV_8UC4);
        Utils.bitmapToMat(bitmap, src_rgba);

        Mat src = new Mat(height, width, CvType.CV_8UC3);
        Imgproc.cvtColor(src_rgba, src, Imgproc.COLOR_RGBA2RGB);


        Mat hsv = new Mat(height, width, CvType.CV_8UC3);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                double[] pixel = src.get(i,j);
                double b = pixel[0] / 255;
                double g = pixel[1] / 255;
                double r = pixel[2] / 255;


                double h = 0,  s, v;
                double M = maximum(r,g);
                M = maximum(M,b);
                double m = minimum(r,g);
                m = minimum(m,b);
                double C = M - m;

                // Value
                v = M;

                // Saturation
                s = (M != 0) ? (C / M) : 0;

                // Hue
                if (C != 0) {
                    if (M == r) h = 60 * (g - b) / C;
                    if (M == g) h = 120 + 60 * (b - r) / C;
                    if (M == b) h = 240 + 60 * (r - g) / C;
                }
                else { // grayscale
                    h = 0;
                }
                if (h < 0) {
                    h += 360;
                }

//                int vFin = Math.round(v * 255);
//                int sFin = minimum(Math.round(s * 255), 255);
//                int hFin = Math.round(h * 180 / 360);
//                bmpNew.setPixel(i,j, Color.rgb(v * 255, s * 255, h * 180 / 360));

                double[] data = {h * 180 / 360,s * 255,v * 255 + value};
                hsv.put(i,j,data);
            }
        }

        // hsv mat RGBA


        // hsv mat RGB
        //Mat hsvMat = new Mat(height, width, CvType.CV_8UC3);
        //Imgproc.cvtColor(hsvMata, hsvMat, Imgproc.COLOR_RGBA2RGB);

        // rgb mat
        Mat finalMat = new Mat(height, width, CvType.CV_8UC3);
        Imgproc.cvtColor(hsv, finalMat, Imgproc.COLOR_HSV2BGR, 0);

        // bitmap
        Bitmap finalBmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Utils.matToBitmap(finalMat, finalBmp);

        return finalBmp;
    }

    /**
     * Rotate right
     * @param bitmap input image
     * @return modified image
     */
    public static Bitmap rotateRight(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap bmpRot = Bitmap.createBitmap(height, width, Bitmap.Config.RGB_565);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                bmpRot.setPixel(height-i-1, j, bitmap.getPixel(j,i));
            }
        }

        return bmpRot;
    }

    /**
     * Rotate left
     * @param bitmap input image
     * @return modified image
     */
    public static Bitmap rotateLeft(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap bmpRot = Bitmap.createBitmap(height, width, Bitmap.Config.RGB_565);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                bmpRot.setPixel(i, width-j-1, bitmap.getPixel(j,i));
            }
        }

        return bmpRot;
    }

    /**
     * Flip horizontally
     * @param bitmap input image
     * @return modified image
     */
    public static Bitmap flipHorizontally(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap bmpFLip = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        for(int i=0; i<width/2; i++){
            for(int j=0; j<height; j++){
                int pixel1  = bitmap.getPixel(width-i-1,j);
                bmpFLip.setPixel(i,j,pixel1);
                int pixel2 = bitmap.getPixel(i,j);
                bmpFLip.setPixel(width-i-1,j,pixel2);

            }
        }

        return bmpFLip;
    }

    /**
     * Flip vertically
     * @param bitmap input image
     * @return modified image
     */
    public static Bitmap flipVertically(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap bmpFLip = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        for(int i=0; i<width; i++){
            for(int j=0; j<height/2+1; j++){
                int pixel1  = bitmap.getPixel(i,height-j-1);
                bmpFLip.setPixel(i,j,pixel1);
                int pixel2 = bitmap.getPixel(i,j);
                bmpFLip.setPixel(i,height-j-1,pixel2);
            }
        }

        return bmpFLip;
    }

    /**
     * Resize image
     * @param bitmap input image
     * @return modified image
     */
    public static Bitmap resize(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        int newHeight = height / 2;
        int newWidth = width / 2;

        Bitmap bmpNew = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.RGB_565);

        float ratioHeight =  (float)height / (float)newHeight;
        float ratioWidth = (float)width / (float)newWidth;

        for(int i=0; i<newWidth; i++){
            for(int j=0; j<newHeight; j++){
                int oldi = minimum(width, Math.round(i*ratioWidth));
                int oldj = minimum(height, Math.round(j*ratioHeight));
                bmpNew.setPixel(i,j, bitmap.getPixel(oldi, oldj));
            }
        }

        return bmpNew;

    }


    public static int minimum(int n1, int n2){
        if(n1 < n2) return n1;
        return n2;
    }

    public static float minimum(float n1, float n2){
        if(n1 < n2) return n1;
        return n2;
    }

    public static double minimum(double n1, double n2){
        if(n1 < n2) return n1;
        return n2;
    }

    public static int maximum(int n1, int n2){
        if(n1 > n2) return n1;
        return n2;
    }

    public static float maximum(float n1, float n2){
        if(n1 > n2) return n1;
        return n2;
    }

    public static double maximum(double n1, double n2){
        if(n1 > n2) return n1;
        return n2;
    }

}
