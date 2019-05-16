package com.example.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxandroid.Model.Student;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.AsyncSubject;

public class MainActivity extends AppCompatActivity {


    private static String TAG = "RX-Java";
    private String[] rxJava = {"Hello", "from", "RX", "Java"};
    Observable<Student> observable;
    DisposableObserver<Student> disposableObserver;

    // Example of AsyncSubject
    AsyncSubject<Student> asyncSubject;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposableObserver.dispose();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("Syed", "address1", 20));
        students.add(new Student("Syed", "address1", 20));
        students.add(new Student("Abdul", "address2", 22));
        students.add(new Student("Abdul", "address2", 22));
        students.add(new Student("Samad", "address3", 234));
        students.add(new Student("Samad", "address3", 254));


        observable = Observable.fromIterable(students).distinct(s -> s.getAge())
                .skip(1) // skip first n operators
                .skipLast(1); // skip last n operators

        // creating the subject
        asyncSubject = AsyncSubject.create();
        // observable subscribing with Subject
        observable.subscribe(asyncSubject);

        disposableObserver = new DisposableObserver<Student>() {
            @Override
            public void onNext(Student s) {
                //  textView.setText(s);
                Log.d(TAG, "" + s.getName());
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
        //observable.subscribeWith(disposableObserver);

        // Async subject subscribing with Observer.
        asyncSubject.subscribe(disposableObserver);
    }
}
