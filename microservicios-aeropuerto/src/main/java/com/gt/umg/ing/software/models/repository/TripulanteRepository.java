/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.models.repository;

import com.gt.umg.ing.software.models.entity.Tripulante;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cristian
 */
public interface TripulanteRepository extends JpaRepository<Tripulante, Integer> {

    @Query(value = "select distinct t.id_tripulante,t.nombre,t.puesto,t.estado from tripulante t  left join vuelo_tripulante vt on t.id_tripulante = vt.id_tripulante left join vuelo v on vt.id_vuelo = v.id_vuelo where (v.fecha_hora_salida is null or CAST (?1 AS TIMESTAMP) not between v.fecha_hora_salida and v.fecha_hora_llegada) and (v.fecha_hora_llegada is null or CAST (?2 AS TIMESTAMP) not between v.fecha_hora_salida and v.fecha_hora_llegada) order by t.puesto", nativeQuery = true)
    public Iterable<Tripulante> findTripulantesByFechaHora(Date fechaHoraSalida, Date fechaHoraLlegada);
}
