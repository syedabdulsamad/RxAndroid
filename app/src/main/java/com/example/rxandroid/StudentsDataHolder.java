package com.example.rxandroid;

import com.example.rxandroid.Model.Student;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;

public class StudentsDataHolder {


    private static StudentsDataHolder sharedHolder = null;
    Observable<List<Student>> studentsObservable = null;
    DisposableObserver<List<Student>> studentsObserver = null;

    public List<Student> getStudents() {
        return students;
    }

    private List<Student> students = null;

    public static StudentsDataHolder sharedInstance() {
        if (sharedHolder == null) {
            sharedHolder = new StudentsDataHolder();
        }
        return sharedHolder;
    }

    private StudentsDataHolder() {
        fillStudentsList();
       // studentsObservable = Observable.from()
    }

    private void fillStudentsList() {

        students = new ArrayList<Student>();
        students.add(new Student("Syed", "address1", 20));
        students.add(new Student("Abdul", "address1", 20));
        students.add(new Student("Samad", "address2", 22));
        students.add(new Student("Qanita", "address2", 22));
        students.add(new Student("Holah", "address3", 234));
    }

    public void addStudent(Student s) {
        students.add(s);
    }


}