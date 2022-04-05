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
public class AeropuertoFechasSalidaLLegadaDto {

    private Long aeropuertoSalida;
    private Long aeropuertoLlegada;
    private Date fechaHoraSalida;

    public AeropuertoFechasSalidaLLegadaDto() {
    }

    public AeropuertoFechasSalidaLLegadaDto(Long aeropuertoSalida, Long aeropuertoLlegad, Date fechaHoraSalida) {
        this.aeropuertoSalida = aeropuertoSalida;
        this.aeropuertoLlegada = aeropuertoLlegad;
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public Long getAeropuertoSalida() {
        return aeropuertoSalida;
    }

    public void setAeropuertoSalida(Long aeropuertoSalida) {
        this.aeropuertoSalida = aeropuertoSalida;
    }

    public Long getAeropuertoLlegada() {
        return aeropuertoLlegada;
    }

    public void setAeropuertoLlegada(Long aeropuertoLlegada) {
        this.aeropuertoLlegada = aeropuertoLlegada;
    }

    public Date getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Date fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }
}
