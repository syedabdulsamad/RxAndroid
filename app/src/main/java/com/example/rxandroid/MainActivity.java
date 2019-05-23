package com.example.rxandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rxandroid.Model.Student;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Unit;

public class MainActivity extends AppCompatActivity {


    private static String TAG = "RX-Java";
    private String[] rxJava = {"Hello", "from", "RX", "Java"};
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

        TextView textView = findViewById(R.id.textView);
        EditText textEdit = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);

        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("Syed", "address1", 20));
        students.add(new Student("Syed", "address1", 20));
        students.add(new Student("Abdul", "address2", 22));
        students.add(new Student("Abdul", "address2", 22));
        students.add(new Student("Samad", "address3", 234));
        students.add(new Student("Samad", "address3", 254));


        disposable = RxTextView.textChanges(textEdit).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                textView.setText(charSequence);
            }
        });


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
