/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.models.repository;

import com.gt.umg.ing.software.models.entity.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cristian
 */
public interface PasajeroRepository extends JpaRepository<Pasajero, Long>{
    
    @Query(value = "select p from VueloPasajero vp inner join Pasajero p on vp.id.noPasaporte = p.noPasaporte where vp.id.idVuelo =?1")
    public Iterable<Pasajero> obtenerPasajerosByVuelo(int idVuelo);
}
