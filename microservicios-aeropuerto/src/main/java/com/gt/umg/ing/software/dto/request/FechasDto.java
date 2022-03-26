/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.dto.request;

import java.util.Date;

/**
 *
 * @author Cristian
 */
public class FechasDto {
    private Date fechaHoraSalida;
    private Date fechaHoraLlegada;

    public FechasDto() {
    }

    public FechasDto(Date fechaHoraSalida, Date fechaHoraLlegada) {
        this.fechaHoraSalida = fechaHoraSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
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
    
     
}
