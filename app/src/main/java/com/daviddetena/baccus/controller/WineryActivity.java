package com.daviddetena.baccus.controller;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.daviddetena.baccus.R;
import com.daviddetena.baccus.model.Wine;

public class WineryActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winery);


        // Creamos dos modelos de vino, un bembibre y un vegaval
        Wine bembibre = new Wine("Bembibre",
                "Tinto",
                R.drawable.bembibre,
                "Dominio de Tares",
                "http://www.dominiodetares.com/portfolio/bembibre/",
                "Este vino muestra toda la complejidad y la elegancia de la variedad Mencía. En fase visual luce un color rojo picota muy cubierto con tonalidades violáceas en el menisco. En nariz aparecen recuerdos frutales muy intensos de frutas rojas (frambuesa, cereza) y una potente ciruela negra, así como tonos florales de la gama de las rosas y violetas, vegetales muy elegantes y complementarios, hojarasca verde, tabaco y maderas aromáticas (sándalo) que le brindan un toque ciertamente perfumado.",
                "El Bierzo",
                5);

        // Añadimos una uva
        bembibre.addGrape("Mencía");


        Wine vegaval = new Wine("Vegaval",
                "Tinto",
                R.drawable.vegaval,
                "Miguel de Calatayud",
                "http://www.vegaval.com/es",
                "Bla blah blah bla",
                "Valdepeñas",
                4);

        // Añadimos una uva
        vegaval.addGrape("Tempranillo");


        // Definimos pestañas de TabHost y creamos el intent para cambiar de pantalla

        // Creamos primera pestaña del TabHost
        Intent intent = new Intent(this, WineActivity.class);
        intent.putExtra(WineActivity.EXTRA_WINE, bembibre);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec = tabHost
                .newTabSpec(bembibre.getName())
                .setIndicator(bembibre.getName())
                .setContent(intent);

        tabHost.addTab(spec);


        // Creamos segunda pestaña del TabHost, no es necesario definir de nuevo el intent, ya que
        // sólo se mostrará uno al mismo tiempo
        intent = new Intent(this, WineActivity.class);
        intent.putExtra(WineActivity.EXTRA_WINE, vegaval);

        spec = tabHost
                .newTabSpec(vegaval.getName())
                .setIndicator(vegaval.getName())
                .setContent(intent);

        tabHost.addTab(spec);

    }


}
