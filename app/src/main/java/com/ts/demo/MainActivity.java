package com.ts.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ts.demo.observer.Observable;
import com.ts.demo.observer.Observer;
import com.ts.demo.observer.Wheater;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    private CustomTextView tvDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initJPush();

        initViews();

        initData();

        initObserver();
        
    }

    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this.getApplicationContext());
    }

    private void initObserver() {
        Observer<Wheater> observer1 = new Observer<Wheater>() {
            @Override
            public void update(Observable<Wheater> observable, Wheater data) {
                Log.w("MainActivity", "Observer1 data changed:" + data);
            }
        };

        Observer<Wheater> observer2 = new Observer<Wheater>() {
            @Override
            public void update(Observable<Wheater> observable, Wheater data) {
                Log.w("MainActivity", "Observer2 data changed:" + data);
            }
        };

        Observable<Wheater> observabale = new Observable<>();

        observabale.register(observer1);
        observabale.register(observer2);

        Wheater w1 = new Wheater("多云转晴了！");
        Wheater w2 = new Wheater("下大雨了！");

        observabale.notifyObservers(w1);

        observabale.unRegister(observer1);

        observabale.notifyObservers(w2);
    }

    private void initData() {
        Person.Builder builder = new Person.Builder();
        Person person = builder.age(12)
                .name("ts")
                .height(23)
                .weight(32)
                .build();
    }

    private void initViews() {
        tvDemo = (CustomTextView) findViewById(R.id.tv_demo);
    }
}
