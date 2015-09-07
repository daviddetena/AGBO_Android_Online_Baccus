package com.daviddetena.baccus.model;

import com.daviddetena.baccus.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by daviddetena on 07/09/15.
 */
public class Winery {

    private static Winery sInstance = null;
    private List<Wine> mWines = null;

    /**
     * Método estático para acceder a la única instancia de Winery de la app
     * @return
     */
    public static Winery getInstance(){
        // Comprobamos si ya hay instancia. Si no la hay, la creamos. No seguro en multihilo
        if(sInstance==null){
            sInstance = new Winery();
        }
        return sInstance;
    }

    /**
     * Constructor por defecto de la bodega
     */
    public Winery(){
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


        Wine zarate = new Wine(
                "Zárate",
                "Blanco",
                R.drawable.zarate,
                "Miguel Calatayud",
                "http://bodegas-zarate.com/productos/vinos/albarino-zarate/",
                "El albariño Zarate es un vino blanco monovarietal que pertenece a la Denominación de Origen Rías Baixas. Considerado por la crítica especializada como uno de los grandes vinos blancos del mundo, el albariño ya es todo un mito.",
                "Rías bajas",
                4);
        zarate.addGrape("Albariño");

        Wine champagne = new Wine(
                "Champagne",
                "Otros",
                R.drawable.champagne,
                "Champagne Taittinger",
                "http://bodegas-zarate.com/productos/vinos/albarino-zarate/",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc purus. Curabitur eu velit mauris. Curabitur magna nisi, ullamcorper ac bibendum ac, laoreet et justo. Praesent vitae tortor quis diam luctus condimentum. Suspendisse potenti. In magna elit, interdum sit amet facilisis dictum, bibendum nec libero. Maecenas pellentesque posuere vehicula. Vivamus eget nisl urna, quis egestas sem. Vivamus at venenatis quam. Sed eu nulla a orci fringilla pulvinar ut eu diam. Morbi nibh nibh, bibendum at laoreet egestas, scelerisque et nisi. Donec ligula quam, semper nec bibendum in, semper eget dolor. In hac habitasse platea dictumst. Maecenas adipiscing semper rutrum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;",
                "Comtes de Champagne",
                5);
        champagne.addGrape("Chardonnay");

        // Añadimos los vinos como lista de elementos
        mWines = Arrays.asList(new Wine[]{bembibre, vegaval, zarate, champagne});
    }

    /**
     * Obtenemos el vino i-ésimo indicado por el índice
     * @param index
     * @return
     */
    public Wine getWine(int index){
        return mWines.get(index);
    }


    /**
     * Método que devuelve el número de vinos que tiene la bodega
     * @return
     */
    public int getWineCount(){
        return mWines.size();
    }

}
