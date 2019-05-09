package com.example.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {


    private static String TAG = "RX-Java";
    private String[] rxJava = {"Hello", "from", "RX", "Java"};
    Observable<String> observable;
    DisposableObserver<String> disposableObserver;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposableObserver.dispose();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView textView = (TextView) findViewById(R.id.textView);

        observable = Observable.fromArray(rxJava).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return s.toUpperCase();
            }
        });

        disposableObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                //  textView.setText(s);
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
        observable.subscribeWith(disposableObserver);
    }
}
