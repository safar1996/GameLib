package com.sstudio.glib.math;

import android.opengl.Matrix;


public class Orientation {
    
    protected final float[] translateMatrix = new float[16];
    protected final float[] scaleMatrix = new float[16];
    protected final float[] rotateMatrix = new float[16];
    protected final float[] resultMatrix = new float[16];
    protected boolean isChanged = true;
    
    public Orientation() {
        reset();
    }
    
    public Orientation(Orientation other) {
        set(other);
    }
    
    public Orientation reset() {
        Matrix.setIdentityM(translateMatrix, 0);
        Matrix.setIdentityM(scaleMatrix, 0);
        Matrix.setIdentityM(rotateMatrix, 0);
        Matrix.setIdentityM(resultMatrix, 0);
        return this;
    }
    
    public Orientation translate(float x, float y, float z) {
        Matrix.translateM(translateMatrix, 0, x, y, z);
        isChanged = true;
        return this;
    }
    
    public Orientation scale(float x, float y, float z) {
        Matrix.scaleM(scaleMatrix, 0, x, y, z);
        isChanged = true;
        return this;
    }
    
    public Orientation rotate(float degree, float x, float y, float z) {
        Matrix.rotateM(rotateMatrix, 0, degree, x, y, z);
        isChanged = true;
        return this;
    }
    
    public float[] getTranslateM() {
        return translateMatrix;
    }
    
    public float[] getRotateM() {
        return rotateMatrix;
    }
    
    public float[] getScaleM() {
        return scaleMatrix;
    }
    
    public Orientation set(Orientation other) {
        Matrix.setIdentityM(translateMatrix, 0);
        Matrix.setIdentityM(rotateMatrix, 0);
        Matrix.setIdentityM(scaleMatrix, 0);
        multiply(other);
        isChanged = true;
        return this;
    }
    
    public Orientation multiply(Orientation other) {
        Matrix.multiplyMM(translateMatrix, 0, translateMatrix, 0, other.getTranslateM(), 0);
        Matrix.multiplyMM(rotateMatrix, 0, rotateMatrix, 0, other.getRotateM(), 0);
        Matrix.multiplyMM(scaleMatrix, 0, scaleMatrix, 0, other.getScaleM(), 0);
        isChanged = true;
        return this;
    }
    
    public void updateMatrix() {
        Matrix.setIdentityM(resultMatrix, 0);
        Matrix.multiplyMM(resultMatrix, 0, resultMatrix, 0, translateMatrix, 0);
        Matrix.multiplyMM(resultMatrix, 0, resultMatrix, 0, rotateMatrix, 0);
        Matrix.multiplyMM(resultMatrix, 0, resultMatrix, 0, scaleMatrix, 0);
    }
    
    public float[] getMatrix() {
        if (isChanged) {
          updateMatrix();
          isChanged = false;
        }
        return resultMatrix;
    }
    
}
