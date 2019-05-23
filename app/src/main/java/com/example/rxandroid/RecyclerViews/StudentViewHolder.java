package com.example.rxandroid.RecyclerViews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rxandroid.Model.Student;
import com.example.rxandroid.R;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView = null;
    TextView addressTextView = null;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.student_name);
        addressTextView = itemView.findViewById(R.id.student_address);
    }


    public void populateDataWithModel(Student student) {
        nameTextView.setText(student.getName());
        addressTextView.setText(student.getAddress());
    }
}
