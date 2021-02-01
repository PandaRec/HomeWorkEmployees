package com.example.homeworkemployees.api;

import com.example.homeworkemployees.pojo.MyResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("testTask.json")
    Observable<MyResponse> getMyResponse();
}
