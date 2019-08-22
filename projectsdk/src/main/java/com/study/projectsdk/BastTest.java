package com.study.projectsdk;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.nostra13.universalimageloader.core.ImageLoader;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @function created by lxw on 2019/8/16
 */
public class BastTest extends AppCompatActivity {
    String TAG=getComponentName().getShortClassName();
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        ImageLoader imageLoader=ImageLoader.getInstance();
//        imageLoader.displayImage();

    }
}
