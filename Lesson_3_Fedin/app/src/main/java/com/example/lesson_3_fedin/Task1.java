package com.example.lesson_3_fedin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Task1 extends AppCompatActivity {

    public static Intent createStartIntent(Context context){
        return new Intent(context, Task1.class);
    }

    final String[] data = { "Карта №7898769\nСпециалист", "Анастасия", "Антонина", "anykee.box@gmail.ru", "HIE023UOI88", "Санкт-Петербург"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        createToolbar();


        createInformation();
        createData(data);

        View viewLocation = findViewById(R.id.location);
        viewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"Кнопка изменения локации", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        View viewExitAccaunt = findViewById(R.id.exitAccaunt);
        viewExitAccaunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"Кнопка выхода из аккаунта", Toast.LENGTH_LONG);
                toast.show();
            }
        });





    }

    private void createData(String[] list){
        TextView textView1 = findViewById(R.id.toolbar).findViewById(R.id.textView);
        textView1.setText(list[0]);

        creatData(R.id.name, list[1]);
        creatData(R.id.surname, list[2]);
        creatData(R.id.e_mail, list[3]);
        creatData(R.id.login, list[4]);
        creatData(R.id.location, list[5]);
    }

    private void creatData(Integer id, String str){
        TextView textView1 = findViewById(id).findViewById(R.id.textView2);
        textView1.setText(str);
    }

    private void createInformation(){
        creatInfo(R.id.name, R.string._name);
        creatInfo(R.id.surname, R.string._surname);
        creatInfo(R.id.e_mail, R.string._e_mail);
        creatInfo(R.id.login, R.string._login);
        creatInfo(R.id.location, R.string._location);

    }

    private void creatInfo(Integer id, Integer str){
        TextView textView1 = findViewById(id).findViewById(R.id.textView1);
        textView1.setText(str);
    }

    private void createToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_tollbar);

        toolbar.inflateMenu(R.menu.my_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.item_menu) {
                    Toast toast = Toast.makeText(getApplicationContext(),"нажато меню", Toast.LENGTH_LONG);
                    toast.show();
                    return true;
                }
                return false;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }
}
