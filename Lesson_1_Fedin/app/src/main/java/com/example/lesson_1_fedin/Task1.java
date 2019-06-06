package com.example.lesson_1_fedin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.TreeSet;

public class Task1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        final EditText editText = (EditText) findViewById(R.id.editText);
        Button button1 = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button3);
        final TextView textView = (TextView) findViewById(R.id.textView2);

        final TreeSet<String> studentSet = new TreeSet();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentSet.add(editText.getText().toString()); // Добавляет учащегося в TreeSet
                editText.setText("");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() { // Выводит список добаленных учеников
            @Override
            public void onClick(View v) {
                textView.setText(R.string.list);
                for(String i : studentSet){
                    textView.append("\n" + i);
                }
            }
        });

    }
}
