/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.service;

import com.gt.umg.ing.software.models.entity.Aerolinea;
import com.gt.umg.ing.software.models.repository.AerolineaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cristian
 */
@Service
public class AerolineaService extends CommonService<Aerolinea,Integer, AerolineaRepository> {
    
}
