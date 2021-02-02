package com.example.homeworkemployees.screens.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.homeworkemployees.R;
import com.example.homeworkemployees.pojo.Employee;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        Intent intent = getIntent();
        if(intent!=null && intent.hasExtra("id")){

        }
    }
}