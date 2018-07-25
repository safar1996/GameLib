package com.sstudio.glib;

import android.app.Activity;
import android.os.Bundle;
import com.sstudio.glib.scene.g3d.*;



public class MainActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SimpleScene(this));
        
    }
    
}
