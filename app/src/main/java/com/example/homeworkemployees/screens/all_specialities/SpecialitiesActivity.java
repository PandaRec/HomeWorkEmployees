package com.example.homeworkemployees.screens.all_specialities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.homeworkemployees.R;
import com.example.homeworkemployees.adapters.SpecialitiesAdapter;
import com.example.homeworkemployees.pojo.Employee;
import com.example.homeworkemployees.pojo.Specialty;
import com.example.homeworkemployees.screens.employees_of_speciality.EmployeesOfSpecialityActivity;
import com.example.homeworkemployees.screens.employees_of_speciality.EmployeesViewModel;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SpecialitiesActivity extends AppCompatActivity {
    private RecyclerView recyclerViewSpecialities;
    private SpecialitiesAdapter adapter;
    private EmployeesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewSpecialities = findViewById(R.id.recyclerViewSpecialities);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(EmployeesViewModel.class);


        adapter = new SpecialitiesAdapter();
        recyclerViewSpecialities.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSpecialities.setAdapter(adapter);



        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                List<String> specialities = new ArrayList<>(viewModel.getAllSpecialities(employees));
                adapter.setSpecialities(specialities);

            }
        });

        viewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                Log.i("my_res",throwable.getMessage());
            }
        });

        adapter.setOnSpecialityClickListener(new SpecialitiesAdapter.OnSpecialityClickListener() {
            @Override
            public void onSpecialityClick(int adapterPosition) {
                Intent intent = new Intent(SpecialitiesActivity.this, EmployeesOfSpecialityActivity.class);
                intent.putExtra("speciality",adapter.getSpecialities().get(adapterPosition));
                startActivity(intent);
            }
        });

        viewModel.loadData();

    }
}