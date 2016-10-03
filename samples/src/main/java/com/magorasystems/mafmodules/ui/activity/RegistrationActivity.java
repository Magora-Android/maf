package com.magorasystems.mafmodules.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jakewharton.rxbinding.view.RxView;
import com.magorasystems.mafmodules.R;
import com.magorasystems.rx.imagepicker.RxImagePicker;
import com.magorasystems.rx.imagepicker.Sources;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        SimpleDraweeView ivPickedImage = (SimpleDraweeView) findViewById(R.id.iv_result);
        FloatingActionButton fabCamera = (FloatingActionButton) findViewById(R.id.fab_pick_camera);
        FloatingActionButton fabGallery = (FloatingActionButton) findViewById(R.id.fab_pick_gallery);

        RxView.clicks(fabCamera)
                .subscribe(aVoid -> {
                    RxImagePicker.with(this)
                            .requestImage(Sources.CAMERA)
                            .map(uri -> {
                                final ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                                        .setAutoRotateEnabled(true)
                                        .build();
                                return Fresco.newDraweeControllerBuilder()
                                        .setImageRequest(request)
                                        .setOldController(ivPickedImage.getController())
                                        .build();
                            })
                            .subscribe(ivPickedImage::setController);
                });

        RxView.clicks(fabGallery)
                .subscribe(aVoid -> {
                    RxImagePicker.with(this)
                            .requestImage(Sources.GALLERY)
                            .map(uri -> {
                                final ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                                        .setAutoRotateEnabled(true)
                                        .build();
                                return Fresco.newDraweeControllerBuilder()
                                        .setImageRequest(request)
                                        .setOldController(ivPickedImage.getController())
                                        .build();
                            })
                            .subscribe(ivPickedImage::setController);
                });
    }
}
