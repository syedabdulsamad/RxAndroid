package com.example.rxandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import kotlin.Unit;

public class MainActivity extends AppCompatActivity {


    private static String TAG = "RX-Java";
    private List<String> rxJava = new ArrayList<String>();

    Disposable disposable;

    // Example of AsyncSubject

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxJava.add("Hello");
        Observable<List<String>> observable = Observable.just(rxJava);


        DisposableObserver<List<String>> observer = new DisposableObserver<List<String>>() {
            @Override
            public void onNext(List<String> strings) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribeWith(observer);

        rxJava.add("RX");
        rxJava.add("Java");


        TextView textView = findViewById(R.id.textView);

        EditText textEdit = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        RxView.clicks(button).subscribe(new Consumer<Unit>() {
            @Override
            public void accept(Unit unit) throws Exception {
                moveToSecondActivity();
            }
        });

    }

    public void moveToSecondActivity() {

        Intent intent = new Intent(this, StudentsListActivity.class);
        startActivity(intent);

    }

}
