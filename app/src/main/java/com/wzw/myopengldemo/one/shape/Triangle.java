package com.wzw.myopengldemo.one.shape;

import android.opengl.GLES30;
import android.opengl.GLES30;

import com.wzw.myopengldemo.one.utils.Utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangle {

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
//                    "  gl_FragColor = vColor;" +
                    "gl_FragColor = vec4(1.0,1.0,1.0,1.0);" +
                    "}";

    private FloatBuffer vertexBuffer;

    static final int COORDS_PER_VERTEX = 3;// number of coordinates per vertex in this array

    static float triangleCoords[] = {   // in counterclockwise order:
            0.0f, 0.5f, 0.0f, // top
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f  // bottom right
    };
    //     Set color with red, green, blue and alpha (opacity) values
    float color[] = {1, 1, 0, 1.0f};

    int mProgram;


    private int mPositionHandle;
    private int mColorHandle;

    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    public Triangle() {
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4); // 初始化ByteBuffer，长度为arr数组的长度*4，因为一个float占4个字节
        bb.order(ByteOrder.nativeOrder()); // 数组排列用nativeOrder
        vertexBuffer = bb.asFloatBuffer(); // 从ByteBuffer创建一个浮点缓冲区
        vertexBuffer.put(triangleCoords);  // 将坐标添加到FloatBuffer
        vertexBuffer.position(0);// 设置缓冲区来读取第一个坐标

        //数据转换
        int vertexShader = Utils.loadShader(GLES30.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = Utils.loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentShaderCode);
        mProgram = GLES30.glCreateProgram(); // 创建空的OpenGL ES程序
        GLES30.glAttachShader(mProgram, vertexShader); // 添加顶点着色器到程序中
        GLES30.glAttachShader(mProgram, fragmentShader); // 添加片段着色器到程序中
        GLES30.glLinkProgram(mProgram); // 创建OpenGL ES程序可执行文件
    }


    public void draw(GL10 gl) {

        GLES30.glUseProgram(mProgram);// 将程序添加到OpenGL ES环境
        GLES30.glVertexAttribPointer(0, 3, GLES30.GL_FLOAT, false, 0, vertexBuffer); //准备坐标数据
        GLES30.glEnableVertexAttribArray(0);  //启用顶点的句柄

        GLES30.glLineWidth(15);
        GLES30.glDrawArrays(GLES30.GL_LINE_STRIP, 0, 3);


//        gl.glPointSize(10);
//        GLES30.glDrawArrays(GLES30.GL_POINTS, 0, 3); //绘制三个点


        GLES30.glDisableVertexAttribArray(0);//禁止顶点数组的句柄


//
//        GLES30.glUseProgram(mProgram);// 将程序添加到OpenGL ES环境
//        mPositionHandle = GLES30.glGetAttribLocation(mProgram, "vPosition");// 获取顶点着色器的位置的句柄
//        mColorHandle = GLES30.glGetUniformLocation(mProgram, "vColor"); // 获取片段着色器的颜色的句柄
//        GLES30.glEnableVertexAttribArray(mPositionHandle);// 启用三角形顶点位置的句柄
//
//        GLES30.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
//                GLES30.GL_FLOAT, false,
//                vertexStride, vertexBuffer);//准备三角形坐标数据
//
//        GLES30.glUniform4fv(mColorHandle, 1, color, 0); // 设置绘制三角形的颜色
//
//        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, vertexCount);// 绘制三角形
//        GLES30.glDisableVertexAttribArray(mPositionHandle);// 禁用顶点数组

    }
}
