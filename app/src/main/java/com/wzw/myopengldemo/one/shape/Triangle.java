package com.wzw.myopengldemo.one.shape;

import android.opengl.GLES20;

import com.wzw.myopengldemo.one.utils.Utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

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
                    "  gl_FragColor = vColor;" +
                    "}";


//    private final String vertexShaderCode =
//            // This matrix member variable provides a hook to manipulate
//            // the coordinates of the objects that use this vertex shader
//            "uniform mat4 uMVPMatrix;" +
//                    "attribute vec4 vPosition;" +
//                    "void main() {" +
//                    // the matrix must be included as a modifier of gl_Position
//                    // Note that the uMVPMatrix factor *must be first* in order
//                    // for the matrix multiplication product to be correct.
//                    "  gl_Position = uMVPMatrix * vPosition;" +
//                    "}";


    private int mMVPMatrixHandle;// Use to access and set the view transformation

    private FloatBuffer vertexBuffer;

    static final int COORDS_PER_VERTEX = 3;// number of coordinates per vertex in this array

    static float triangleCoords[] = {   // in counterclockwise order:
            0.0f, 0.5f, 0.0f, // top
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f  // bottom right
    };
    // Set color with red, green, blue and alpha (opacity) values
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
        int vertexShader = Utils.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = Utils.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
        mProgram = GLES20.glCreateProgram(); // 创建空的OpenGL ES程序
        GLES20.glAttachShader(mProgram, vertexShader); // 添加顶点着色器到程序中
        GLES20.glAttachShader(mProgram, fragmentShader); // 添加片段着色器到程序中
        GLES20.glLinkProgram(mProgram); // 创建OpenGL ES程序可执行文件
    }

    public void draw() {
        GLES20.glUseProgram(mProgram);// 将程序添加到OpenGL ES环境
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");// 获取顶点着色器的位置的句柄
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor"); // 获取片段着色器的颜色的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);// 启用三角形顶点位置的句柄

        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);//准备三角形坐标数据

        GLES20.glUniform4fv(mColorHandle, 1, color, 0); // 设置绘制三角形的颜色

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);// 绘制三角形
        GLES20.glDisableVertexAttribArray(mPositionHandle);// 禁用顶点数组


//        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");// 得到形状的变换矩阵的句柄
//        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);  // 将投影和视图转换传递给着色器
//        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);  // 画三角形
//        GLES20.glDisableVertexAttribArray(mPositionHandle); // 禁用顶点数组
    }
}
