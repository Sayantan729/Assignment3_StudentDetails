package com.example.studentdetails;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;

public class DisplayActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        recyclerView=findViewById(R.id.student_recycler);
        Realm studentdata=Realm.getDefaultInstance();
        RealmResults<Student> studentRealmResults=studentdata.where(Student.class).findAll();
        StudentAdapter studentAdapter=new StudentAdapter(studentRealmResults,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentAdapter);


    }
}
