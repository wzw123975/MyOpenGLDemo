package com.wzw.myopengldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wzw.myopengldemo.one.renderer.OneRenderer;
import com.wzw.myopengldemo.one.view.OneSurfaceView;

public class MainActivity extends AppCompatActivity {

    private OneSurfaceView gl_surface_View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setData();
    }

    private void initView() {
        gl_surface_View = findViewById(R.id.gl_surface_View);
    }

    private void setData() {
        OneRenderer oneRenderer = new OneRenderer();
        gl_surface_View.setMyRender(oneRenderer);
    }

}