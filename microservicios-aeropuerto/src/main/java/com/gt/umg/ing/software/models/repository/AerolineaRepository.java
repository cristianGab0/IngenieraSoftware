/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.models.repository;

import com.gt.umg.ing.software.dto.response.IAerolineaAeropuerto;
import com.gt.umg.ing.software.models.entity.Aerolinea;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cristian
 */
public interface AerolineaRepository extends JpaRepository<Aerolinea, Integer>{
    
    @Query(value = "select a.id_aerolinea,a.nombre  from usuario u inner join aerolinea a on u.aerolinea = a.id_aerolinea where u.usuario =?1",nativeQuery = true)
    public Optional<Aerolinea> getAerolineaPorUsuario(String usuario);
  
    @Query(value = "select a.nombre as nombreAerolinea, count(a3.id_avion) as cantidadAviones from aerolinea a inner join aeropuerto_aerolinea aa on a.id_aerolinea = aa.id_aerolinea inner join aeropuerto a2 on aa.id_aeropuerto = a2.id_aeropuerto inner join avion a3 on a3.id_aerolinea = a.id_aerolinea where a2.id_aeropuerto =?1 group by a.nombre", nativeQuery = true)
    public List<IAerolineaAeropuerto> getAerolineasAutorizadasByAeropuerto(int idAeropuerto);

}
