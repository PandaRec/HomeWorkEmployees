package com.example.homeworkemployees.screens.employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.homeworkemployees.R;
import com.example.homeworkemployees.pojo.Employee;
import com.example.homeworkemployees.screens.employees_of_speciality.EmployeesViewModel;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class EmployeeActivity extends AppCompatActivity {
    private Employee currentEmployee;
    private int employeeId;
    private String speciality;

    private TextView textViewName;
    private TextView textViewBirthday;
    private TextView textViewSpeciality;


    private RoundedImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        imageView = findViewById(R.id.imageViewPerson);
        textViewName = findViewById(R.id.textViewNameEmployee);
        textViewBirthday = findViewById(R.id.textViewBirthdayEmployee);
        textViewSpeciality = findViewById(R.id.textViewEmployeeSpeciality);



        Intent intent = getIntent();

      if(intent!=null && intent.hasExtra("employee")){
           currentEmployee = new Gson().fromJson(intent.getStringExtra("employee"),Employee.class);
      }

        if(intent!=null && intent.hasExtra("speciality")){
            speciality = intent.getStringExtra("speciality");
        }

        textViewName.setText(String.format("%s %s",currentEmployee.getFName(),currentEmployee.getFName()));
        textViewBirthday.setText(currentEmployee.getBirthday());
        textViewSpeciality.setText(speciality);




        if(currentEmployee!=null && currentEmployee.getAvatrUrl()!=null && !currentEmployee.getAvatrUrl().equals("")){

            Transformation transformation = new RoundedTransformationBuilder()

                    .cornerRadiusDp(30)
                    .oval(false)
                    .build();

            Picasso.get()
                    .load(currentEmployee.getAvatrUrl())
                    .fit()
                    .transform(transformation)
                    .into(imageView);

        }
        else {
            Transformation transformation = new RoundedTransformationBuilder()
                    //.borderColor(Color.BLACK)
                    //.borderWidthDp(3)
                    .cornerRadiusDp(30)
                    .oval(false)
                    .build();

            Picasso.get()
                    .load(R.drawable.no_avatar_icon2)
                    .fit()
                    .transform(transformation)
                    .into(imageView);
        }


    }

}