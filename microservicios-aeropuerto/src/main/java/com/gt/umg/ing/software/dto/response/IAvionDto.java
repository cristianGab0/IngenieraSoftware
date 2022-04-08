/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.dto.response;

/**
 *
 * @author Cristian
 */
public interface IAvionDto {

    int getIdAvion();
    int getIdAerolinea();
    String getModelo();
    String getMarca();
    String getAnio();
    Character getEstado();
}
