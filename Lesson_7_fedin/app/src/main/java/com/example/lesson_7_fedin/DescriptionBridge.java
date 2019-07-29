package com.example.lesson_7_fedin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.lesson_7_fedin.bridge.Bridge;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DescriptionBridge extends AppCompatActivity {

    public static final String BRIDGE = "BRIDGE";
    TextView descriptionBridge;
    Bridge bridge;

    public static Intent createStartIntent(Context context, Bridge bridge) {
        Intent intent = new Intent(context, DescriptionBridge.class);
        intent.putExtra(BRIDGE, bridge);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_bridge);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        Intent intent = getIntent();
        bridge = (Bridge) intent.getParcelableExtra(BRIDGE);
        if (bridge != null)
            bindView();

    }

    private void bindView() {
        descriptionBridge = findViewById(R.id.descriptionBridge);
        descriptionBridge.setText(Html.fromHtml(bridge.getDescription()));
        AdapterRecyclerViewBridges.HolderBridge statusBridge =
                new AdapterRecyclerViewBridges.HolderBridge(findViewById(R.id.status));
        statusBridge.bind(bridge, null);
        statusBridge.dontClicable();

        CollapsingToolbarLayout photoBridge = findViewById(R.id.photoBridge);

        String photoURL = Controler.BASE_URL;

        if (statusBridge.isOpen())
            photoURL += bridge.getPhotoOpen();
        else photoURL += bridge.getPhotoClose();

        Glide.with(this)
                .load(photoURL)
                .centerCrop()
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource,
                                                @Nullable Transition<? super Drawable> transition) {
                        photoBridge.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });


    }

}
