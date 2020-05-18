package com.example.project2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.project2.imageprocessors.Filter;
import com.example.project2.imageprocessors.ImageProcessor;
import com.example.project2.imageprocessors.subfilters.BrightnessSubFilter;
import com.example.project2.imageprocessors.subfilters.ContrastSubFilter;
import com.example.project2.imageprocessors.subfilters.FlipHorizontalSubFilter;
import com.example.project2.imageprocessors.subfilters.FlipVerticalSubFilter;
import com.example.project2.imageprocessors.subfilters.HueSubFilter;
import com.example.project2.imageprocessors.subfilters.RotateLeftSubFilter;
import com.example.project2.imageprocessors.subfilters.RotateRightSubFilter;
import com.example.project2.imageprocessors.subfilters.SaturationSubFilter;
import com.example.project2.imageprocessors.subfilters.ToneSubFilter;
import com.example.project2.imageprocessors.subfilters.ValueSubFilter;
import com.example.project2.utils.BitmapUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.opencv.android.OpenCVLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FiltersListFragment.FiltersListFragmentListener, EditImageFragment.EditImageFragmentListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String IMAGE_NAME = "dog.jpg";

    public static final int SELECT_GALLERY_IMAGE = 101;

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    Uri imageUri;
    String mCameraFileName;

    @BindView(R.id.image_preview)
    ImageView imagePreview;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    Bitmap originalImage;
    // to backup image with filter applied
    Bitmap filteredImage;

    // the final image after applying
    // brightness, saturation, contrast
    Bitmap finalImage;

    FiltersListFragment filtersListFragment;
    EditImageFragment editImageFragment;

    // modified image values
    int brightnessFinal = 0;
    float contrastFinal = 1.0f;
    int toneFinal = 0;
    int saturationFinal = 0;
    int hueFinal = 0;
    int valueFinal = 0;

    int brightnessPrev = 0;
    float contrastPrev = 1.0f;
    int tonePrev = 0;
    int saturationPrev = 0;
    int huePrev = 0;
    int valuePrev = 0;


    static {

        if (!OpenCVLoader.initDebug()) {
        }
    }
    // load native image filters library
    static {
        System.loadLibrary("NativeImageProcessor");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.activity_title_main));

        loadImage();

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // adding filter list fragment
        filtersListFragment = new FiltersListFragment();
        filtersListFragment.setListener(this);

        // adding edit image fragment
        editImageFragment = new EditImageFragment();
        editImageFragment.setListener(this);

        adapter.addFragment(filtersListFragment, getString(R.string.tab_filters));
        adapter.addFragment(editImageFragment, getString(R.string.tab_edit));

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFilterSelected(Filter filter) {
        // reset image controls
        resetControls();

        // applying the selected filter
        filteredImage = filter.processFilter(originalImage);

        // preview filtered image
        imagePreview.setImageBitmap(filteredImage);
        finalImage = filteredImage.copy(Bitmap.Config.ARGB_8888, true);
    }

    @Override
    public void onBrightnessChanged(final int brightness) {
        brightnessFinal = brightness;
        //Filter myFilter = new Filter();
        //myFilter.addSubFilter(new BrightnessSubFilter(brightness));
        //imagePreview.setImageBitmap(myFilter.processFilter(finalImage.copy(Bitmap.Config.ARGB_8888, true)));
    }

    @Override
    public void onContrastChanged(final float contrast) {
        contrastFinal = contrast;
        //Filter myFilter = new Filter();
        //myFilter.addSubFilter(new ContrastSubFilter(contrast));
        //imagePreview.setImageBitmap(myFilter.processFilter(finalImage.copy(Bitmap.Config.ARGB_8888, true)));
    }

    @Override
    public void onToneChanged(final int tone) {
        toneFinal = tone;
//        Filter myFilter = new Filter();
//        myFilter.addSubFilter(new ToneSubFilter(tone));
//        imagePreview.setImageBitmap(myFilter.processFilter(finalImage.copy(Bitmap.Config.ARGB_8888, true)));
    }

    @Override
    public void onSaturationChanged(final int saturation) {
        saturationFinal = saturation;
//        Filter myFilter = new Filter();
//        myFilter.addSubFilter(new ToneSubFilter(tone));
//        imagePreview.setImageBitmap(myFilter.processFilter(finalImage.copy(Bitmap.Config.ARGB_8888, true)));
    }

    @Override
    public void onHueChanged(final int hue) {
        hueFinal = hue;
//        Filter myFilter = new Filter();
//        myFilter.addSubFilter(new ToneSubFilter(tone));
//        imagePreview.setImageBitmap(myFilter.processFilter(finalImage.copy(Bitmap.Config.ARGB_8888, true)));
    }

    @Override
    public void onValueChanged(final int value) {
        valueFinal = value;
//        Filter myFilter = new Filter();
//        myFilter.addSubFilter(new ToneSubFilter(tone));
//        imagePreview.setImageBitmap(myFilter.processFilter(finalImage.copy(Bitmap.Config.ARGB_8888, true)));
    }

    @Override
    public void onRotateRight() {
        Filter myFilter = new Filter();
        myFilter.addSubFilter(new RotateRightSubFilter());
        filteredImage = myFilter.processFilter(originalImage);
        imagePreview.setImageBitmap(filteredImage);
        originalImage = filteredImage.copy(Bitmap.Config.ARGB_8888, true);
        finalImage = filteredImage.copy(Bitmap.Config.ARGB_8888, true);
    }

    @Override
    public void onRotateLeft() {
        Filter myFilter = new Filter();
        myFilter.addSubFilter(new RotateLeftSubFilter());
        filteredImage = myFilter.processFilter(originalImage);
        imagePreview.setImageBitmap(filteredImage);
        originalImage = filteredImage.copy(Bitmap.Config.ARGB_8888, true);
        finalImage = filteredImage.copy(Bitmap.Config.ARGB_8888, true);
    }

    @Override
    public void onFlipHor() {
        Filter myFilter = new Filter();
        myFilter.addSubFilter(new FlipHorizontalSubFilter());
        filteredImage = myFilter.processFilter(originalImage);
        imagePreview.setImageBitmap(filteredImage);
        originalImage = filteredImage.copy(Bitmap.Config.ARGB_8888, true);
        finalImage = filteredImage.copy(Bitmap.Config.ARGB_8888, true);
    }

    @Override
    public void onFlipVer() {
        Filter myFilter = new Filter();
        myFilter.addSubFilter(new FlipVerticalSubFilter());
        filteredImage = myFilter.processFilter(originalImage);
        imagePreview.setImageBitmap(filteredImage);
        originalImage = filteredImage.copy(Bitmap.Config.ARGB_8888, true);
        finalImage = filteredImage.copy(Bitmap.Config.ARGB_8888, true);
    }

    @Override
    public void onApplyEdit(){
        originalImage = finalImage.copy(Bitmap.Config.ARGB_8888, true);
        filtersListFragment.prepareThumbnail(originalImage);
        resetControls();
    }

    @Override
    public void onApplyFilter(){
        originalImage = finalImage.copy(Bitmap.Config.ARGB_8888, true);
        filtersListFragment.prepareThumbnail(originalImage);
    }


    @Override
    public void onEditStarted() {

    }

    @Override
    public void onEditCompleted() {
        // once the editing is done i.e seekbar is drag is completed,
        // apply the values on to filtered image
        /*final Bitmap bitmap = filteredImage.copy(Bitmap.Config.ARGB_8888, true);

        Filter myFilter = new Filter();
        myFilter.addSubFilter(new BrightnessSubFilter(brightnessFinal));
        myFilter.addSubFilter(new ContrastSubFilter(contrastFinal));
        myFilter.addSubFilter(new ToneSubFilter(toneFinal));
        finalImage = myFilter.processFilter(bitmap);*/

        // applying the selected filter
        Filter myFilter = new Filter();
        if(brightnessFinal != brightnessPrev) {
            myFilter.addSubFilter(new BrightnessSubFilter(brightnessFinal));
            brightnessPrev = brightnessFinal;
        }
        if(contrastFinal != contrastPrev){
            myFilter.addSubFilter(new ContrastSubFilter(contrastFinal));
            contrastPrev = contrastFinal;
        }
        if(toneFinal != tonePrev) {
            myFilter.addSubFilter(new ToneSubFilter(toneFinal));
            tonePrev = toneFinal;
        }
        if(saturationFinal != saturationPrev){
            myFilter.addSubFilter(new SaturationSubFilter(saturationFinal));
            saturationPrev = saturationFinal;
        }
        if(hueFinal != huePrev) {
            myFilter.addSubFilter(new HueSubFilter(hueFinal));
            huePrev = hueFinal;
        }
        if(valueFinal != valuePrev) {
            myFilter.addSubFilter(new ValueSubFilter(valueFinal));
            valuePrev = valueFinal;
        }
        //filteredImage = myFilter.processFilter(originalImage);
        finalImage = myFilter.processFilter(originalImage);

        // preview filtered image
        imagePreview.setImageBitmap(finalImage);
        //finalImage = filteredImage.copy(Bitmap.Config.ARGB_8888, true);
        //imagePreview.setImageBitmap(myFilter.processFilter(finalImage.copy(Bitmap.Config.ARGB_8888, true)));
    }


    /**
     * Resets image edit controls to normal when new filter
     * is selected
     */
    private void resetControls() {
        if (editImageFragment != null) {
            editImageFragment.resetControls();
        }
        brightnessFinal = 0;
        saturationFinal = 0;
        contrastFinal = 10.0f;
        hueFinal = 0;
        valueFinal = 0;
        toneFinal = 0;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    // load the default image from assets on app launch
    private void loadImage() {
        originalImage = BitmapUtils.getBitmapFromAssets(this, IMAGE_NAME, 300, 300);
        filteredImage = originalImage.copy(Bitmap.Config.ARGB_8888, true);
        finalImage = originalImage.copy(Bitmap.Config.ARGB_8888, true);
        imagePreview.setImageBitmap(originalImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_open) {
            openImageFromGallery();
            return true;
        }

        if (id == R.id.action_save) {
            saveImageToGallery();
            return true;
        }

        if (id == R.id.action_takepic){
            openCamera();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_GALLERY_IMAGE) {
            Bitmap bitmap = BitmapUtils.getBitmapFromGallery(this, data.getData(), 800, 800);
            bitmap = ImageProcessor.resize(bitmap);

            Uri tempUri = getImageUri(getApplicationContext(), bitmap);
            File imagefile = new File(getRealPathFromURI(tempUri));

            int imageRotation = getImageRotation(imagefile);

            System.out.print("IMAGE ROTAAAAAAAATIONNNNNNNN" + imageRotation);

            if (imageRotation != 0)
                bitmap = getBitmapRotatedByDegree(bitmap, imageRotation);


            // clear bitmap memory
            originalImage.recycle();
            finalImage.recycle();
            finalImage.recycle();

            originalImage = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            filteredImage = originalImage.copy(Bitmap.Config.ARGB_8888, true);
            finalImage = originalImage.copy(Bitmap.Config.ARGB_8888, true);
            imagePreview.setImageBitmap(originalImage);
            bitmap.recycle();

            // render selected image thumbnails
            filtersListFragment.prepareThumbnail(originalImage);
        }

        if (requestCode == 2 && resultCode == Activity.RESULT_OK)
        {
            if(data != null){
                System.out.println("\n\n\nBr 1\n\n\n");
                imageUri = data.getData();
                imagePreview.setImageURI(imageUri);
                imagePreview.setVisibility(View.VISIBLE);

                Bitmap bitmapBig = ((BitmapDrawable)imagePreview.getDrawable()).getBitmap();
                Bitmap bitmap = Bitmap.createScaledBitmap(bitmapBig, (int)(bitmapBig.getWidth()*0.25),
                        (int)(bitmapBig.getHeight()*0.25), true);

                // clear bitmap memory
                originalImage.recycle();
                finalImage.recycle();
                finalImage.recycle();

                originalImage = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                filteredImage = originalImage.copy(Bitmap.Config.ARGB_8888, true);
                finalImage = originalImage.copy(Bitmap.Config.ARGB_8888, true);

                bitmap.recycle();
                filtersListFragment.prepareThumbnail(originalImage);
            }

            if(imageUri == null && mCameraFileName != null){
                System.out.println("\n\n\nBr 2\n\n\n");
                imageUri = Uri.fromFile(new File(mCameraFileName));
                imagePreview.setImageURI(imageUri);
                imagePreview.setVisibility(View.VISIBLE);

                Bitmap bitmapBig = ((BitmapDrawable)imagePreview.getDrawable()).getBitmap();
                Bitmap bitmap = Bitmap.createScaledBitmap(bitmapBig, (int)(bitmapBig.getWidth()*0.25),
                        (int)(bitmapBig.getHeight()*0.25), true);

                // clear bitmap memory
                originalImage.recycle();
                finalImage.recycle();
                finalImage.recycle();

                originalImage = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                filteredImage = originalImage.copy(Bitmap.Config.ARGB_8888, true);
                finalImage = originalImage.copy(Bitmap.Config.ARGB_8888, true);

                //bitmap.recycle();
                filtersListFragment.prepareThumbnail(originalImage);
            }

            File file = new File(mCameraFileName);
            if (!file.exists()) {
                file.mkdir();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void openImageFromGallery() {
        Dexter.withActivity(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent, SELECT_GALLERY_IMAGE);
                        } else {
                            Toast.makeText(getApplicationContext(), "Permissions are not granted!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void saveImageToGallery() {
        Dexter.withActivity(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            final String path = BitmapUtils.insertImage(getContentResolver(), finalImage, System.currentTimeMillis() + "_profile.jpg", null);
                            if (!TextUtils.isEmpty(path)) {
                                Snackbar snackbar = Snackbar
                                        .make(coordinatorLayout, "Image saved to gallery!", Snackbar.LENGTH_LONG)
                                        .setAction("OPEN", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                openImage(path);
                                            }
                                        });

                                snackbar.show();
                            } else {
                                Snackbar snackbar = Snackbar
                                        .make(coordinatorLayout, "Unable to save image!", Snackbar.LENGTH_LONG);

                                snackbar.show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Permissions are not granted!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    private void openImage(String path) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(path), "image/*");
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void openCamera(){
        // cameraa
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        }
        else
        {
         /*   Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);*/

            // ???
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            Date date = new Date();
            DateFormat df = new SimpleDateFormat("-mm-ss");

            Random rand = new Random();
            int randInt = rand.nextInt(1000);

//            String newPicFile = df.format(date) + randInt + ".jpg";
            String newPicFile ="aaaaaa.jpg";
            String outPath = Environment.getExternalStorageDirectory().getPath() + "/" + newPicFile;
            //String outPath = "/sdcard/" + newPicFile;
            File outFile = new File(outPath);

            mCameraFileName = outFile.toString();
            Uri outuri = Uri.fromFile(outFile);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outuri);
            startActivityForResult(cameraIntent, 2);
        }
    }

    private  int getImageRotation(final File imageFile) {

        ExifInterface exif = null;
        int exifRotation = 0;

        try {
            exif = new ExifInterface(imageFile.getPath());
            exifRotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (exif == null)
            return 0;
        else
            return exifToDegrees(exifRotation);
    }

    private  int exifToDegrees(int rotation) {
        if (rotation == ExifInterface.ORIENTATION_ROTATE_90)
            return 90;
        else if (rotation == ExifInterface.ORIENTATION_ROTATE_180)
            return 180;
        else if (rotation == ExifInterface.ORIENTATION_ROTATE_270)
            return 270;

        return 0;
    }

    private  Bitmap getBitmapRotatedByDegree(Bitmap bitmap, int rotationDegree) {
        Matrix matrix = new Matrix();
        matrix.preRotate(rotationDegree);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
}
