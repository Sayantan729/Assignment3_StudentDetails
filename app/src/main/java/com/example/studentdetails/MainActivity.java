package com.example.studentdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private EditText inputname,inputphone,inputroll;
    private EditText inputdept;
    private Switch genderselect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputdept=findViewById(R.id.inputdept);
        inputname=findViewById(R.id.inputname);
        inputphone=findViewById(R.id.inputphone);
        inputroll=findViewById(R.id.inputroll);
        genderselect=findViewById(R.id.genderselect);
        EventBus.getDefault().register(this);
    }
    public void onSave(View view)
    {
        boolean store1=false;
        boolean store2=false;

        if(!inputname.getText().toString().replaceAll(" ","").equals(""))
        {
            if(!inputdept.getText().toString().replaceAll(" ","").equals(""))
            {
                if(!inputdept.getText().toString().replaceAll(" ","").equals("ComputerScienceEngineering")&&!inputdept.getText().toString().replaceAll(" ","").equals("InformationTechnology")&&!inputdept.getText().toString().replaceAll(" ","").equals("ElectronicsEngineering")&&!inputdept.getText().toString().replaceAll(" ","").equals("ElectricalEngineering"))
                {
                    Toast.makeText(this, "Enter full department name with correct spelling", Toast.LENGTH_SHORT).show();
                    inputdept.requestFocus();
                }
                else
                    store2=true;
                if(!inputroll.getText().toString().replaceAll(" ","").equals(""))
                {
                    if(!inputphone.getText().toString().replaceAll(" ","").equals(""))
                    {
                        if(inputphone.getText().toString().replaceAll(" ","").length()!=10)
                            Toast.makeText(this, "Phone number should be of 10 digits", Toast.LENGTH_SHORT).show();
                        else
                            store1=true;
                    }
                    else
                    {Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT).show();
                    inputphone.requestFocus();}


                }
                else
                {Toast.makeText(this, "Enter roll no", Toast.LENGTH_SHORT).show();
                inputroll.requestFocus();}
            }
            else
            {Toast.makeText(this, "Enter department", Toast.LENGTH_SHORT).show();
            inputdept.requestFocus();}
        }
        else
        {Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
        inputname.requestFocus();}
        boolean store=(store1)&&(store2);
        Intent intent=new Intent(this,StudentIntentService.class);
        Bundle bundle=new Bundle();
        bundle.putBoolean("Valid",store);
        String gender = "";
        if (genderselect.isChecked())
            gender = "Female";
        else
            gender = "Male";
        String dept=inputdept.getText().toString();
        String roll=inputroll.getText().toString();
        String name=inputname.getText().toString();
        String phone=inputphone.getText().toString();
        String details[]=new String[5];
        details[0]=name.trim();
        details[1]=dept.trim();
        details[2]=roll.trim();
        details[3]=phone.trim();
        details[4]=gender;
        bundle.putStringArray("Details",details);
        intent.putExtra("Bundle",bundle);
        startService(intent);
    }
    public void onDisplay(View view)
    {
        Intent intent=new Intent(this,DisplayActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void showToast(String s)
    {
        Toast.makeText(this,s, Toast.LENGTH_SHORT).show();
    }
}
