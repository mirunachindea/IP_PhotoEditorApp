package com.example.project2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditImageFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, Button.OnClickListener{

    private EditImageFragmentListener listener;

    @BindView(R.id.seekbar_brightness)
    SeekBar seekBarBrightness;

    @BindView(R.id.seekbar_contrast)
    SeekBar seekBarContrast;

    @BindView(R.id.seekbar_tone)
    SeekBar seekBarTone;

    @BindView(R.id.seekbar_saturation)
    SeekBar seekBarSaturation;

    @BindView(R.id.seekbar_hue)
    SeekBar seekBarHue;

    @BindView(R.id.seekbar_value)
    SeekBar seekBarValue;

    @BindView(R.id.rotate_right)
    Button btnRotateRight;

    @BindView(R.id.rotate_left)
    Button btnRotateLeft;

    @BindView(R.id.flip_hor)
    Button btnFlipHor;

    @BindView(R.id.flip_ver)
    Button btnFlipVer;

    @BindView(R.id.apply)
    Button btnApply;

    public void setListener(EditImageFragmentListener listener) {
        this.listener = listener;
    }

    public EditImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_image, container, false);

        ButterKnife.bind(this, view);

        // keeping brightness value b/w -100 / +100
        seekBarBrightness.setMax(200);
        seekBarBrightness.setProgress(100);

        // keeping contrast value b/w 0 - 20
        seekBarContrast.setMax(20);
        seekBarContrast.setProgress(10);

        // keeping tone value between -20 and +20
        seekBarTone.setMax(40);
        seekBarTone.setProgress(20);

        // keeping saturation value between -40 and +40
        seekBarSaturation.setMax(80);
        seekBarSaturation.setProgress(40);

        // keeping hue value between -40 and +40
        seekBarHue.setMax(80);
        seekBarHue.setProgress(40);

        // keeping value between -40 and +40
        seekBarValue.setMax(80);
        seekBarValue.setProgress(40);


        seekBarBrightness.setOnSeekBarChangeListener(this);
        seekBarContrast.setOnSeekBarChangeListener(this);
        seekBarTone.setOnSeekBarChangeListener(this);
        seekBarSaturation.setOnSeekBarChangeListener(this);
        seekBarHue.setOnSeekBarChangeListener(this);
        seekBarValue.setOnSeekBarChangeListener(this);

        btnRotateRight.setOnClickListener(this);
        btnRotateLeft.setOnClickListener(this);
        btnFlipHor.setOnClickListener(this);
        btnFlipVer.setOnClickListener(this);
        btnApply.setOnClickListener(this);
        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        if (listener != null) {

            if (seekBar.getId() == R.id.seekbar_brightness) {
                // brightness values are b/w -100 to +100
                listener.onBrightnessChanged(progress - 100);
            }

            if (seekBar.getId() == R.id.seekbar_contrast) {
                //progress += 1;
                //float floatVal = .10f * progress;
                listener.onContrastChanged((progress - 1) * 0.1f);
            }

            if (seekBar.getId() == R.id.seekbar_tone) {
                // tone values are b/w -20 to 20
                listener.onToneChanged(progress - 20);
            }

            if (seekBar.getId() == R.id.seekbar_saturation) {
                // tone values are b/w -40 to 40
                listener.onSaturationChanged(progress - 40);
            }

            if (seekBar.getId() == R.id.seekbar_hue) {
                // tone values are b/w -40 to 40
                listener.onHueChanged(progress - 40);
            }

            if (seekBar.getId() == R.id.seekbar_value) {
                // tone values are b/w -40 to 40
                listener.onValueChanged(progress - 40);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (listener != null)
            listener.onEditStarted();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (listener != null)
            listener.onEditCompleted();
    }

    public void resetControls() {
        seekBarBrightness.setProgress(100);
        seekBarContrast.setProgress(10);
        seekBarTone.setProgress(20);
        seekBarSaturation.setProgress(40);
        seekBarHue.setProgress(40);
        seekBarValue.setProgress(40);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            
            if (v.getId() == R.id.rotate_right) {
                listener.onRotateRight();
            }
            if (v.getId() == R.id.rotate_left) {
                listener.onRotateLeft();
            }
            if (v.getId() == R.id.flip_hor) {
                listener.onFlipHor();
            }
            if (v.getId() == R.id.flip_ver) {
                listener.onFlipVer();
            }
            if (v.getId() == R.id.apply) {
                listener.onApplyEdit();
            }
        }
    }

    public interface EditImageFragmentListener {
        void onBrightnessChanged(int brightness);

        void onContrastChanged(float contrast);

        void onToneChanged(int tone);

        void onSaturationChanged(int saturation);

        void onHueChanged(int hue);

        void onValueChanged(int value);

        void onEditStarted();

        void onEditCompleted();

        void onRotateRight();

        void onRotateLeft();

        void onFlipHor();

        void onFlipVer();

        void onApplyEdit();
    }
}