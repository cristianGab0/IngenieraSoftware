/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.service;

import com.gt.umg.ing.software.dto.request.FechasDto;
import com.gt.umg.ing.software.models.entity.Avion;
import com.gt.umg.ing.software.models.repository.AvionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cristian
 */
@Service
public class AvionService extends CommonService<Avion, Integer, AvionRepository> {

    @Transactional(readOnly = true)
    public Iterable<Avion> findAvionesByNombreAerolinea(String nombreAerolinea) {
        return this.repository.findAvionesByNombreAerolinea(nombreAerolinea);
    }
    
    @Transactional(readOnly = true)
    public Iterable<Avion> findAvionesByAerolineaFechaHora(FechasDto fechas, Long idAerolinea){
        return this.repository.findAvionesByAerolineaFechaHora(idAerolinea,fechas.getFechaHoraSalida(),fechas.getFechaHoraLlegada());
    }
}
