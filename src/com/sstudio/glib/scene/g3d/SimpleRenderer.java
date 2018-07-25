package com.sstudio.glib.scene.g3d;

import java.util.ArrayList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import com.sstudio.glib.graphic.GLObject;
import android.opengl.GLSurfaceView;
import java.util.List;


public class SimpleRenderer implements GLSurfaceView.Renderer {
    
    protected List<GLObject> objectList = new ArrayList<>();
    
    public SimpleRenderer() {
        
    }
    
    public void addObject(GLObject object) {
        objectList.add(object);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glViewport(0, 0, width, height);
        final float ratio = (width > height) ? ((float)width / (float)height) : ((float)height / (float)width);
        if (width < height) {
            gl.glOrthof(-1, 1, -ratio, ratio, 0, 10);
        } else {
            gl.glOrthof(-ratio, ratio, -1, 1, 0, 10);
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        for (GLObject object : objectList) {
            object.update(0);
            object.draw(gl);
        }
    }
    
}
