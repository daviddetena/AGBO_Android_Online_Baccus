package com.daviddetena.baccus.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.daviddetena.baccus.R;
import com.daviddetena.baccus.model.Wine;

public class WineActivity extends Activity {

    private static final String TAG = WineActivity.class.getSimpleName();
    private ImageView mWineImage = null;
    private Wine mWine = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);

        mWine = new Wine("Vegaval", "Tinto", R.drawable.vegaval, "", "", "", "Valdepeñas",5);

        // Buscamos la referencia a la imagen en el .xml y se la asignamos a la propiedad
        mWineImage = (ImageView) findViewById(R.id.wine_image);

    }

    /**
     * Método que se ejecuta cuando se pulsa en el botón de CAMBIAR
     * Queremos que cambie la imagen sólo al pulsar el botón
     * @param view
     */
    public void changeImage(View view){
        mWineImage.setImageResource(mWine.getPhoto());
    }
}
