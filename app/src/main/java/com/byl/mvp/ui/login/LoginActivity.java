package com.byl.mvp.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.byl.mvp.databinding.ActivityLoginBinding;
import com.byl.mvp.ui.MainActivity;
import com.byl.mvp.ui.base.BaseActivity;
import com.byl.mvp.ui.base.InjectPresenter;
import com.byl.mvp.ui.base.event.EventMsg;
import com.byl.mvp.ui.base.event.MsgCode;
import com.byl.mvp.ui.login.model.UserModel;
import com.byl.mvp.ui.login.mvpview.LoginMvpView;
import com.byl.mvp.ui.login.prestener.LoginPresenter;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

/**
 * @Title :
 * @Author : BaiYuliang
 * @Date :
 * @Desc :
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements LoginMvpView {

    @InjectPresenter
    LoginPresenter loginPresenter;

    @Override
    public void initView() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void initClick() {
        RxView.clicks(vb.btnLogin).throttleFirst(1, TimeUnit.SECONDS).subscribe(v -> {
            if (!TextUtils.isEmpty(vb.etAccount.getText().toString()) && !TextUtils.isEmpty(vb.etPwd.getText().toString())) {
                showLoading();
                loginPresenter.login(vb.etAccount.getText().toString(), vb.etPwd.getText().toString());
            } else {
                Toast.makeText(mContext, "请输入账号和密码", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void loginSuccess(UserModel userModel) {
        dismissLoading();
        startActivity(new Intent(mContext, MainActivity.class));
    }

    @Override
    public void loginFail(int error_code, String error_msg) {
        dismissLoading();
        startActivity(new Intent(mContext, MainActivity.class));//请求肯定是失败的，这里只做演示
    }

    /**
     * 接收消息
     *
     * @param eventMsg
     */
    @Override
    public void handleEventMsg(EventMsg eventMsg) {
        super.handleEventMsg(eventMsg);
        if (eventMsg.code == MsgCode.LOGIN_SUCCESS) {
            Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}