package com.study.business.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.study.business.R;
import com.study.business.adapter.CourseAdapter;
import com.study.business.base.BaseFragment;
import com.study.business.base.ViewInject;
import com.study.business.module.home.BaseHomeModel;
import com.study.business.network.RequestCenter;
import com.study.business.view.HomeHeaderLayout;
import com.study.projectsdk.okhttp.listener.DisposeDataListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * created by Administrator on 2019/8/15
 */
@ViewInject(mainLayoutId = R.layout.fragment_home)
public class HomeFragmet extends BaseFragment {

    @BindView(R.id.tv_qrcode)
    TextView tvQrcode;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.tv_home_search)
    TextView tvHomeSearch;
    @BindView(R.id.iv_home_loading)
    ImageView ivHomeLoading;
    @BindView(R.id.list)
    ListView list;
    private AnimationDrawable mAnimationDrawable;
    private BaseHomeModel mHomeData;

    @Override
    public void afterBindView() {
        mContext=getActivity();
        mAnimationDrawable = (AnimationDrawable) ivHomeLoading.getDrawable();
        mAnimationDrawable.start();
        requestHomeData();
    }

    /**
     * 请求首页列表数据
     */
    private void requestHomeData() {

        RequestCenter.requestHomeData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.i(TAG, "onSuccess: ");
                /**
                 * 获取到数据后更新UI
                 */
                mHomeData = (BaseHomeModel) responseObj;
                showSuccessView();
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.i(TAG, "onFailure: ");
            }
        });


    }

    /**
     * 请求成功的执行方法
     */
    private void showSuccessView() {
        if(mHomeData.data.list!=null && mHomeData.data.list.size()>0){
            ivHomeLoading.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
            //为listview添加列表头
            list.addHeaderView(new HomeHeaderLayout(mContext,mHomeData.data.head));
            //创建adapter
            CourseAdapter adapter=new CourseAdapter(mContext,mHomeData.data.list);
            list.setAdapter(adapter);
        }else{
            showErrorView();
        }
    }

    /**
     * 请求失败后执行的方法
     */
    private void showErrorView() {

    }

    @OnClick({R.id.tv_qrcode, R.id.tv_category, R.id.tv_home_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_qrcode:
                break;
            case R.id.tv_category:
                break;
            case R.id.tv_home_search:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
