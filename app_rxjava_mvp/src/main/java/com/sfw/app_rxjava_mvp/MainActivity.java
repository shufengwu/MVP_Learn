package com.sfw.app_rxjava_mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,IWeatherView {
    private final String AppKey = "12c5afd699940";
    private Button button;
    private ListView show;
    private ProgressBar progress;
    WeatherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        show = (ListView) findViewById(R.id.show);
        progress = (ProgressBar) findViewById(R.id.progess);
        presenter = new WeatherPresenter(new WeatherModel(),this);
        initShow();
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            presenter.load();
        }
    }

    @Override
    public void initShow() {
        show.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadStart() {
        show.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showWeather(List<String>list) {
        show.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list));
    }

    @Override
    public void showLoadSuccess() {
        show.setVisibility(View.VISIBLE);
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadFailed() {
        show.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);
    }
}
