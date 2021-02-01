package com.example.homeworkemployees.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkemployees.R;

import java.util.ArrayList;
import java.util.List;

public class SpecialitiesAdapter extends RecyclerView.Adapter<SpecialitiesAdapter.SpecialitiesViewHolder> {
    private List<String> specialities;

    public List<String> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<String> specialities) {
        this.specialities = specialities;
        notifyDataSetChanged();
    }

    public SpecialitiesAdapter() {
        this.specialities = new ArrayList<>();
    }

    @NonNull
    @Override
    public SpecialitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spesiality_item,parent,false);
        return new SpecialitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialitiesViewHolder holder, int position) {
    holder.textViewSpeciality.setText(specialities.get(position));
    }

    @Override
    public int getItemCount() {
        return specialities.size();
    }

    public static class SpecialitiesViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewSpeciality;
        public SpecialitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSpeciality = itemView.findViewById(R.id.textViewSpeciality);
        }
    }
}
