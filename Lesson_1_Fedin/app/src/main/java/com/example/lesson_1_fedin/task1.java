package com.example.lesson_1_fedin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.TreeSet;

public class task1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        final EditText editText = (EditText) findViewById(R.id.editText);
        Button button1 = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button3);
        final TextView textView = (TextView) findViewById(R.id.textView2);

        final TreeSet<String> studentList = new TreeSet();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentList.add(editText.getText().toString());
                editText.setText("");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(String i : studentList){
                    textView.append("\n" + i);
                }
            }
        });

    }
}
