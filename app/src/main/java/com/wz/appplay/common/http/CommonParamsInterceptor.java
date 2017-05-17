package com.wz.appplay.common.http;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.wz.appplay.common.Constant;
import com.wz.appplay.common.util.DensityUtil;
import com.wz.appplay.common.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by wz on 17-5-15.
 */

public class CommonParamsInterceptor implements Interceptor {


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private Gson mGson;
    private Context mContext;

    public CommonParamsInterceptor(Context context, Gson gson) {
        mGson = gson;
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        String methon = request.method();

        HashMap<String, Object> commonParamsMap = new HashMap<>();
        //commonParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
        //commonParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
        //commonParamsMap.put(Constant.MODEL, DeviceUtils.getModel());
        commonParamsMap.put(Constant.LANGUAGE, DeviceUtils.getLanguage());
        commonParamsMap.put(Constant.os, DeviceUtils.getBuildVersionIncremental());
        commonParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext) + "*" + DensityUtil.getScreenH(mContext));
        commonParamsMap.put(Constant.SDK, DeviceUtils.getBuildVersionSDK() + "");
     //   commonParamsMap.put(Constant.DENSITY_SCALE_FACTOR, mContext.getResources().getDisplayMetrics().density + "");


        if (methon.equals("GET")) {

            HttpUrl url = request.url();

            HashMap<String, Object> rooMap = new HashMap<>();
            Set<String> paramNames = url.queryParameterNames();

            for (String key : paramNames) {
                if (Constant.PARAM.equals(key)) {

                    String oldParamJson = url.queryParameter(Constant.PARAM);
                    //原始参数

                    if (oldParamJson != null) {
                        HashMap<String, Object> p = mGson.fromJson(oldParamJson, HashMap.class);
                        if (p != null) {
                            for (Map.Entry<String, Object> entry : p.entrySet()) {
                                rooMap.put(entry.getKey(), entry.getValue());
                            }
                        }
                    }

                } else {
                    rooMap.put(key, url.queryParameter(key));
                }
            }

            rooMap.put("publicParams", commonParamsMap);

            String newJsonParams = mGson.toJson(rooMap);

            String urlString = url.toString();

            int index = urlString.indexOf("?");
            if (index > 0) {
                urlString = urlString.substring(0, index);
            }
            urlString = urlString + "?" + Constant.PARAM +"="+ newJsonParams;


            request = request.newBuilder().url(urlString).build();

        } else if (methon.equals("POST")) {
            RequestBody body = request.body();

            HashMap<String, Object> rooMap = new HashMap<>();
            if (body instanceof FormBody) {
                for (int i = 0; i < ((FormBody) body).size(); i++) {
                    rooMap.put(((FormBody) body).encodedName(i), ((FormBody) body).encodedValue(i));
                }
            } else {
                Buffer buffer = new Buffer();

                body.writeTo(buffer);
                String oldJsonParams = buffer.readUtf8();
                if (!TextUtils.isEmpty(oldJsonParams)){
                    rooMap = mGson.fromJson(oldJsonParams, HashMap.class);
                    if (rooMap!=null){

                        rooMap.put("publicParams", commonParamsMap);
                        String newJsonParams = mGson.toJson(rooMap);
                        request = request.newBuilder().post(RequestBody.create(JSON, newJsonParams)).build();
                    }
                }

            }
        }

        return chain.proceed(request);
    }
}
