package com.example.studentdetails;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import io.realm.Realm;

public class StudentIntentService extends IntentService {
    private String message="";
    private String TAG="Sayantan";
    public StudentIntentService() {
        super("");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle bundle=intent.getBundleExtra("Bundle");
        boolean store=bundle.getBoolean("Valid");
        String details[]=bundle.getStringArray("Details");
        if(store)
        {
            Realm studentdata=Realm.getDefaultInstance();
            studentdata.beginTransaction();
            try {
                Student studentcheck=studentdata.where(Student.class).equalTo("id",details[1].replaceAll(" ","")+details[2].replaceAll(" ","")).findFirst();
                if(studentcheck==null)
                {Student student =studentdata.createObject(Student.class,details[1].replaceAll(" ","")+details[2].replaceAll(" ",""));
                    student.setName(details[0]);
                    student.setDept(details[1]);
                    student.setRoll(Integer.parseInt(details[2]));
                    student.setPhone(details[3]);
                    student.setGender(details[4]);
                    studentdata.commitTransaction();
                    message="Details entered";}
                else
                    message="Already entered";
            }
            catch (Exception e)
            {
                studentdata.cancelTransaction();
                message="Failure "+e;
            }
            studentdata.close();}
        }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if(!message.equals(""))
        EventBus.getDefault().post(message);
    }
}
