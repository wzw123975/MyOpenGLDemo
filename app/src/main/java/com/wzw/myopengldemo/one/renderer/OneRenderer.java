package com.wzw.myopengldemo.one.renderer;


import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import com.wzw.myopengldemo.one.shape.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OneRenderer implements GLSurfaceView.Renderer {

    Triangle mTriangle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {//21 210 106
        GLES30.glClearColor(0.182f, 0.8235f, 0.4156f, 1.0f); // Set the background frame color
        mTriangle = new Triangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES30.glClear(GL10.GL_COLOR_BUFFER_BIT);
        mTriangle.draw(gl);

    }
}
