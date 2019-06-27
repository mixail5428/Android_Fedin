package com.example.lesson_4_fedin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<DataCard> listDataCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDataList();

        createToolbar();

        createRecyclerView();
    }

    private void createToolbar(){
        Toolbar toolbar = findViewById(R.id.my_toolbar);

        toolbar.inflateMenu(R.menu.my_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) { //////обрабатываю нажатия на элементы toolbar
                int id = item.getItemId();

                switch (id){
                    case R.id._home:
                        Toast toast = Toast.makeText(getApplicationContext(),"нажат домик", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0,100);
                        toast.show();
                        break;

                    case R.id.info:
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("Диалоговое окно")
                                .setMessage("Здесь различный текст")
                                .setCancelable(false);

                        dialog.setPositiveButton("Все классно", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        dialog.setNegativeButton("Все плохо", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        dialog.show();

                        break;
                }
                return true;
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  /// нажатие на кнопку назад
                Toast toast = Toast.makeText(getApplicationContext(),"нажато назад", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    private void createRecyclerView(){

        recyclerView = findViewById(R.id.my_recyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(recyclerView.getAdapter().getItemViewType(position) == MyAdapter.shortType)
                    return 1;
                else
                    return 2;
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new MyAdapter(listDataCard);
        ((MyAdapter) adapter).setClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataCard item) {
                Snackbar.make(findViewById(R.id.activityMain), item.getTitle(),Snackbar.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyItemDecoration((int)getResources().getDimensionPixelSize(R.dimen.space))); // расстояние между view в recyclerView


    }

    //создаю список с элементами для recyclerView
    private void createDataList(){
        listDataCard = new ArrayList<>();
        listDataCard.add(new DataCard(this ,R.drawable.ic_bill,"Квитанция","- 40 080,55 \u20BD ",R.color.coral));
        listDataCard.add(new DataCard(this ,R.drawable.ic_counter,"Счетчик","Подайте показания ",R.color.coral ));
        listDataCard.add(new DataCard(this ,R.drawable.ic_installment,"Рассрочка","Сл. платеж 25.02.2018 "));
        listDataCard.add(new DataCard(this ,R.drawable.ic_insurance,"Страхование","Полис до 12.01.2019"));
        listDataCard.add(new DataCard(this ,R.drawable.ic_tv,"Интернет и ТВ","Балланс 350 \u20BD" ));
        listDataCard.add(new DataCard(this ,R.drawable.ic_homephone,"Домофон","Подключен"));
        listDataCard.add(new DataCard(this ,R.drawable.ic_guard,"Охрана","Нет"));
        listDataCard.add(new DataCard(this ,R.drawable.ic_uk_contacts,"Контакты УК и служб"));
        listDataCard.add(new DataCard(this ,R.drawable.ic_request,"Мои заявки"));
        listDataCard.add(new DataCard(this ,R.drawable.ic_about,"Памятка жителя А101"));
    }

}
