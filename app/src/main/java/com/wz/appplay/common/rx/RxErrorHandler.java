package com.wz.appplay.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.wz.appplay.common.exception.ApiException;
import com.wz.appplay.common.exception.BaseException;
import com.wz.appplay.common.exception.ErrorMessageFactory;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * Created by wz on 17-5-15.
 */

public class RxErrorHandler {

    private  Context mContext;

    public RxErrorHandler(Context context) {
        mContext = context;
    }

    public BaseException handlerError(Throwable t){
        BaseException exception = new BaseException();
        if (t instanceof ApiException){
            exception.setCode(((ApiException)t).getCode());
        } else if (t instanceof SocketException){
            exception.setCode(BaseException.SOCKET_ERROR);
        } else if (t instanceof HttpException){
            exception.setCode(BaseException.HTTP_ERROR);
        } else if (t instanceof SocketTimeoutException){
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        } else {
            exception.setCode(BaseException.UNKNOWN_ERROR);
        }

        exception.setDisplayMessage(ErrorMessageFactory.create(mContext,exception.getCode()));
        return exception;
    }
    public void showErrorMessage(BaseException e){
        Toast.makeText(mContext, e.getDisplayMessage(), Toast.LENGTH_SHORT).show();
    }
}
