package com.girlathome.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.girlathome.R;
import com.girlathome.utilities.CropImage;
import com.girlathome.utilities.CropImageView;
import com.girlathome.utilities.ImagePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by steve on 4/20/17.
 */
public class StyleUploadImagesFragment extends Fragment {
    private static final String TAG = StyleUploadImagesFragment.class.getSimpleName();
    Activity parentActivity;
    @BindView(R.id.img_1)
    ImageView img1;
    @BindView(R.id.img_2)
    ImageView img2;
    @BindView(R.id.img_3)
    ImageView img3;
    @BindView(R.id.img_4)
    ImageView img4;
    int position = 0;
    private ImagePicker imagePicker = new ImagePicker();

    public StyleUploadImagesFragment() {
    }


    public static StyleUploadImagesFragment newInstance() {
        StyleUploadImagesFragment fragment = new StyleUploadImagesFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hit");
        super.onCreate(savedInstanceState);
    }

    /**
     * Change the null parameter in {@code inflater.inflate()}
     * to a layout resource {@code R.layout.example}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: hit");
        View rootView = inflater.inflate(R.layout.style_upload_images_fragment, container, false);
        ButterKnife.bind(this, rootView);
        ((AddNew)parentActivity).setUpTitle(getString(R.string.add_images));

        imagePicker.setTitle("Pick Image");
        imagePicker.setCropImage(true);

        return rootView;
    }


    @OnClick(R.id.rel_1)
    void img1() {
        position = 1;
        startChooser();
    }

    @OnClick(R.id.rel_2)
    void img2() {
        position = 2;
        startChooser();

    }

    @OnClick(R.id.rel_3)
    void img3() {
        position = 3;
        startChooser();
    }

    @OnClick(R.id.rel_4)
    void img4() {
        position = 4;
        startChooser();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    private void startChooser() {
        // 启动图片选择器
        imagePicker.startChooser(this, new ImagePicker.Callback() {
            // 选择图片回调
            @Override
            public void onPickImage(Uri imageUri) {

            }

            // 裁剪图片回调
            @Override
            public void onCropImage(Uri imageUri) {
                if (position == 1) {
                    img1.setImageURI(imageUri);
                } else if (position == 2) {
                    img2.setImageURI(imageUri);
                } else if (position == 3) {
                    img3.setImageURI(imageUri);
                } else if (position == 4) {
                    img4.setImageURI(imageUri);
                }
            }

            @Override
            public void cropConfig(CropImage.ActivityBuilder builder) {
                builder
                        .setMultiTouchEnabled(false)
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setRequestedSize(640, 640)
                        .setAspectRatio(1, 1);
            }

            // 用户拒绝授权回调
            @Override
            public void onPermissionDenied(int requestCode, String[] permissions,
                                           int[] grantResults) {
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: hit");
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        Log.d(TAG, "onResume: hit");
        super.onResume();
    }


    @Override
    public void onPause() {
        Log.d(TAG, "onPause: hit");
        super.onPause();
    }


    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: hit");
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: hit");
        super.onDestroy();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parentActivity = activity;
    }
} 