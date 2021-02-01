package com.example.homeworkemployees.screens.all_specialities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.homeworkemployees.R;
import com.example.homeworkemployees.adapters.SpecialitiesAdapter;
import com.example.homeworkemployees.pojo.Employee;
import com.example.homeworkemployees.screens.employees_of_speciality.EmployeesViewModel;

import java.util.ArrayList;
import java.util.List;

public class SpecialitiesActivity extends AppCompatActivity {
    private RecyclerView recyclerViewSpecialities;
    private SpecialitiesAdapter adapter;
    private EmployeesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewSpecialities = findViewById(R.id.recyclerViewSpecialities);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(EmployeesViewModel.class);


        adapter = new SpecialitiesAdapter();
        recyclerViewSpecialities.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSpecialities.setAdapter(adapter);

        List<String> spec = new ArrayList<>();
        spec.add("Manager");
        spec.add("Developer");

        adapter.setSpecialities(spec);

        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                for(Employee employee:employees){
                    Log.i("my_res",employee.getFName());
                }
            }
        });

        viewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                Log.i("my_res",throwable.getMessage());
            }
        });

        viewModel.loadData();

    }
}