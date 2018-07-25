package com.sstudio.glib.graphic;

import android.graphics.Color;


public class GLColor {
    
    protected static final float MAX_VALUE = 1F;
    
    private float r, g, b, a;
    
    public GLColor() {
        set(0, 0, 0, 0);
    }
    
    public GLColor(int value) {
        r = ((float) Color.red(value) / 255F);
        g = ((float) Color.green(value) / 255F);
        b = ((float) Color.blue(value) / 255F);
        a = ((float) Color.alpha(value) / 255F);
    }
    
    public GLColor(float r, float g, float b, float a) {
        set(r, g, b, a);
    }
    
    public GLColor(GLColor other) {
        set(other.getRed(), other.getGreen(), other.getBlue(), other.getAlpha());
    }
    
    protected float ensureValue(float value) {
        return (value > MAX_VALUE) ? MAX_VALUE : value;
    }
    
    public GLColor set(float r, float g, float b, float a) {
        this.r = ensureValue(r);
        this.g = ensureValue(g);
        this.b = ensureValue(b);
        this.a = ensureValue(a);
        return this;
    }
    
    public float getRed() {
        return r;
    }
    
    public float getGreen() {
        return g;
    }
    
    public float getBlue() {
        return b;
    }
    
    public float getAlpha() {
        return a;
    }
    
    public GLColor setRed(float value) {
        r = ensureValue(value);
        return this;
    }
    
    public GLColor setGreen(float value) {
        g = ensureValue(value);
        return this;
    }
    
    public GLColor setBlue(float value) {
        b = ensureValue(value);
        return this;
    }
    
    public GLColor setAlpha(float value) {
        a = ensureValue(value);
        return this;
    }
    
    public GLColor combine(GLColor other) {
        r = ensureValue(r + other.getRed());
        g = ensureValue(g + other.getGreen());
        b = ensureValue(b + other.getBlue());
        a = ensureValue(a + other.getAlpha());
        return this;
    }
}
