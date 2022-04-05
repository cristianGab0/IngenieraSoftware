/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.dto.request;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Cristian
 */
public class VuelosResumenDto {

    List<Integer> idsVuelos;
    private Date fechaHoraSalida;
    private Date fechaHoraLlegada;
    private String tiempoTotal;
    private double precioEconomica;
    private double precioEjecutiva;

    public VuelosResumenDto() {
    }

    public VuelosResumenDto(List<Integer> idsVuelos, Date fechaHoraSalida, Date fechaHoraLlegada, String tiempoTotal, double precioEconomica, double precioEjecutiva) {
        this.idsVuelos = idsVuelos;
        this.fechaHoraSalida = fechaHoraSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.tiempoTotal = tiempoTotal;
        this.precioEconomica = precioEconomica;
        this.precioEjecutiva = precioEjecutiva;
    }

    public List<Integer> getIdsVuelos() {
        return idsVuelos;
    }

    public void setIdsVuelos(List<Integer> idsVuelos) {
        this.idsVuelos = idsVuelos;
    }

    public Date getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Date fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public Date getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(Date fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public String getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(String tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public double getPrecioEconomica() {
        return precioEconomica;
    }

    public void setPrecioEconomica(double precioEconomica) {
        this.precioEconomica = precioEconomica;
    }

    public double getPrecioEjecutiva() {
        return precioEjecutiva;
    }

    public void setPrecioEjecutiva(double precioEjecutiva) {
        this.precioEjecutiva = precioEjecutiva;
    }

    
    
}
