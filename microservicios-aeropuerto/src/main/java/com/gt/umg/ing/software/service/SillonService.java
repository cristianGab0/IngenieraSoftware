/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.service;

import com.gt.umg.ing.software.dto.response.ISillonesVuelo;
import com.gt.umg.ing.software.models.entity.Sillon;
import com.gt.umg.ing.software.models.repository.SillonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cristian
 */
@Service
public class SillonService extends CommonService<Sillon, Integer, SillonRepository> {
    
    @Transactional(readOnly = true)
    public Iterable<ISillonesVuelo> findSillonesDisponiblesByVuelo(Integer idVuelo){
        return this.repository.findSillonesDisponiblesByVuelo(idVuelo);
    }
}
