package com.wzw.myopengldemo.one.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class OneSurfaceView extends GLSurfaceView {

    private Context mContext;

    public OneSurfaceView(Context context) {
        this(context, null);
    }

    public OneSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
    }

    public void setMyRender(GLSurfaceView.Renderer render) {
        setRenderer(render);
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }


}
