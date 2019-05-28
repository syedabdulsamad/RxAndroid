package com.example.rxandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.widget.Button;

import com.example.rxandroid.Model.Student;
import com.example.rxandroid.RecyclerViews.StudentAdapter;
import com.jakewharton.rxbinding3.view.RxView;

import io.reactivex.functions.Consumer;
import kotlin.Unit;

public class StudentsListActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    Button addStudentButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_list_activity);
        recyclerView = findViewById(R.id.students_recycler_view);
        addStudentButton = findViewById(R.id.add_student_button);
        LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        StudentAdapter adapter = new StudentAdapter();
        recyclerView.setAdapter(adapter);


        RxView.clicks(addStudentButton).subscribe(new Consumer<Unit>() {
            @Override
            public void accept(Unit unit) throws Exception {
                StudentsDataHolder.sharedInstance().addStudent(new Student("New Student", "New Address", 20));
            }
        });

    }


}
