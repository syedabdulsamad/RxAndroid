package com.example.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {


    private static String TAG = "RX-Java";
    private String rxJava = "Hello fro RX Java";
    Observable<String> observable;
    Observer<String> observer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        observable = Observable.just(rxJava);
        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "Disposed");
            }

            @Override
            public void onNext(String s) {

                Log.d(TAG, s);
            }

            @Override
            public void onError(Throwable e) {

                Log.d(TAG, e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

                Log.d(TAG, "Completed");
            }
        };
        observable.subscribe(observer);


    }
}
