package com.byl.mvp.api;

import com.byl.mvp.api.net.HttpResult;
import com.byl.mvp.ui.login.model.UserModel;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    //登录
    @FormUrlEncoded
    @POST("Account/Login")
    Observable<HttpResult<UserModel>> login(@FieldMap HashMap<String, String> options);
}
