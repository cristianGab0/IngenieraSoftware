/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.service;

import com.gt.umg.ing.software.dto.response.IAerolineaAeropuerto;
import com.gt.umg.ing.software.models.entity.Aerolinea;
import com.gt.umg.ing.software.models.repository.AerolineaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cristian
 */
@Service
public class AerolineaService extends CommonService<Aerolinea, Integer, AerolineaRepository> {

    public Optional<Aerolinea> getAerolineaPorUsuario(String usuario) {
        return this.repository.getAerolineaPorUsuario(usuario);
    }

    public List<IAerolineaAeropuerto> getAerolineasAutorizadasByAeropuerto(int idAeropuerto) {
        return this.repository.getAerolineasAutorizadasByAeropuerto(idAeropuerto);
    }
}
