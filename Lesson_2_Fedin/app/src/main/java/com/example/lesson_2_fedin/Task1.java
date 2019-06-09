package com.example.lesson_2_fedin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Task1 extends AppCompatActivity {

    public static Intent createStartIntent(Context context){
        return new Intent(context, Task1.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);
    }
}
