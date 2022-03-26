/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.service;

import com.gt.umg.ing.software.models.entity.Aeropuerto;
import com.gt.umg.ing.software.models.repository.AeropuertoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cristian
 */
@Service
public class AeropuertoService extends CommonService<Aeropuerto, Integer, AeropuertoRepository> {

    @Transactional(readOnly = true)
    public Iterable<Aeropuerto> getAeropuertoByAerolinea(int aerolinea) {
        return this.repository.getAeropuertoByAerolinea(aerolinea);
    }
}
