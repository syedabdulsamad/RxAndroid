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

public class StudentAdapter extends RecyclerView.Adapter {

    StudentsDataHolder dataHolder = null;

    public StudentAdapter() {
        dataHolder = StudentsDataHolder.sharedInstance();
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
