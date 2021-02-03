package com.example.homeworkemployees.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.util.StringUtil;

import com.example.homeworkemployees.R;
import com.example.homeworkemployees.pojo.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    holder.textViewName.setText(normalizeString(employee.getFName()));
    holder.textViewLastName.setText(normalizeString(employee.getLName()));
    int age = getAge(employee.getBirthday());
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

    private static int getAge(String birthdayString)
    {
        Date birthday = stringToDate(birthdayString);
        if(birthday==null)
            return -1;
        GregorianCalendar today = new GregorianCalendar();
        GregorianCalendar bday = new GregorianCalendar();
        GregorianCalendar bdayThisYear = new GregorianCalendar();

        bday.setTime(birthday);
        bdayThisYear.setTime(birthday);
        bdayThisYear.set(Calendar.YEAR, today.get(Calendar.YEAR));

        int age = today.get(Calendar.YEAR) - bday.get(Calendar.YEAR);

        if(today.getTimeInMillis() < bdayThisYear.getTimeInMillis())
            age--;

        return age;
    }

    private static Date stringToDate(String string){
        if(string==null){
            return null;
        }

        String[] split = string.split("-");
        Date date = null;

        if(split[0].length()>2){
            try {
                date=new SimpleDateFormat("yyyy-MM-dd").parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            try {
                date=new SimpleDateFormat("dd-MM-yyyy").parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;

    }

    private String normalizeString(String str){
        return str.toLowerCase().substring(0, 1).toUpperCase() + str.toLowerCase().substring(1);
    }
}
