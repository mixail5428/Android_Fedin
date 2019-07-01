package com.example.lesson_5_fedin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity4 extends AppCompatActivity {

    private final static long DEFAULT_TIME = 0;

    public static Intent createStartIntent(Context context){
        return new Intent(context, Activity4.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        onNewIntent(getIntent());

        Button button = findViewById(R.id.button_go_to_4_again);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.createStartIntentForTime(Activity4.this));
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        long longTime = intent.getLongExtra(MainActivity.EXTRA_TIME, DEFAULT_TIME );
        SimpleDateFormat time = new SimpleDateFormat(getResources().getString(R.string.dataFormat));
        TextView textView = findViewById(R.id.textViewWithTime);
        textView.setText(time.format(new Date(longTime)));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView textView = findViewById(R.id.textViewWithTime);
        String stringTime =(String) textView.getText();
        outState.putString(MainActivity.EXTRA_TIME, stringTime);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String stringTime = savedInstanceState.getString(MainActivity.EXTRA_TIME);
        TextView textView = findViewById(R.id.textViewWithTime);
        textView.setText(stringTime);
    }
}
