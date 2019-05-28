package com.example.rxandroid;

import com.example.rxandroid.Model.Student;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.BehaviorSubject;

public class StudentsDataHolder {


    private static StudentsDataHolder sharedHolder = null;
    BehaviorSubject<List<Student>> studentsSubject = BehaviorSubject.create();

    public BehaviorSubject<List<Student>> getStudentsSubject() {
        return studentsSubject;
    }

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
        studentsSubject.onNext(students);
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
        if(studentsSubject != null && studentsSubject.hasObservers()) {
            studentsSubject.onNext(students);
        }
    }


}