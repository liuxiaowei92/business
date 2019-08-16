package com.study.business.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

/**
 * created by Administrator on 2019/8/15
 */
public abstract class BaseActivity extends AppCompatActivity {

    public String TAG;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG=getComponentName().getShortClassName();

        //找到注解的类
        ViewInject annotation=this.getClass().getAnnotation(ViewInject.class);
        if(annotation!=null){
            int mainLayoutId=annotation.mainLayoutId();
            if(mainLayoutId>0){
                setContentView(mainLayoutId);
                ButterKnife.bind(this);//初始化
                afterBindView();
            }else{
                throw new RuntimeException("mainLayoutId<0");
            }
        }else{
            throw new RuntimeException("annotation==null");
        }


    }

    //模板方法 设计模式
    public abstract void afterBindView() ;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
