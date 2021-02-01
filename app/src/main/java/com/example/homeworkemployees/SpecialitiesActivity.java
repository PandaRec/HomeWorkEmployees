package com.example.homeworkemployees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.homeworkemployees.adapters.SpecialitiesAdapter;

import java.util.ArrayList;
import java.util.List;

public class SpecialitiesActivity extends AppCompatActivity {
    private RecyclerView recyclerViewSpecialities;
    private SpecialitiesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewSpecialities = findViewById(R.id.recyclerViewSpecialities);

        adapter = new SpecialitiesAdapter();
        recyclerViewSpecialities.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSpecialities.setAdapter(adapter);

        List<String> spec = new ArrayList<>();
        spec.add("Manager");
        spec.add("Developer");

        adapter.setSpecialities(spec);
    }
}