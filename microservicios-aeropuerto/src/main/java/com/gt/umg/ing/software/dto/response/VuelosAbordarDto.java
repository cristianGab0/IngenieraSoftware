/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.dto.response;

import com.gt.umg.ing.software.models.entity.Vuelo;
import java.util.Date;

/**
 *
 * @author Cristian
 */
public class VuelosAbordarDto {

    private Integer idVuelo;
    private String aeropuertoSalida;
    private String aeropuertoLlegada;
    private Date fechaHoraSalida;

    public VuelosAbordarDto() {
    }

    public VuelosAbordarDto(Integer idVuelo, String aeropuertoSalida, String aeropuertoLlegada, Date fechaHoraSalida) {
        this.idVuelo = idVuelo;
        this.aeropuertoSalida = aeropuertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegada;
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public Integer getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Integer idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getAeropuertoSalida() {
        return aeropuertoSalida;
    }

    public void setAeropuertoSalida(String aeropuertoSalida) {
        this.aeropuertoSalida = aeropuertoSalida;
    }

    public String getAeropuertoLlegada() {
        return aeropuertoLlegada;
    }

    public void setAeropuertoLlegada(String aeropuertoLlegada) {
        this.aeropuertoLlegada = aeropuertoLlegada;
    }

    public Date getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Date fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

}
