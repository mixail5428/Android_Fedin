package com.example.lesson_5_fedin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity5 extends AppCompatActivity {

    Data data;
    TextView textView;

    public static final String MESSAGE = "message";
    public static final String SAVE_DATA = "saveData";


    public static Intent createStartIntent(Context context){
        return new Intent(context, Activity5.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

//        if(data != null) {
//            TextView textView = findViewById(R.id.textView);
//            textView.setText(data.getValue());
//        }


        goToActivity3();

        SaveData();
    }

    public void SaveData(){
        Button button = findViewById(R.id.buttonSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editText);
                String message = editText.getText().toString();
                TextView textView = findViewById(R.id.textView);
                textView.setText(message);
                data = new Data(message);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(data != null)
        outState.putParcelable(Activity5.SAVE_DATA, data);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        data = savedInstanceState.getParcelable(Activity5.SAVE_DATA);
        if(data != null) {
            TextView textView = findViewById(R.id.textView);
            textView.setText(data.getValue());
        }
    }

    public void goToActivity3(){
        Button buttonGoTo3 = findViewById(R.id.button_go_to_3);
        buttonGoTo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editText);
                String message = editText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra(Activity5.MESSAGE, message);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
