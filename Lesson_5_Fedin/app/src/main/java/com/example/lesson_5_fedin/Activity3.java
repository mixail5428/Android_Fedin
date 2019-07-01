package com.example.lesson_5_fedin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class Activity3 extends AppCompatActivity {

    final static int MY_CODE = 1;

    public static Intent createStartIntent(Context context) {
        return new Intent(context, Activity3.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Button button = findViewById(R.id.button_go_to_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.createStartIntent(Activity3.this));
            }
        });

        Button button2 = findViewById(R.id.button_go_to_5);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(Activity5.createStartIntent(Activity3.this), MY_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == MY_CODE && resultCode == RESULT_OK)
            if (data != null) {
                String message = data.getExtras().getString(Activity5.MESSAGE);
                Snackbar.make(findViewById(R.id.activity3), message, Snackbar.LENGTH_LONG).show();
            }
    }
}

