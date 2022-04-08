/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.models.repository;

import com.gt.umg.ing.software.models.entity.Sillon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cristian
 */
public interface SillonRepository extends JpaRepository<Sillon, Integer> {

    @Query(value = "select s.id_sillon,s.nombre,s.id_avion from sillon s inner join avion a on s.id_avion = a.id_avion inner join vuelo v on a.id_avion = v.id_avion and a.id_aerolinea =v.id_aerolinea where v.id_vuelo =?1 and s.id_sillon not in (select distinct vp2.sillon from vuelo v2 left join vuelo_pasajero vp2 on vp2.id_vuelo = v2.id_vuelo inner join avion a2 on v2.id_avion = a2.id_avion and a2.id_aerolinea =v2.id_aerolinea inner join sillon s2 on a2.id_avion = s2.id_avion where v2.id_vuelo =?1 and vp2.sillon is not null)", nativeQuery = true)
    public Iterable<Sillon> findSillonesDisponiblesByVuelo(Integer idVuelo);
}
