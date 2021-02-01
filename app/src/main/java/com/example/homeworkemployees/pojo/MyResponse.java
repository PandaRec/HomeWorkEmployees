package com.example.homeworkemployees.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyResponse {

    @SerializedName("response")
    @Expose
    private List<Employee> response = null;

    public List<Employee> getResponse() {
        return response;
    }

    public void setResponse(List<Employee> response) {
        this.response = response;
    }
}
