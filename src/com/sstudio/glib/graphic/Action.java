package com.sstudio.glib.graphic;

import android.opengl.Matrix;


public class Action {
    
    protected static final int MAX_INDEX = 3;
    protected boolean done = false;
    public enum Order { TRS, TSR, RTS, RST, SRT, STR };
    protected int matrixIndex = 0;
    protected MatrixTyped translateM;
    protected MatrixTyped rotateM;
    protected MatrixTyped scaleM;
    protected MatrixTyped identityM;
    protected MatrixTyped[] matrixSequence;
    protected int translateCount = 0;
    protected int rotateCount = 0;
    protected int scaleCount = 0;
    
    
    public Action(Order matrixOrder) {
        translateM = new MatrixTyped(MatrixTyped.Type.TRANSLATE);
        rotateM = new MatrixTyped(MatrixTyped.Type.ROTATE);
        scaleM = new MatrixTyped(MatrixTyped.Type.SCALE);
        identityM = new MatrixTyped(MatrixTyped.Type.IDENTITY);
        Matrix.setIdentityM(translateM.matrix, 0);
        Matrix.setIdentityM(rotateM.matrix, 0);
        Matrix.setIdentityM(scaleM.matrix, 0);
        Matrix.setIdentityM(identityM.matrix, 0);
        setupOrder(matrixOrder);
    }
    
    private void setupOrder(Order order) {
        switch (order) {
            case TRS: matrixSequence = new MatrixTyped[]{translateM, rotateM, scaleM};
                      break;
            case TSR: matrixSequence = new MatrixTyped[]{translateM, scaleM, rotateM};
                      break;
            case RTS: matrixSequence = new MatrixTyped[]{rotateM, translateM, scaleM};
                      break;
            case RST: matrixSequence = new MatrixTyped[]{rotateM, scaleM, translateM};
                      break;
            case SRT: matrixSequence = new MatrixTyped[]{scaleM, rotateM, translateM};
                      break;
            case STR: matrixSequence = new MatrixTyped[]{scaleM, translateM, rotateM};
                      break;
            default:  matrixSequence = new MatrixTyped[]{translateM, scaleM, rotateM};
        }
    }
    
    public boolean isDone() {
        return done;
    }
    
    public void reset() {
        done = false;
    }
    
    public void update() {
        if ((translateCount == 0) &&
            (rotateCount == 0) &&
            (scaleCount == 0)) {
          done = true;
        }
    }
    
    public MatrixTyped nextMatrix() {
        if (matrixIndex == MAX_INDEX) {
            return null;
        }
        if ((translateCount == 0) &&
            (rotateCount == 0) &&
            (scaleCount == 0)) {
          done = true;
        }
        MatrixTyped currentMatrix = matrixSequence[matrixIndex];
        matrixIndex++;
        switch (currentMatrix.type) {
            case TRANSLATE: 
                if (translateCount == 0) return identityM;
                else translateCount--;
                break;
            case ROTATE:
                if (rotateCount == 0) return identityM;
                else rotateCount--;
                break;
            case SCALE:
                if (scaleCount == 0) return identityM;
                else scaleCount--;
                break;
        }
        return currentMatrix;
    }
    
    public void begin() {
        matrixIndex = 0;
    }
    
    public void end() {}
    
    public Action setTranslate(float x, float y, float z, int times) {
        Matrix.translateM(translateM.matrix, 0, x, y, z);
        translateCount = times;
        return this;
    }
    
    public Action setRotate(float degree, float x, float y, float z, int times) {
        Matrix.rotateM(rotateM.matrix, 0, degree, x, y, z);
        rotateCount = times;
        return this;
    }
    
    public Action setScale(float x, float y, float z,int times) {
        Matrix.scaleM(scaleM.matrix, 0, x, y, z);
        scaleCount = times;
        return this;
    }
    
    
    public static class MatrixTyped {
        enum Type {TRANSLATE, ROTATE, SCALE, IDENTITY}
        Type type;
        float[] matrix = new float[16];
        
        MatrixTyped(Type type) {
            this.type = type;
        }
    }
    
}
