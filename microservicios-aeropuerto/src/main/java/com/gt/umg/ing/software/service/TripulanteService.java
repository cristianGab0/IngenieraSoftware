/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.service;

import com.gt.umg.ing.software.dto.request.FechasDto;
import com.gt.umg.ing.software.models.entity.Tripulante;
import com.gt.umg.ing.software.models.repository.TripulanteRepository;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cristian
 */
@Service
public class TripulanteService extends CommonService<Tripulante, Integer, TripulanteRepository> {
    
    public Iterable<Tripulante> findTripulantesByFechaHora(FechasDto fechas){
        return this.repository.findTripulantesByFechaHora(fechas.getFechaHoraSalida(), fechas.getFechaHoraLlegada());
    }
}
