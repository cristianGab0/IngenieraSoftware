/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gt.umg.ing.software.dto.response;

import java.util.Date;

/**
 *
 * @author Cristian
 */
public interface IDetalleVueloPasajero {
    Integer getIdVuelo();
    String getModeloAvion();
    String getOrigen();
    String getDestino();
    Date getFechaHoraSalida();
    Date getFechaHoraLlegada();
}
