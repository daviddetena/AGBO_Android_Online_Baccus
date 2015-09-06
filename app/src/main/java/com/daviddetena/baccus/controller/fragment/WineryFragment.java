package com.daviddetena.baccus.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daviddetena.baccus.R;
import com.daviddetena.baccus.model.Wine;

/**
 * Created by daviddetena on 06/09/15.
 */
public class WineryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_winery, container, false);

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

        // Creamos tab host pero con fragments. Cada tab será un fragment
        FragmentTabHost tabHost = (FragmentTabHost) root.findViewById(android.R.id.tabhost);

        // La actividad actual la incluyo en tabcontent definido en fragment_winery.xml
        tabHost.setup(getActivity(), getActivity().getSupportFragmentManager(), android.R.id.tabcontent);

        // Preparamos argumentos que se le pasarán al Fragment que se pasa a cada Tab. En este caso
        // el argumento es el vino en sí (uno por tab)

        // Añadimos primera pestaña
        Bundle arguments = new Bundle();
        arguments.putSerializable(WineFragment.ARG_WINE, bembibre);

        tabHost.addTab(tabHost
                .newTabSpec(bembibre.getName())
                .setIndicator(bembibre.getName()), WineFragment.class, arguments);

        // Añadimos segunda pestaña
        arguments = new Bundle();
        arguments.putSerializable(WineFragment.ARG_WINE, vegaval);

        tabHost.addTab(tabHost
                .newTabSpec(vegaval.getName())
                .setIndicator(vegaval.getName()), WineFragment.class, arguments);

        return root;
    }
}
