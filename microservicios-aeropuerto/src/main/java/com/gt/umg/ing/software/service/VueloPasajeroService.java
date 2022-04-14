/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.service;

import com.gt.umg.ing.software.dto.response.IRepoEquipaje;
import com.gt.umg.ing.software.models.entity.VueloPasajero;
import com.gt.umg.ing.software.models.entity.VueloPasajeroId;
import com.gt.umg.ing.software.models.repository.VueloPasajeroRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cristian
 */
@Service
public class VueloPasajeroService extends CommonService<VueloPasajero, VueloPasajeroId, VueloPasajeroRepository> {
    
    public boolean validarPasaporteVuelo(Long noPasaporte, Integer idVuelo){
        Integer vuelos = this.repository.getVuelosFechaInicial(noPasaporte, idVuelo);
        return (vuelos > 0);
    }
    
    public Iterable<VueloPasajero> getBoletosSinAbordar(Integer idVuelo){
        return this.repository.getBoletosSinAbordar(idVuelo);
    }
    
    public void saveAll(List<VueloPasajero> vp){
        this.repository.saveAll(vp);
    }
    
    public Iterable<IRepoEquipaje> getDetalleVueloPasajero(Integer idVuelo){
     return this.repository.getDetalleVueloPasajero(idVuelo);
    }
}
