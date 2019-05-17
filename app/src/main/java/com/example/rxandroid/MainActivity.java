package com.example.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxandroid.Model.Student;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {


    private static String TAG = "RX-Java";
    private String[] rxJava = {"Hello", "from", "RX", "Java"};
    Observable<Student> observable;
    DisposableObserver<Student> disposableObserver;

    // Example of AsyncSubject
    AsyncSubject<Student> asyncSubject;


    AsyncSubject<String> asyncSubject2;
    BehaviorSubject<String> behaviourSubject;
    PublishSubject<String> publishSubject;
    ReplaySubject<String> replaySubject;

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
                Log.d(TAG, "" + s.getAddress());
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
        // asyncSubject.subscribe(disposableObserver);
        //  asyncSubjectExample();
        //  behaviourSubjectExample();
        //  publishSubjectExample();
        replaySubjectExample();



    }

    private void asyncSubjectExample() {

        Log.d(TAG, "********** Async Subject Example **************");

        asyncSubject2 = AsyncSubject.create();
        asyncSubject2.onNext("Syed Abdul Samad");

        asyncSubject2.subscribe(getDisposableObserver());
        asyncSubject2.onNext("Qanita Abdul Samad");

        asyncSubject2.subscribe(getDisposableObserver());

        asyncSubject2.onNext("Holah Samad");
        asyncSubject2.onComplete();
        asyncSubject2.subscribe(getDisposableObserver());

    }

    private void behaviourSubjectExample() {
        // Behaviour subject emits the most recent value that is emitted before
        // subscription and all the susiquent values after that
        Log.d(TAG, "********** Behaviour Subject Example **************");

        behaviourSubject = BehaviorSubject.createDefault("Name");
        behaviourSubject.subscribe(getDisposableObserver());
        behaviourSubject.onNext("Syed");

        behaviourSubject.subscribe(getDisposableObserver());
        behaviourSubject.onNext("Qanita");

        behaviourSubject.subscribe(getDisposableObserver());

        behaviourSubject.onNext("Holah");
        behaviourSubject.subscribe(getDisposableObserver());
        behaviourSubject.onComplete();

    }

    private void publishSubjectExample() {
        // Behaviour subject emits the most recent value that is emitted before
        // subscription and all the susiquent values after that

        Log.d(TAG, "********** Publish Subject Example **************");

        publishSubject = PublishSubject.create();
        publishSubject.subscribe(getDisposableObserver());
        publishSubject.onNext("Syed");
        publishSubject.onNext("Qanita");
        publishSubject.subscribe(getDisposableObserver());
        publishSubject.onNext("Holah");
        publishSubject.onComplete();
    }

    private void replaySubjectExample() {
        // Behaviour subject emits the most recent value that is emitted before
        // subscription and all the susiquent values after that

        Log.d(TAG, "********** Replay Subject Example **************");

        replaySubject = ReplaySubject.create();
        replaySubject.subscribe(getDisposableObserver());
        replaySubject.onNext("Syed");
        replaySubject.onNext("Qanita");
        replaySubject.subscribe(getDisposableObserver());
        replaySubject.onNext("Holah");
        replaySubject.onComplete();
    }



    private DisposableObserver<String> getDisposableObserver() {

        DisposableObserver<String> dObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG, s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "On Complete");
            }
        };
        return dObserver;
    }
}
