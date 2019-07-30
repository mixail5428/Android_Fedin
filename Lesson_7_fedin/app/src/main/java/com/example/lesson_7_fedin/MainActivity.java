package com.example.lesson_7_fedin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.lesson_7_fedin.bridge.Bridge;
import com.example.lesson_7_fedin.bridgeAPI.BridgeAPI;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements AdapterRecyclerViewBridges.ClickListener {
    Disposable subscribe;
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
        statusConnecting();
        updateBridges();
    }

    @SuppressLint("CheckResult")
    private void updateBridges() {
        BridgeAPI bridgeAPI = Controler.getBridgeAPI();

        subscribe = bridgeAPI.getData()
                .map(o -> o.getBridges())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bridges -> statusConnected(bridges),
                        error -> statusNone()
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe != null)
            subscribe.dispose();
    }

    private void findView() {
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        button.setOnClickListener(v -> statusConnecting());
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
        updateBridges();
    }

    private void statusConnected(List<Bridge> bridges) {
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
        startActivity(BridgeDetailActivity.createStartIntent(MainActivity.this, bridge));
    }
}
