package com.example.lesson_7_fedin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lesson_7_fedin.bridge.Bridge;
import com.example.lesson_7_fedin.bridgeAPI.BridgeAPI;

import java.util.Iterator;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements AdapterRecyclerViewBridges.ClickListener {
    final String LOG = "mLOG";

    final int STATUS_NONE = 0; // нет подключения
    final int STATUS_CONNECTING = 1; // подключаемся
    final int STATUS_CONNECTED = 2; // подключено

    List<Bridge> bridges;
    Handler h;
    TextView textView;
    ProgressBar progressBar;
    Button button;
    RecyclerView recyclerView;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createToolbar();
        findView();

        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case STATUS_NONE:
                        statusNone();
                        break;

                    case STATUS_CONNECTING:
                        statusConnecting();
                        updateBridges();
                        break;

                    case STATUS_CONNECTED:
                        statusConnected();
                        break;

                }
            }
        };

        h.sendEmptyMessage(STATUS_CONNECTING);
    }

    @SuppressLint("CheckResult")
    private void updateBridges() {
        BridgeAPI bridgeAPI = Controler.getBridgeAPI();

        bridgeAPI.getData()
                .map(o -> o.getBridges())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bridges1 -> {
                            bridges = bridges1;
                            h.sendEmptyMessage(STATUS_CONNECTED);
                        },
                        error -> h.sendEmptyMessage(STATUS_NONE)
                );
    }

    private void findView() {
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        button.setOnClickListener(v -> h.sendEmptyMessage(STATUS_CONNECTING));
    }

    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.The_Most_SPB);
    }

    private void statusNone() {
        button.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        textView.setText(R.string.status_none);
    }

    private void statusConnecting() {
        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        textView.setText(R.string.status_connecting);
    }

    private void statusConnected() {
        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        AdapterRecyclerViewBridges adapter = new AdapterRecyclerViewBridges(bridges, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void clickRecyclerView(Bridge bridge) {
        startActivity(DescriptionBridge.createStartIntent(MainActivity.this, bridge));
    }
}
