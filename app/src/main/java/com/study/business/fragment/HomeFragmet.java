package com.study.business.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.study.business.R;
import com.study.business.base.BaseFragment;
import com.study.business.base.ViewInject;

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

    @Override
    public void afterBindView() {
        mAnimationDrawable = (AnimationDrawable) ivHomeLoading.getDrawable();
        mAnimationDrawable.start();
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
