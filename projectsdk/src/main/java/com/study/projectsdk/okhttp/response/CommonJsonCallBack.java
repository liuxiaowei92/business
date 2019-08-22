package com.study.projectsdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.study.projectsdk.okhttp.exception.OkHttpException;
import com.study.projectsdk.okhttp.listener.DisposeDataHandle;
import com.study.projectsdk.okhttp.listener.DisposeDataListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 专门处理JSON的回调响应
 * created by lxw on 2019/8/19
 * @function
 */
public class CommonJsonCallBack implements Callback {

    //与服务器返回的字段的一个对应关系
    protected final String RESULT_CODE="ecode";
    protected final int RESULT_CODE_VALUE=0;
    protected final String ERROR_MSG="emsg";
    protected final String EMPTY_MSG="";

    /**
     * 自定义异常类型
     */
    protected final int NETWORK_ERROR=-1;
    protected final int JSON_ERROR=-2;
    protected final int OTHER_ERROR=-3;

    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallBack(DisposeDataHandle handle){
        this.mListener=handle.mListener;
        this.mClass=handle.mClass;
        this.mDeliveryHandler=new Handler(Looper.getMainLooper());
    }


    /**
     * 请求失败处理
     * @param call
     * @param e
     */
    @Override
    public void onFailure(@NotNull Call call, @NotNull final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR,e));
            }
        });
    }

    //响应处理函数
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        final String result=response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);     
            }
        });
        
        
    }

    /**
     * 处理服务器返回的响应数据
     * @param responseObj
     */
    private void handleResponse(Object responseObj) {
        if(responseObj==null && responseObj.toString().trim().equals("")){
            mListener.onFailure(new OkHttpException(NETWORK_ERROR,EMPTY_MSG));
            return;
        }
        try{
            JSONObject result=new JSONObject(responseObj.toString());
            if(result.has(RESULT_CODE)){
                //从json对象中取出我们的响应码，为0 则是正确的响应
                if(result.getInt(RESULT_CODE)==RESULT_CODE_VALUE){
                    if(mClass==null){
                        mListener.onSuccess(responseObj);
                    }else{
                        //即，需要我们将接送对象转实体对象
                        Object obj= new Gson().fromJson(result.toString(),mClass);
                        //表明正确转为实体
                        if(obj!=null){
                            mListener.onSuccess(obj);
                        }else{
                            mListener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
                        }
                    }
                }else{
                    mListener.onFailure(new OkHttpException(OTHER_ERROR,result.get(RESULT_CODE)));
                }
            }
        }catch (Exception e){
            mListener.onFailure(new OkHttpException(OTHER_ERROR,e.getMessage()));
        }



    }
}
