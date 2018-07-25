package com.sstudio.glib.graphic;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
import java.util.List;
import com.sstudio.glib.math.Orientation;
import android.opengl.*;


public class Actor implements GLObject {
    
    protected List<Action> actionSequence;
    protected GLObject object;
    protected Orientation orientation;
    
    public Actor(GLObject object, Orientation orientation) {
        this.object = object;
        this.orientation = orientation;
        actionSequence = new ArrayList<Action>();
    }
    
    @Override
    public void init() {
        object.init();
    }
    
    @Override
    public void update(float deltaTime) {
        object.update(deltaTime);
        Action currentAction = nextAction();
        if (currentAction != null) {
            Action.MatrixTyped matrixTyped;
            currentAction.begin();
            while ((matrixTyped = currentAction.nextMatrix()) != null) {
                switch (matrixTyped.type) {
                    case TRANSLATE: 
                        float[] translateM = orientation.getTranslateM();
                        //Matrix.setIdentityM(translateM, 0);
                        Matrix.multiplyMM(translateM, 0, translateM, 0, matrixTyped.matrix, 0);
                        break;
                    case ROTATE:
                        float[] rotateM = orientation.getRotateM();
                        Matrix.multiplyMM(rotateM, 0, rotateM, 0, matrixTyped.matrix, 0);
                        break;
                    case SCALE:
                        float[] scaleM = orientation.getScaleM();
                        Matrix.multiplyMM(scaleM, 0, scaleM, 0, matrixTyped.matrix, 0);
                        break;
                }
                orientation.updateMatrix();
            }
            currentAction.end();
        } 
    }
    
    @Override
    public void draw(GL10 gl) {
        object.draw(gl);
    }

    @Override
    public void dispose() {
        object.dispose();
    }

    @Override
    public String getId() {
        return object.getId();
    }
    
    protected Action nextAction() {
        for (Action action : actionSequence) {
            if (action.isDone()) {
                continue;
            }
            return action;
        }
        return null;
    }
    
    public void resetAction() {
        for (Action action : actionSequence) {
            action.reset();
        }
    }
    
    public Actor addAction(Action action) {
        actionSequence.add(action);
        return this;
    }
    
}
