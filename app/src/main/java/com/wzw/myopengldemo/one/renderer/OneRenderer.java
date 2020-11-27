package com.wzw.myopengldemo.one.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;

import com.wzw.myopengldemo.one.shape.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OneRenderer implements GLSurfaceView.Renderer {

    Triangle mTriangle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {//21 210 106
        GLES20.glClearColor(0.082f, 0.8235f, 0.4156f, 1.0f); // Set the background frame color
        // 初始化triangle
        mTriangle = new Triangle();
    }

    // mMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        Log.e("ii--", "5555555555");
        float ratio = (float) width / height;
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7); // 这个投影矩阵被应用于对象坐标在onDrawFrame（）方法中
    }

    private float[] mRotationMatrix = new float[16];

    @Override
    public void onDrawFrame(GL10 gl) {
//        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);// Redraw background color
        mTriangle.draw();
        Log.e("ii--", "666666666666666666667776677");


//        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);  // Set the camera position (View matrix)
//        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);// Calculate the projection and view transformation
//        mTriangle.draw(mMVPMatrix);// Draw shape
//
//
//        float[] scratch = new float[16];
////         创建一个旋转矩阵
//        long time = SystemClock.uptimeMillis() % 4000L;
//        float angle = 0.090f * ((int) time);
//        Matrix.setRotateM(mRotationMatrix, 0, angle, 0, 0, -1.0f);
//
////         将旋转矩阵与投影和相机视图组合在一起
////         Note that the mMVPMatrix factor *must be first* in order
////         for the matrix multiplication product to be correct.
//        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0);
//        mTriangle.draw(scratch);// Draw triangle
    }
}
