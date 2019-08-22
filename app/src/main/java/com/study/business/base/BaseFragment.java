package com.study.business.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

/**
 * created by Administrator on 2019/8/15
 */
public abstract class BaseFragment extends Fragment {
    public String TAG;
    protected Activity mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       TAG=getClass().getSimpleName();
        View view=null;
        //找到注解的类
        ViewInject annotation=this.getClass().getAnnotation(ViewInject.class);
        if(annotation!=null){
            int mainLayoutId=annotation.mainLayoutId();
            if(mainLayoutId>0){
                view=inflater.inflate(mainLayoutId,null);
                ButterKnife.bind(this,view);
                afterBindView();
            }else{
                throw new RuntimeException("mainLayoutId<0");
            }
        }else{
            throw new RuntimeException("annotation==null");
        }


        return view;
    }

    public abstract void afterBindView();
}
