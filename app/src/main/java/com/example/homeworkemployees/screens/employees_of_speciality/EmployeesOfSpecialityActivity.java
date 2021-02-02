package com.example.homeworkemployees.screens.employees_of_speciality;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeworkemployees.R;
import com.example.homeworkemployees.adapters.EmployeesAdapter;
import com.example.homeworkemployees.pojo.Employee;
import com.example.homeworkemployees.screens.all_specialities.SpecialitiesActivity;
import com.example.homeworkemployees.screens.employee.EmployeeActivity;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class EmployeesOfSpecialityActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView textViewCurrentSpeciality;
    private EmployeesAdapter adapter;
    private EmployeesViewModel viewModel;

    private static String currentSpeciality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_of_speciality);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }

        recyclerView = findViewById(R.id.recyclerViewEmployees);
        textViewCurrentSpeciality = findViewById(R.id.textViewCurrentSpeciality);
        adapter = new EmployeesAdapter();
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(EmployeesViewModel.class);


        Intent intent = getIntent();
        if(intent!=null && intent.hasExtra("speciality")){
            currentSpeciality = intent.getStringExtra("speciality");
           // Toast.makeText(this, ""+intent.getStringExtra("speciality"), Toast.LENGTH_SHORT).show();
            //getSupportActionBar().setTitle("работники по специальности "+intent.getStringExtra("speciality"));
            textViewCurrentSpeciality.setText(String.format("По цпециальности \"%s\"",currentSpeciality));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                //List<Employee> all = viewModel.sortEmployeesBySpeciality(employees,currentSpeciality);
                adapter.setEmployees(viewModel.sortEmployeesBySpeciality(employees,currentSpeciality));
            }
        });
        viewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                Toast.makeText(EmployeesOfSpecialityActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnEmployeeClickListener(new EmployeesAdapter.OnEmployeeClickListener() {
            @Override
            public void onEmployeeClick(int adapterPosition) {
                Intent intent = new Intent(EmployeesOfSpecialityActivity.this, EmployeeActivity.class);
                //intent.putExtra("id",adapter.getEmployees().get(0).getId());
                intent.putExtra("employee", new Gson().toJson(adapter.getEmployees().get(adapterPosition)));
                startActivity(intent);
            }
        });





    }


}