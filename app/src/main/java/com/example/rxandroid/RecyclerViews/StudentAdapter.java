package com.example.rxandroid.RecyclerViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rxandroid.Model.Student;
import com.example.rxandroid.R;
import com.example.rxandroid.StudentsDataHolder;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.BehaviorSubject;

public class StudentAdapter extends RecyclerView.Adapter {

    StudentsDataHolder dataHolder = null;
    BehaviorSubject<List<Student>> studentsSubject = null;

    public StudentAdapter() {

        dataHolder = StudentsDataHolder.sharedInstance();
        studentsSubject = StudentsDataHolder.sharedInstance().getStudentsSubject();
        studentsSubject.subscribe(getObserver());
    }

    private Observer<List<Student>> getObserver() {
        return new DisposableObserver<List<Student>>() {
            @Override
            public void onNext(List<Student> students) {
                notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_list_item,
                viewGroup, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        StudentViewHolder vh = (StudentViewHolder) viewHolder;
        Student s = dataHolder.getStudents().get(i);
        Log.d("A2", s.toString());
        vh.populateDataWithModel(s);
    }

    @Override
    public int getItemCount() {
        return dataHolder.getStudents().size();
    }
}
