/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.models.repository;

import com.gt.umg.ing.software.models.entity.Vuelo;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cristian
 */
public interface VueloRepository extends JpaRepository<Vuelo, Integer>{
    
    @Query(value = "select v from Vuelo v where v.fecha_hora_salida >= (?1) order by v.fecha_hora_salida")
    public Iterable<Vuelo> getVuelosFechaInicial(Date fecha);
    
    @Query(value="select count(v.id_vuelo) from pasajero p inner join vuelo_pasajero vp on p.no_pasaporte = vp.no_pasaporte inner join vuelo v on v.id_vuelo = vp.id_vuelo where p.no_pasaporte =?1 and (CAST (?2 AS TIMESTAMP)  between v.fecha_hora_salida and v.fecha_hora_llegada) and (CAST (?3 AS TIMESTAMP)  between v.fecha_hora_salida and v.fecha_hora_llegada)",nativeQuery = true)
    public int getTieneVuelosUsuario(Long idUsuario, Date fechaSalida,Date fechaLlegada );
}
