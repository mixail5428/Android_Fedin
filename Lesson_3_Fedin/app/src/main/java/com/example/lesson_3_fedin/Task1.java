package com.example.lesson_3_fedin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Task1 extends AppCompatActivity {

    public static Intent createStartIntent(Context context){
        return new Intent(context, Task1.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);



        Toolbar toolbar = (Toolbar) findViewById(R.id.my_tollbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.item_menu) {
            Toast toast = Toast.makeText(getApplicationContext(),"нажато меню", Toast.LENGTH_LONG);
            toast.show();
        }

        if(id == android.R.id.home)
            finish();

        return true;
    }
}
