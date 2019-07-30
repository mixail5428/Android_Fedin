package com.example.lesson_7_fedin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.lesson_7_fedin.bridge.Bridge;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class BridgeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_BRIDGE = "EXTRA_BRIDGE";
    TextView textViewDescriptionBridge;
    Bridge bridge;
    AdapterRecyclerViewBridges.HolderBridge holderBridgeStatusBridge;

    public static Intent createStartIntent(Context context, Bridge bridge) {
        Intent intent = new Intent(context, BridgeDetailActivity.class);
        intent.putExtra(EXTRA_BRIDGE, bridge);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        Intent intent = getIntent();
        bridge = (Bridge) intent.getParcelableExtra(EXTRA_BRIDGE);
        if (bridge != null)
            bindView();

    }

    private void bindView() {
        textViewDescriptionBridge = findViewById(R.id.descriptionBridge);
        textViewDescriptionBridge.setText(Html.fromHtml(bridge.getDescription()));
        holderBridgeStatusBridge =
                new AdapterRecyclerViewBridges.HolderBridge(findViewById(R.id.status));
        holderBridgeStatusBridge.bind(bridge, null);
        holderBridgeStatusBridge.dontClicable();
        Button buttonReconnect = findViewById(R.id.button_reconnect);
        buttonReconnect.setOnClickListener(v -> downloadPhoto());
        downloadPhoto();

    }

    private void downloadPhoto(){
        CollapsingToolbarLayout photoBridge = findViewById(R.id.photoBridge);

        String photoURL = Controler.BASE_URL;

        if (holderBridgeStatusBridge.isOpen())
            photoURL += bridge.getPhotoOpen();
        else photoURL += bridge.getPhotoClose();


        TextView textViewError = findViewById(R.id.text_view_error);
        textViewError.setVisibility(View.GONE);
        Button buttonReconnect = findViewById(R.id.button_reconnect);
        buttonReconnect.setVisibility(View.GONE);
        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(this);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();
        photoBridge.setBackground(progressDrawable);
        Glide.with(this)
                .load(photoURL)
                .centerCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        photoBridge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        buttonReconnect.setVisibility(View.VISIBLE);
                        textViewError.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        photoBridge.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });


    }

}
