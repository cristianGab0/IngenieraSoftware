/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.service;

import com.gt.umg.ing.software.dto.request.FechasDto;
import com.gt.umg.ing.software.dto.response.IAvionDto;
import com.gt.umg.ing.software.dto.response.IRepoAvionesAerolinea;
import com.gt.umg.ing.software.models.entity.Avion;
import com.gt.umg.ing.software.models.entity.AvionId;
import com.gt.umg.ing.software.models.repository.AvionRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cristian
 */
@Service
public class AvionService extends CommonService<Avion, AvionId, AvionRepository> {

    @Transactional(readOnly = true)
    public Iterable<IAvionDto> findAvionesByNombreAerolinea(int idAerolinea) {
        return this.repository.findAvionesByNombreAerolinea(idAerolinea);
    }
    
    @Transactional(readOnly = true)
    public Iterable<IAvionDto> findAvionesByAerolineaFechaHora(FechasDto fechas, Long idAerolinea){
        return this.repository.findAvionesByAerolineaFechaHora(idAerolinea,fechas.getFechaHoraSalida(),fechas.getFechaHoraLlegada());
    }
    
    @Transactional(readOnly = true)
    public List<IRepoAvionesAerolinea> findAvionesByAerolinea(int idAerolinea){
        return this.repository.findAvionesByAerolinea(idAerolinea);
    }
}
