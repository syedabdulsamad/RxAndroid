package com.example.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {


    private static String TAG = "RX-Java";
    private String[] rxJava = {"Hello", "from", "RX", "Java"};
    Observable<List<String>> observable;
    DisposableObserver<List<String>> disposableObserver;


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

        /*observable = Observable.fromArray(rxJava).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return s.toUpperCase();
            }
        });*/


        observable = Observable.fromArray(rxJava).
                flatMap(new Function<String, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(String s) throws Exception {

                        return Observable.just(s, s + "1", s + "2").buffer(3).
                                filter(new Predicate<List<String>>() {
                                    @Override
                                    public boolean test(List<String> strings) throws Exception {
                                       return strings.get(0) == "RX";
                                    }
                                });
                    }
                });


        disposableObserver = new DisposableObserver<List<String>>() {
            @Override
            public void onNext(List<String> s) {
                //  textView.setText(s);

                Log.d(TAG, ""+s.toString());

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
