package com.daviddetena.baccus.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daviddetena.baccus.R;
import com.daviddetena.baccus.model.Wine;

public class WineActivity extends Activity {

    private static final String TAG = WineActivity.class.getSimpleName();

    // Modelo
    private Wine mWine = null;

    // Vistas
    private ImageView mWineImage = null;
    private TextView mWineNameText = null;
    private TextView mWineTypeText = null;
    private TextView mWineOriginText = null;
    private RatingBar mWineRatingBar = null;
    private TextView mWineCompanyText = null;
    private TextView mWineNotesText = null;
    private ViewGroup mWineGrapesContainer = null;
    private ImageButton mGoToWebButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);

        // Creamos el modelo
        mWine = new Wine("Bembibre",
                "Tinto",
                R.drawable.vegaval,
                "Dominio de Tares",
                "http://www.dominiodetares.com/portfolio/bembibre/",
                "Este vino muestra toda la complejidad y la elegancia de la variedad Mencía. En fase visual luce un color rojo picota muy cubierto con tonalidades violáceas en el menisco. En nariz aparecen recuerdos frutales muy intensos de frutas rojas (frambuesa, cereza) y una potente ciruela negra, así como tonos florales de la gama de las rosas y violetas, vegetales muy elegantes y complementarios, hojarasca verde, tabaco y maderas aromáticas (sándalo) que le brindan un toque ciertamente perfumado.",
                "El Bierzo",
                5);

        // Añadimos una uva
        mWine.addGrape("Mencía");

        // Accedemos a las vistas desde el controlador, utilizando sus referencias en el .xml, para
        // asociarlas a nuestras variables
        mWineImage = (ImageView) findViewById(R.id.wine_image);
        mWineNameText = (TextView) findViewById(R.id.wine_name);
        mWineTypeText = (TextView) findViewById(R.id.wine_type);
        mWineOriginText = (TextView) findViewById(R.id.wine_origin);
        mWineRatingBar = (RatingBar) findViewById(R.id.wine_rating);
        mWineCompanyText = (TextView) findViewById(R.id.wine_company);
        mWineNotesText = (TextView) findViewById(R.id.wine_notes);
        mWineGrapesContainer = (ViewGroup) findViewById(R.id.grapes_container);
        mGoToWebButton = (ImageButton) findViewById(R.id.go_to_web_button);

        // Sincronizamos modelo y vista
        syncModelWithView();
    }

    /**
     * Método helper que utilizamos para mostrar en las vistas la información de nuestro modelo de
     * vino.
     */
    private void syncModelWithView(){
        mWineImage.setImageResource(mWine.getPhoto());
        mWineNameText.setText(mWine.getName());
        mWineTypeText.setText(mWine.getType());
        mWineOriginText.setText(mWine.getOrigin());
        mWineRatingBar.setRating(mWine.getRating());
        mWineCompanyText.setText(mWine.getCompanyName());
        mWineNotesText.setText(mWine.getNotes());

        // Creamos tantos TextViews como Uvas tenga el vino
        for(int i=0; i<mWine.getGrapeCount(); i++){
            TextView grapeText = new TextView(this);
            grapeText.setText(mWine.getGrape(i));

            // Queremos que la uva ocupe el ancho completo en su contenedor
            grapeText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            // Añadimos la vista de la uva que hemos creado a su contenedor
            mWineGrapesContainer.addView(grapeText);
        }

        // Configuramos botones

        // Clase anónima para suscribirme al evento del click en el botón. Implementamos los métodos
        // de dicha interfaz. En este caso, sólo el onClick, donde cargaremos nuestra WebView
        // Activity mediante un intent.
        mGoToWebButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(WineActivity.this, WebActivity.class);

                // Pasamos nuestro modelo Wine al Activity de WebActivity
                webIntent.putExtra(WebActivity.EXTRA_WINE, mWine);
                startActivity(webIntent);
            }
        });
    }
}
