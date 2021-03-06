package com.byl.mvp.api.subscribe;

import com.byl.mvp.api.net.ApiUtil;
import com.byl.mvp.api.net.HttpResult;

import java.util.LinkedHashMap;

import io.reactivex.Observable;

/**
 * @Title : 对应接口模块
 * @Author : BaiYuliang
 * @Date :
 * @Desc :
 */
public class AccountSubscribe extends BaseSubscribe {

    private static class SingletonHolder {
        public static AccountSubscribe instance = new AccountSubscribe();
    }

    public static AccountSubscribe newInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    public Observable<HttpResult> login(String userName, String password) {
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("userName", userName);
        params.put("password", password);
        return ApiUtil.getInstance()
                .getApiService()
                .login(appendParam(params))
                .compose(compose());
    }


}
