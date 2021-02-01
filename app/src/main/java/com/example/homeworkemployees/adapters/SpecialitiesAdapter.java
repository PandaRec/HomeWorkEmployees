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
    private OnSpecialityClickListener onSpecialityClickListener;

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

    public interface OnSpecialityClickListener{
        void onSpecialityClick(int adapterPosition);
    }

    public void setOnSpecialityClickListener(OnSpecialityClickListener onSpecialityClickListener) {
        this.onSpecialityClickListener = onSpecialityClickListener;
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

    public class SpecialitiesViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewSpeciality;
        public SpecialitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSpeciality = itemView.findViewById(R.id.textViewSpeciality);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onSpecialityClickListener!=null){
                        onSpecialityClickListener.onSpecialityClick(getAdapterPosition());
                    }
                }
            });

        }
    }
}
