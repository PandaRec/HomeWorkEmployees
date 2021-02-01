package com.example.homeworkemployees.screens.employees_of_speciality;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.homeworkemployees.R;

public class EmployeesOfSpecialityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_of_speciality);

        Intent intent = getIntent();
        if(intent!=null && intent.hasExtra("speciality")){
            Toast.makeText(this, ""+intent.getStringExtra("speciality"), Toast.LENGTH_SHORT).show();
            getSupportActionBar().setTitle(intent.getStringExtra("speciality"));

        }
    }
}