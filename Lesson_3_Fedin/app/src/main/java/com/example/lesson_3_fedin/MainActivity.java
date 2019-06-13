package com.example.lesson_3_fedin;

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

        Button buttonTask1 = (Button) findViewById(R.id.buttonTask1);
        Button buttonTask2 = (Button) findViewById(R.id.buttonTask2);


        buttonTask1.setOnClickListener(new View.OnClickListener() { // переход в активити второго задания
            @Override
            public void onClick(View v) {
                startActivity(Task1.createStartIntent(MainActivity.this));
            }
        });

        //startActivity(new Intent(MainActivity.this, Task1.class));
    }
}
