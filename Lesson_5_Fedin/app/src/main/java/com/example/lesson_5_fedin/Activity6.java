package com.example.lesson_5_fedin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.snackbar.Snackbar;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class Activity6 extends AppCompatActivity {
    public static Intent createStartIntent(Context context) {
        return new Intent(context, Activity6.class);
    }
    String foto1 = "https://img14.postila.ru/resize?w=460&src=%2Fdata%2F3c%2Fc2%2Fb6%2F71%2F3cc2b671ce3fe9714b564c7be6fa1094b1df3e1306e6ff9acc665ee125f4e802.jpg";
    String foto2 = "http://sapienttools.ru/wp-content/uploads/2018/06/63bbc8ad52a6111354ecabccafc3ab55-300x200.jpg";
    String foto3 = "https://pp.userapi.com/c626124/u23612600/video/l_cb89ba10.jpg";
    ArrayList<DataServices> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);
        createBackgraundToolbar();
        createArrayList();
        createRecyclerView();
        createViewPager();
        createofferServices();


    }

    private void createofferServices(){
        View offerService = findViewById(R.id.offerServicesss);
        offerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.offerService), "Предложить услугу"
                        , Snackbar.LENGTH_SHORT).show();
            }
        });

       View servicesTitle = findViewById(R.id.servicesTitle);
        servicesTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.servicesTitle), "Список всех услуг"
                        , Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    private void createBackgraundToolbar(){

        final String backgraund = "https://2ch.pm/wp/src/61026/15210778802152.jpg";
        final String text = "Услуги управляющей компании будут доступны после сдачи дома";
        final FrameLayout frameLayout = findViewById(R.id.frameLayout);
        Glide.with(this).load(backgraund)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        frameLayout.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

        TextView textView = findViewById(R.id.text);
        textView.setText(text);


    }

    private void createViewPager(){

        ArrayList<String> drawable = new ArrayList<>();
        drawable.add(foto1);
        drawable.add(foto2);
        drawable.add(foto3);

        ViewPager viewPager = findViewById(R.id.viewPager);
        AdapterViewPager adapter = new AdapterViewPager(getSupportFragmentManager(), drawable);

        viewPager.setAdapter(adapter);

        CirclePageIndicator indicator = findViewById(R.id.indicator);

        indicator.setViewPager(viewPager);

    }

    private void createRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        AdapterServices adapterServices = new AdapterServices(data);
        adapterServices.setClickLister(new AdapterServices.OnClickLister() {
            @Override
            public void onClick(DataServices item) {
                Snackbar.make(findViewById(R.id.recyclerView), item.getTitle()
                        , Snackbar.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapterServices);
    }

    private void createArrayList(){
        data = new ArrayList<>();
        data.add(new DataServices(foto1,"Царь пышка","Скидка 10% на выпечку\n по коду"
                ,"Лермонтовский пр, 52, МО №1"));
        data.add(new DataServices(foto2, "Сидишь голодный?", "Вкуснейшая шаурма в городе ждет тебя",
                "Богдана Хмельницкого 35"));
        data.add(new DataServices(foto3,"Время учиться", "Приёмная комиссия МГУ им. Огарева начала свою работу"
                , "Большевистская ул 68"));

    }
}
