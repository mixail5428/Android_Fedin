package com.example.lesson_2_fedin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonTask1 = (Button) findViewById(R.id.buttonTask1);
        Button buttonTask2 = (Button) findViewById(R.id.buttonTask2);

        buttonTask1.setOnClickListener(new View.OnClickListener() { // переход в активити первого задания
            @Override
            public void onClick(View v) {
                startActivity(Task1.createStartIntent(MainActivity.this));
            }
        });

        buttonTask2.setOnClickListener(new View.OnClickListener() { // переход в активити второго задания
            @Override
            public void onClick(View v) {
                startActivity(Task2.createStartIntent(MainActivity.this));
            }
        });
    }
}
