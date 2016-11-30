package com.example.addrxjava;

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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String AppKey = "12c5afd699940";
    private Button button;
    private ListView show;
    private ProgressBar progress;
    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        show = (ListView) findViewById(R.id.show);
        progress = (ProgressBar) findViewById(R.id.progess);
        initShow();
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            showLoadStart();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://apicloud.mob.com")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
            WeatherService weatherService = retrofit.create(WeatherService.class);
            weatherService.getWeather(AppKey, "朝阳", "北京")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<WeatherBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            showLoadFailed();
                        }

                        @Override
                        public void onNext(WeatherBean weatherBean) {
                            final WeatherBean wB = weatherBean;
                            List<WeatherBean.ResultBean.FutureBean> futureList = wB.getResult().get(0).getFuture();
                            for (int i = 0; i < futureList.size(); i++) {
                                list.add("\n" + futureList.get(i).getDate() + "\n"
                                        + "白天：" + futureList.get(i).getDayTime() + "\n"
                                        + "夜间：" + futureList.get(i).getNight() + "\n"
                                        + futureList.get(i).getTemperature() + "\n"
                                        + futureList.get(i).getWeek() + "\n"
                                        + futureList.get(i).getWind() + "\n");
                            }
                            show.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list));


                            showLoadSuccess();
                        }
                    });
        }
    }

    public void initShow() {
        show.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);
    }

    public void showLoadStart() {
        show.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.VISIBLE);
    }

    public void showLoadSuccess() {
        show.setVisibility(View.VISIBLE);
        progress.setVisibility(View.INVISIBLE);
    }

    public void showLoadFailed() {
        show.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);
    }
}

/*    final WeatherBean wB = weatherBean;
    runOnUiThread(new Runnable() {
        @Override
        public void run() {
            List<WeatherBean.ResultBean.FutureBean> futureList = wB.getResult().get(0).getFuture();
            for(int i=0;i<futureList.size();i++){
                list.add("\n"+futureList.get(i).getDate()+"\n"
                        +"白天："+futureList.get(i).getDayTime()+"\n"
                        +"夜间："+futureList.get(i).getNight()+"\n"
                        +futureList.get(i).getTemperature()+"\n"
                        +futureList.get(i).getWeek()+"\n"
                        +futureList.get(i).getWind()+"\n");
            }
            show.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list));
        }
    });
        showLoadSuccess();*/
