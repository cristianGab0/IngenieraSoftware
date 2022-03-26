/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.models.repository;

import com.gt.umg.ing.software.models.entity.Aeropuerto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cristian
 */
public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Integer>{
    
    @Query(value = "select a.id_aeropuerto,a.nombre,a.pais,a.ciudad  from aeropuerto a inner join aeropuerto_aerolinea a2 on a.id_aeropuerto = a2.id_aeropuerto where a2.id_aerolinea=?1", nativeQuery = true)
    public Iterable<Aeropuerto> getAeropuertoByAerolinea(int aerolinea);
}
