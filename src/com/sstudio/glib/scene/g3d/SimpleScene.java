package com.sstudio.glib.scene.g3d;

import android.opengl.GLSurfaceView;
import android.content.*;
import com.sstudio.glib.graphic.*;
import com.sstudio.glib.math.*;
import javax.microedition.khronos.opengles.*;
import android.graphics.Color;
import android.opengl.*;


public class SimpleScene extends GLSurfaceView {
    
    protected SimpleRenderer renderer;
    
    public SimpleScene(Context context) {
        super(context);
        renderer = new SimpleRenderer();
        setRenderer(renderer);
        
        float[] vertices1 = new float[]{0, 0, 0,
                                       1, 0, 0,
                                       0, 1, 0};
        
        float[] vertices2 = new float[]{0, 0, 0,
                                        -1, 0, 0,
                                        0, 1, 0};
        
        String name1 = "Simple_Object1";
        String name2 = "Simple_Object2";
        GLColor color1 = new GLColor(Color.BLUE);
        GLColor color2 = new GLColor(Color.RED);
        int drawMode = GL10.GL_TRIANGLES;
        int axisCount = 3;
        Orientation orientation0 = new Orientation();
        Orientation orientation1 = new Orientation();
        Orientation orientation2 = new Orientation();
        
        orientation1.scale(0.5F, 0.5F, 0.5F);
        orientation2.scale(0.5F, 0.5F, 0.5F);
        
        SimpleObject simpleObject1 = SimpleObject.Factory.create(name1, vertices1, drawMode, axisCount, color1, orientation1);
        SimpleObject simpleObject2 = SimpleObject.Factory.create(name2, vertices2, drawMode, axisCount, color2, orientation2);
        
        Actor actor1 = new Actor(simpleObject1, orientation1);
        Actor actor2 = new Actor(simpleObject2, orientation2);
        
        
        
        ChainObject chainObject = new ChainObject.Builder()
                                  .addObject(actor1)
                                  .addObject(actor2)
                                  .create("chainObject", orientation0);
                                  
        Actor actor3 = new Actor(chainObject, orientation0);
        /**
        renderer.addObject(chainObject);
        
        orientation0.translate(0.5F, 0.5F, 0);
        orientation1.rotate(20F, 0, 0, 1);
        
        orientation0.rotate(50F, 0, 0, 1);
        */
        
        Action stupidAction = new Action(Action.Order.TSR);
        stupidAction.setTranslate(0.01F, -0.01f, 0, 50);
        Action stupidAction2 = new Action(Action.Order.TSR);
        stupidAction2.setTranslate(-0.01F, 0.01F, 0, 50);
        Action stupidAction3 = new Action(Action.Order.TSR);
        stupidAction3.setRotate(10F, 0, 0, 1, 100);
        actor1.addAction(stupidAction);
        actor2.addAction(stupidAction2);
        actor3.addAction(stupidAction3);
        
        //fakeActor.addAction(stupidAction3);
        
        renderer.addObject(actor3);
        
        // orientation.translate(0.5F, 0.5F, 0);
        
    }

}
