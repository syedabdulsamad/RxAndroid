package com.example.rxandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;

import com.example.rxandroid.RecyclerViews.StudentAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public class StudentsListActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_list_activity);
        recyclerView = findViewById(R.id.students_recycler_view);
        LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        StudentAdapter adapter = new StudentAdapter();
        recyclerView.setAdapter(adapter);


    }
}
