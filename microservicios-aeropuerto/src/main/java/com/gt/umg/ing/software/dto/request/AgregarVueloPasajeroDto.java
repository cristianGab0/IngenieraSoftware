/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.dto.request;

import com.gt.umg.ing.software.models.entity.Vuelo;
import com.gt.umg.ing.software.models.entity.VueloPasajero;

/**
 *
 * @author Cristian
 */
public class AgregarVueloPasajeroDto {

    private Vuelo vuelo;
    private VueloPasajero vueloPasajero;
    
    public AgregarVueloPasajeroDto() {
    }

    public AgregarVueloPasajeroDto(Vuelo vuelo, VueloPasajero vueloPasajero) {
        this.vuelo = vuelo;
        this.vueloPasajero = vueloPasajero;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public VueloPasajero getVueloPasajero() {
        return vueloPasajero;
    }

    public void setVueloPasajero(VueloPasajero vueloPasajero) {
        this.vueloPasajero = vueloPasajero;
    }

}
