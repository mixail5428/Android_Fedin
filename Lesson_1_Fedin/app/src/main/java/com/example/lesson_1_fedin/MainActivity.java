package com.example.lesson_1_fedin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() { // переходит в окно первого задания
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, task1.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() { // переходит в окно второго задания
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, task2.class));
            }
        });


    }
}
