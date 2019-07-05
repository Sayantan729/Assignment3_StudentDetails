package com.example.studentdetails;

import android.app.Application;

import io.realm.Realm;

public class StudentRealm extends Application {

    public void onCreate()
    {
        super.onCreate();
        Realm.init(this);
    }
}
