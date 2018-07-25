package com.sstudio.glib.graphic;

import javax.microedition.khronos.opengles.GL10;


public interface GLObject {
    
    public void init();
    public void update(float deltaTime);
    public void draw(GL10 gl);
    public void dispose();
    public String getId();
    
}
