package com.example.lesson_1_fedin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class task2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);

        final HashMap< Long, Student> list = new HashMap<>();

        final EditText editText = (EditText) findViewById(R.id.editText3);
        Button button = (Button) findViewById(R.id.button4);
        final TextView listStudent = (TextView) findViewById(R.id.textView);

        editText.setOnKeyListener(new View.OnKeyListener() { // добавляет студента в HashMap по нажатию enter
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){

                    try {
                        Student newStudent = new Student(editText.getText().toString());
                        list.put(newStudent.getId(), newStudent);
                    }
                    catch (Exception exp){
                        exp.printStackTrace();
                    }
                    editText.setText("");

                    return true;
                }
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() { // выводит вписок добавленных студентов
            @Override
            public void onClick(View v) {
                listStudent.setText("");
                for(long i : list.keySet()){
                    listStudent.append("\n" + list.get(i).getStudentToString());
                }
            }
        });


    }

    class Student{ // Структура для хранения студентов
        long id;
        String name;
        String surname;
        String grade;
        int birthdayYear;

        Student(String str){
            String[] parse = str.split(" ");
            id = System.currentTimeMillis();
            name = parse[0];
            surname = parse[1];
            grade = parse[2];
            birthdayYear = Integer.parseInt(parse[3]);
        }

        long getId(){
            return id;
        }

        String getStudentToString(){ // переводит все данные о студентах в String
            String _id = Long.toString(id);
            String _birthdayYear = Integer.toString(birthdayYear);
            return _id + " " + name + " " + surname + " " + grade + " " + _birthdayYear;
        }

    }
}
