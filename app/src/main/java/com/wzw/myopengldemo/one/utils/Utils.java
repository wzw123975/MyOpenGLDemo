package com.wzw.myopengldemo.one.utils;

import android.opengl.GLES30;

public class Utils {
    public static int loadShader(int type, String shaderCode) {

        // 创造顶点着色器类型(GLES30.GL_VERTEX_SHADER)
        // 或者是片段着色器类型 (GLES30.GL_FRAGMENT_SHADER)
        int shader = GLES30.glCreateShader(type);
        // 添加上面编写的着色器代码并编译它
        GLES30.glShaderSource(shader, shaderCode);
        GLES30.glCompileShader(shader);
        return shader;
    }
}
