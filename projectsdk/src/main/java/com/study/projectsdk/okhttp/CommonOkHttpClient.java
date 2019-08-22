package com.study.projectsdk.okhttp;

import com.study.projectsdk.okhttp.https.HttpsUtils;
import com.study.projectsdk.okhttp.listener.DisposeDataHandle;
import com.study.projectsdk.okhttp.response.CommonJsonCallBack;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * created by lxw on 2019/8/19
 *
 * @function 请求的发送，请求参数的配置，https支持
 */
public class CommonOkHttpClient {

    private static final int TIME_OUT=30;//超时参数
    private static OkHttpClient mOkHttpClient;

    //为我们的client去配置参数
    static {
        //创建我们client对象的构建者
        OkHttpClient.Builder okHttpBuilder=new OkHttpClient.Builder();
        //为构建者填充超时时间
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);

        okHttpBuilder.followRedirects(true);
        //https支持
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        okHttpBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
        //生成我们的client对象
        mOkHttpClient=okHttpBuilder.build();
    }


    public static Call sendHttp(Request request, DisposeDataHandle handle){
        Call call=mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallBack(handle));
        return call;
    }

}
