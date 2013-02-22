package com.aprendiendodeandroidymas.teststartbootandroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;

/**
 * Clase que se va a encargar de cargar nuestra activity
 * @author gabriel
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // seteamos el layout a cargar
        setContentView(R.layout.activity_main);

        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}