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
    
    @Query(value = "select v.id_vuelo,v.fecha_hora_salida, v.fecha_hora_llegada,v.estado,v.aeropuerto_salida,v.aeropuerto_llegada,v.id_avion,v.id_tripulante,v.precio_economica,v.precio_ejecutiva from vuelo v inner join avion a on v.id_avion = a.id_avion inner join aerolinea a2 on a.id_aerolinea = a2.id_aerolinea where v.estado = 'Pendiente abordar' and a2.id_aerolinea =?1 and cast(v.fecha_hora_salida as DATE) = current_date and cast(v.fecha_hora_salida as TIMESTAMP) >= current_timestamp",nativeQuery = true)
    public Iterable<Vuelo> getVuelosAbordar(Integer idAerolinea);
}
