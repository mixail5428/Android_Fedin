package com.example.lesson_5_fedin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_TIME = "time";

    public static Intent createStartIntentForTime(Context context) {

        Intent intent = new Intent(context, Activity4.class);
        long timeLong = System.currentTimeMillis();
        intent.putExtra(MainActivity.EXTRA_TIME, timeLong);
        return intent;
    }

    public static Intent createStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGoTo4 = findViewById(R.id.button_go_to_4);
        buttonGoTo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(createStartIntentForTime(MainActivity.this));
            }
        });

        Button buttonGoTo2 = findViewById(R.id.button_go_to_2);
        buttonGoTo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Activity2.createStartIntent(MainActivity.this));
            }
        });

        Button button = findViewById(R.id.button_go_to_6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Activity6.createStartIntent(MainActivity.this));
            }
        });


    }
}
