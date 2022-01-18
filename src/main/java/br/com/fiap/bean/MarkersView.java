package br.com.fiap.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@Named
@RequestScoped
public class MarkersView implements Serializable {

    private MapModel simpleModel;

    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();

        //Shared coordinates
        LatLng coord1 = new LatLng(-23.697974331427965, -46.64471311548664);
 

        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Locadora 1"));
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }
}