package com.example.homeworkemployees.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.homeworkemployees.R;
import com.example.homeworkemployees.pojo.Employee;
import com.example.homeworkemployees.screens.employees_of_speciality.EmployeesViewModel;
import java.util.ArrayList;
import java.util.List;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.EmployeeViewHolder> {
    private List<Employee> employees;

    public static OnEmployeeClickListener onEmployeeClickListener;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    public EmployeesAdapter() {
        this.employees = new ArrayList<>();
    }

    public interface OnEmployeeClickListener{
        void onEmployeeClick(int adapterPosition);
    }

    public void setOnEmployeeClickListener(OnEmployeeClickListener onEmployeeClickListener) {
        this.onEmployeeClickListener = onEmployeeClickListener;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.short_info_employee,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
    Employee employee = employees.get(position);
    holder.textViewName.setText(employee.getFName());
    holder.textViewLastName.setText(employee.getLName());
    int age = EmployeesViewModel.getAge(employee.getBirthday());
    if(age!=-1){
        holder.textViewAge.setText(String.format("%s",age));
    }else {
        holder.textViewAge.setText("-");
        }
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewLastName;
        private TextView textViewAge;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewFirstName);
            textViewLastName = itemView.findViewById(R.id.textViewLastName);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onEmployeeClickListener!=null){
                        onEmployeeClickListener.onEmployeeClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
