package com.example.homeworkemployees.screens.employees_of_speciality;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.homeworkemployees.api.ApiFactory;
import com.example.homeworkemployees.api.ApiService;
import com.example.homeworkemployees.data.AppDatabase;
import com.example.homeworkemployees.pojo.Employee;
import com.example.homeworkemployees.pojo.MyResponse;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeesViewModel extends AndroidViewModel {
    private LiveData<List<Employee>> employees;
    private MutableLiveData<Throwable> errors;
    private static AppDatabase database;
    private CompositeDisposable compositeDisposable;

    public EmployeesViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
        employees = database.employeeDao().getAllEmployees();
        errors = new MutableLiveData();
        compositeDisposable = new CompositeDisposable();
    }

    public LiveData<List<Employee>> getEmployees() {
        return employees;
    }

    public LiveData<Throwable> getErrors() {
        return errors;
    }

    public void insertEmployees(List<Employee> employees){
        new InsertEmployeeTask().execute(employees);
    }

    public void deleteAllEmployees(){
        new DeleteAllEmployeesTask().execute();
    }

    public void loadData(){
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();

        Disposable disposable = apiService.getMyResponse().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyResponse>() {
                    @Override
                    public void accept(MyResponse myResponse) throws Exception {
                        deleteAllEmployees();
                        insertEmployees(myResponse.getResponse());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        errors.setValue(throwable);
                    }
                });
        compositeDisposable.add(disposable);
    }

    private static class InsertEmployeeTask extends AsyncTask<List<Employee>,Void,Void>{
        @Override
        protected Void doInBackground(List<Employee>... lists) {
            if(lists!=null && lists.length>0){
                database.employeeDao().insertEmployees(lists[0]);
            }
            return null;
        }
    }

    private static class DeleteAllEmployeesTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            database.employeeDao().deleteAllEmployees();
            return null;
        }
    }

    @Override
    protected void onCleared() {
        if(compositeDisposable!=null){
            compositeDisposable.dispose();
        }
        super.onCleared();
    }
}
