package com.sstudio.glib.graphic;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
import java.util.List;
import android.opengl.Matrix;
import com.sstudio.glib.math.Orientation;


public class ChainObject implements GLObject {
    
    protected List<GLObject> objectList;
    protected Orientation orientation;
    protected Orientation initialOrientation;
    private String id;
    
    public ChainObject(Orientation orientation) {
        objectList = new ArrayList<>();
        initialOrientation = new Orientation(orientation);
        this.orientation = orientation;
    }
    
    @Override
    public void init() {
        orientation.set(initialOrientation);
    }

    @Override
    public void update(float deltaTime) {
        for (GLObject object : objectList) {
            object.update(deltaTime);
        }
    }

    @Override
    public void draw(GL10 gl) {
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glMultMatrixf(orientation.getMatrix(), 0);
        for (GLObject object : objectList) {
            object.draw(gl);
        }
        gl.glPopMatrix();
    }

    @Override
    public void dispose() {
        for (GLObject object : objectList) {
            object.dispose();
        }
    }
    
    @Override
    public String getId() {
        return id;
    }
    
    public Orientation getOrientation() {
        return orientation;
    }
    
    
    public static class Builder {
        
        private List<GLObject> objectList;
        
        public Builder() {
            objectList = new ArrayList<>();
        }
        
        public Builder addObject(GLObject object) {
            objectList.add(object);
            return this;
        }
        
        public ChainObject create(String id, Orientation orientation) {
            ChainObject result = new ChainObject(orientation);
            result.id = id;
            result.objectList = objectList;
            return result;
        }
        
    }
    
}
