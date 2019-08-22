package com.study.business.network;

import com.study.business.module.home.BaseHomeModel;
import com.study.projectsdk.okhttp.CommonOkHttpClient;
import com.study.projectsdk.okhttp.listener.DisposeDataHandle;
import com.study.projectsdk.okhttp.listener.DisposeDataListener;
import com.study.projectsdk.okhttp.request.CommonRequest;
import com.study.projectsdk.okhttp.request.RequestParams;

/**
 * created by lxw on 2019/8/21
 * @function 请求类 外层封装
 */
public class RequestCenter {

    //根据参数发送所有的get请求
    public static void getRequest(String url, RequestParams params,DisposeDataListener listener,Class<?> clazz){
        CommonOkHttpClient.sendHttp(CommonRequest.createGetRequest(url,params),new DisposeDataHandle(listener,clazz));
    }
    //根据参数发送所有的get请求
    public static void postRequest(String url, RequestParams params,DisposeDataListener listener,Class<?> clazz){
        CommonOkHttpClient.sendHttp(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(listener,clazz));
    }

    /**
     * 发送首页数据请求
     * @param listener
     */
    public static void requestHomeData(DisposeDataListener listener){
        getRequest(HttpConstanst.HOME_DATA,null,listener, BaseHomeModel.class);
    }

}
