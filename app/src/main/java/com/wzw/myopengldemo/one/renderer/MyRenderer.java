package com.wzw.myopengldemo.one.renderer;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer implements GLSurfaceView.Renderer {
    //顶点数组
    private float[] mArray = { 0f, 0f, 0f ,0.5f, 0.5f, 0.5f};
    // 缓冲区
    private FloatBuffer mBuffer;
    public MyRenderer() {
        //获取浮点形缓冲数据
//        mBuffer = Utils.getFloatBuffer(mArray);
        ByteBuffer bb = ByteBuffer.allocateDirect(mArray.length * 4); // 初始化ByteBuffer，长度为arr数组的长度*4，因为一个float占4个字节
        bb.order(ByteOrder.nativeOrder()); // 数组排列用nativeOrder
        mBuffer = bb.asFloatBuffer(); // 从ByteBuffer创建一个浮点缓冲区
        mBuffer.put(mArray);  // 将坐标添加到FloatBuffer
        mBuffer.position(0);// 设置缓冲区来读取第一个坐标
    }
    // Surface创建的时候调用
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // 设置清屏颜色为黑色
        gl.glClearColor(0f, 0f, 0f, 0f);
    }
    // Surface改变的的时候调用
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 设置窗口大小
        gl.glViewport(width / 4, width / 2, width / 2, height / 2);
    }
    // 在Surface上绘制的时候调用
    @Override
    public void onDrawFrame(GL10 gl) {
        // 清除屏幕
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        // 允许设置顶点 // GL10.GL_VERTEX_ARRAY顶点数组
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // 设置顶点
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mBuffer);
        //设置点的颜色为绿色
        gl.glColor4f(0f, 1f, 0f, 0f);
        //设置点的大小
//        gl.glPointSize(80f);
        gl.glLineWidth(10);
        // 绘制点
        gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 2);
        // 禁止顶点设置
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}