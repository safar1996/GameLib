package com.sstudio.glib.graphic;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import com.sstudio.glib.math.GLVector;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.Matrix;
import com.sstudio.glib.math.Orientation;


public class SimpleObject implements GLObject {
    
    public static final String TAG = SimpleObject.class.getName();
    protected Orientation orientation;
    protected Orientation initialOrientation;
    private String id;
    protected FloatBuffer vbb;
    protected GLColor color;
    protected int drawMode;
    private int verticeCount;
    private int axisCount;
    
    public SimpleObject(Orientation orientation) {
        initialOrientation = new Orientation(orientation);
        this.orientation = orientation;
    }
    
    @Override
    public void init() {
       orientation.set(initialOrientation);
    }
    
    protected void setupBuffer(float[] vertices) {
        vbb = (FloatBuffer) ByteBuffer.allocateDirect(vertices.length * Float.SIZE)
              .order(ByteOrder.nativeOrder())
              .asFloatBuffer()
              .put(vertices)
              .position(0);
    }
    
    @Override
    public void update(float deltaTime) {
       // orientation.rotate(20F, 0, 0, 1);
    }
    
    @Override
    public void draw(GL10 gl) {
        gl.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glMultMatrixf(orientation.getMatrix(), 0);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(axisCount, GL10.GL_FLOAT, 0, vbb);
        gl.glDrawArrays(drawMode, 0, verticeCount);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glPopMatrix();
    }
    
    @Override
    public void dispose() {
        vbb.clear();
    }
    
    @Override
    public String getId() {
        return id;
    }
    
    
    public static class Factory {
        
        public static SimpleObject create(String id, float[] vertices,  int drawMode, int count, GLColor color, Orientation orientation) {
            SimpleObject result = new SimpleObject(orientation);
            result.id = id;
            result.color = color;
            result.drawMode = drawMode;
            result.init();
            result.setupBuffer(vertices);
            result.verticeCount = vertices.length / count;
            result.axisCount = count;
            return result;
        }
        
    }
    
}
