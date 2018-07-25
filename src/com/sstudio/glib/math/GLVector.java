package com.sstudio.glib.math;

public class GLVector {
    
    public volatile float x, y, z;
    
    public GLVector() {
        set(0, 0, 0);
    }
    
    public GLVector(float x, float y, float z) {
        set(x, y, z);
    }
    
    public GLVector(float x, float y) {
        set(x, y, 0);
    }
    
    public GLVector(GLVector other) {
        set(other.x, other.y, other.z);
    }
    
    public GLVector set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
    
    public GLVector set(float x, float y) {
        return set(x, y, 0);
    }

    @Override
    public String toString() {
        return String.format("(x=%s, y=%s, z=%s)", Float.toString(x), Float.toString(y), Float.toString(z));
    }
    
}
