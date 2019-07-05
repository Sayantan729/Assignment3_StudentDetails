package com.example.studentdetails;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.RealmResults;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    private RealmResults<Student> studentRealmResults;
    private Context context;
    public StudentAdapter(RealmResults<Student> studentRealmResults1,Context context1)
    {
        studentRealmResults=studentRealmResults1;
        context=context1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
        return new MyViewHolder(view);

    }
    public void onBindViewHolder(MyViewHolder holder,int position)
    {
        Student student=studentRealmResults.get(position);
        holder.nametext.setText(student.getName());
        holder.depttext.setText(student.getDept());
        holder.phonetext.setText("+91 "+student.getPhone().substring(0,5)+" "+student.getPhone().substring(5));
        holder.rolltext.setText("Roll No "+student.getRoll());
        holder.gendertext.setText(student.getGender());
        String dept=student.getDept();
        if(dept.replaceAll(" ","").equals("ComputerScienceEngineering"))
            holder.itemView.findViewById(R.id.row).setBackgroundColor(Color.rgb(0,200,53));
        else if(dept.replaceAll(" ","").equals("InformationTechnology"))
            holder.itemView.findViewById(R.id.row).setBackgroundColor(Color.rgb(63,51,181));
        else if (dept.replaceAll(" ","").equals("ElectronicsEngineering"))
            holder.itemView.findViewById(R.id.row).setBackgroundColor(Color.rgb(255,214,0));
        else
            holder.itemView.findViewById(R.id.row).setBackgroundColor(Color.rgb(183,28,28));


    }
    public int getItemCount() {
        return studentRealmResults.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView nametext,gendertext,rolltext,phonetext,depttext;
        public MyViewHolder(@NonNull View itemview)
        {
            super(itemview);
            nametext=itemview.findViewById(R.id.nametext);
            gendertext=itemview.findViewById(R.id.gendertext);
            rolltext=itemview.findViewById(R.id.rolltext);
            phonetext=itemview.findViewById(R.id.phonetext);
            depttext=itemview.findViewById(R.id.depttext);
        }
    }


}
