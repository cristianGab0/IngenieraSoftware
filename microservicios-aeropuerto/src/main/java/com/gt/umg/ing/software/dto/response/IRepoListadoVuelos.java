/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.dto.response;

import java.util.Date;

/**
 *
 * @author Cristian
 */
public interface IRepoListadoVuelos {
    int getVuelo();
    String getAvion();
    String getAerolinea();
    String getOrigen();
    String getDestino();
    Date getSalida();
    Date getLlegada();
}
