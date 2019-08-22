package com.study.projectsdk.okhttp.listener;

/**
 * created by lxw on 2019/8/19
 * @function 自定义事件监听
 */
public interface DisposeDataListener {
    /**
     * 成功回调
     * @param responseObj
     */
    public void onSuccess(Object responseObj);

    /**
     * 失败回调
     * @param reasonObj
     */
    public void onFailure(Object reasonObj);
}
