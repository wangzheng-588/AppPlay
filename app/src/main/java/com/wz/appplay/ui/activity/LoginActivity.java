package com.wz.appplay.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.wz.appplay.R;
import com.wz.appplay.di.component.AppComponent;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Created by wz on 17-5-15.
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.txt_mobi)
    EditText mTxtMobi;
    @BindView(R.id.view_mobi_wrapper)
    TextInputLayout mViewMobiWrapper;
    @BindView(R.id.txt_password)
    EditText mTxtPassword;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout mViewPasswordWrapper;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        initView();
    }

    private void initView() {
        Observable obMobi = RxTextView.textChanges(mTxtMobi);
        Observable obPassword =RxTextView.textChanges(mTxtPassword);

       Observable.combineLatest(obMobi, obPassword, new BiFunction<CharSequence, CharSequence, Boolean>() {
           @Override
           public Boolean apply(CharSequence mobi, CharSequence passwd) throws Exception {
               return isPhoneValid(mobi.toString())&&isPasswordValid(passwd.toString());
           }
       }).subscribe(new Consumer<Boolean>() {
           @Override
           public void accept(Boolean aBoolean) throws Exception {
               RxView.enabled(mBtnLogin).accept(aBoolean);
           }
       });

        RxView.clicks(mBtnLogin).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                String user = mTxtMobi.getText().toString().trim();
                String passwd = mTxtPassword.getText().toString().trim();

                if (user.equals("12345678900")
                        &&passwd.equals("123456")){
                    Toast.makeText(LoginActivity.this, "成功", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoginActivity.this, "失败", Toast.LENGTH_SHORT).show();

                }
            }
        });




    }

    private boolean isPhoneValid(String phone){
        return phone.length() == 11;
    }

    private boolean isPasswordValid (String password){
        return password.length() >= 6;
    }

}
