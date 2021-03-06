# MVP
MVP+Rxjava+Retrofit+ViewBinding+EventBus

- 1.本框架将ViewBinding进行了封装，使用时只需传入对应ViewBinding，即可通过vb.控件id访问；
- 2.本框架对Presenter进行了封装，使用时只需通过注解的方式@InjectPresenter引入presenter即可，并且不再需要注册和注销操作，支持一对多；
- 3.本框架对EventBus进行了封装，不再需要注册和注销操作；

![效果图](https://img-blog.csdnimg.cn/20200826170818594.gif#pic_center)

以上具体使用方法参考源码或下方案例：

1.Activity：
```java
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
```
2.Fragment同

3.Adapter：
```java
public class ListAdapter extends BaseAdapter<ItemListBinding, String> {

    public ListAdapter(Activity context, List<String> listData) {
        super(context, listData);
    }

    @Override
    public void convert(BaseViewHolder holder, String t, int position) {
        ItemListBinding vb = (ItemListBinding) holder.vb;
        vb.tvContent.setText(t);
    }

}
```
4.发送/接收消息：
```java
 App.post(new EventMsg(MsgCode.LOGIN_SUCCESS));
 
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
```


