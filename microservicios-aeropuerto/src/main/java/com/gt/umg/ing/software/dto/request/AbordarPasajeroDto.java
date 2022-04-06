package com.gt.umg.ing.software.dto.request;

import java.math.BigDecimal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cristian
 */
public class AbordarPasajeroDto {
    
    private String equipaje;
    private BigDecimal pesoEquipaje;
    private BigDecimal pagoExtra;

    public AbordarPasajeroDto() {
    }

    public AbordarPasajeroDto(String equipaje, BigDecimal pesoEquipaje, BigDecimal pagoExtra) {
        this.equipaje = equipaje;
        this.pesoEquipaje = pesoEquipaje;
        this.pagoExtra = pagoExtra;
    }

    public String getEquipaje() {
        return equipaje;
    }

    public void setEquipaje(String equipaje) {
        this.equipaje = equipaje;
    }

    public BigDecimal getPesoEquipaje() {
        return pesoEquipaje;
    }

    public void setPesoEquipaje(BigDecimal pesoEquipaje) {
        this.pesoEquipaje = pesoEquipaje;
    }

    public BigDecimal getPagoExtra() {
        return pagoExtra;
    }

    public void setPagoExtra(BigDecimal pagoExtra) {
        this.pagoExtra = pagoExtra;
    }
    
    
}
