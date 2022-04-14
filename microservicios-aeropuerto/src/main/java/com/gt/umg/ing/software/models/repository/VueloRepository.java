/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.models.repository;

import com.gt.umg.ing.software.dto.response.IRepoListadoVuelos;
import com.gt.umg.ing.software.models.entity.Vuelo;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cristian
 */
public interface VueloRepository extends JpaRepository<Vuelo, Integer> {

    @Query(value = "select v from Vuelo v where v.fecha_hora_salida >= (?1) order by v.fecha_hora_salida")
    public Iterable<Vuelo> getVuelosFechaInicial(String fecha);

    @Query(value = "select count(v.id_vuelo) from pasajero p inner join vuelo_pasajero vp on p.no_pasaporte = vp.no_pasaporte inner join vuelo v on v.id_vuelo = vp.id_vuelo where p.no_pasaporte =?1 and (CAST (?2 AS TIMESTAMP)  between v.fecha_hora_salida and v.fecha_hora_llegada) and (CAST (?3 AS TIMESTAMP)  between v.fecha_hora_salida and v.fecha_hora_llegada)", nativeQuery = true)
    public int getTieneVuelosUsuario(Long idUsuario, String fechaSalida, String fechaLlegada);

    @Query(value = "select v.id_vuelo,v.fecha_hora_salida, v.fecha_hora_llegada,v.estado,v.aeropuerto_salida,v.aeropuerto_llegada,v.id_avion,v.id_tripulante,v.precio_economica,v.precio_ejecutiva from vuelo v inner join avion a on v.id_avion = a.id_avion inner join aerolinea a2 on a.id_aerolinea = a2.id_aerolinea where v.estado = 'Pendiente abordar' and a2.id_aerolinea =?1 and cast(v.fecha_hora_salida as DATE) = current_date and cast(v.fecha_hora_salida as TIMESTAMP) >= current_timestamp", nativeQuery = true)
    public Iterable<Vuelo> getVuelosAbordar(Integer idAerolinea);

    @Query(value = "select v.id_vuelo as vuelo,ca.modelo as avion,a2.nombre as aerolinea, aerSa.nombre as origen, aerLle.nombre as destino, v.fecha_hora_salida as salida,v.fecha_hora_llegada as llegada from vuelo v inner join avion a on v.id_avion = a.id_avion and v.id_aerolinea = a.id_aerolinea inner join catalogo_avion ca on ca.id_avion = a.id_avion inner join aerolinea a2 on a.id_aerolinea = a2.id_aerolinea inner join aeropuerto aerSa on v.aeropuerto_salida = aerSa.id_aeropuerto inner join aeropuerto aerLle on v.aeropuerto_llegada  = aerLle.id_aeropuerto where v.fecha_hora_salida between CAST (?1 AS TIMESTAMP) and CAST (?2 AS TIMESTAMP) or v.fecha_hora_llegada between CAST (?1 AS TIMESTAMP) and CAST (?2 AS TIMESTAMP)", nativeQuery = true)
    public Iterable<IRepoListadoVuelos> getVuelosByFechaHoraSalidaLlegada(String fechaSalida, String fechaLlegada);

    @Query(value = "select v.id_vuelo as vuelo,ca.modelo as avion,a2.nombre as aerolinea, aerSa.nombre as origen, aerLle.nombre as destino, v.fecha_hora_salida as salida,v.fecha_hora_llegada as llegada from vuelo v inner join avion a on v.id_avion = a.id_avion and v.id_aerolinea = a.id_aerolinea inner join catalogo_avion ca on ca.id_avion = a.id_avion inner join aerolinea a2 on a.id_aerolinea = a2.id_aerolinea inner join aeropuerto aerSa on v.aeropuerto_salida = aerSa.id_aeropuerto inner join aeropuerto aerLle on v.aeropuerto_llegada  = aerLle.id_aeropuerto where v.id_vuelo =?1", nativeQuery = true)
    public Optional<IRepoListadoVuelos> getDetalleVuelo(int idVuelo);
    
}
