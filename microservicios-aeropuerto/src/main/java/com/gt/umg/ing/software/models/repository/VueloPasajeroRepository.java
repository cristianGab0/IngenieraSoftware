/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.models.repository;

import com.gt.umg.ing.software.dto.response.IRepoEquipaje;
import com.gt.umg.ing.software.models.entity.VueloPasajero;
import com.gt.umg.ing.software.models.entity.VueloPasajeroId;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cristian
 */
public interface VueloPasajeroRepository extends JpaRepository<VueloPasajero, VueloPasajeroId>{
    
    @Query(value = "select count(*) from vuelo_pasajero vp where vp.id_vuelo =?2 and vp.no_pasaporte =?1",nativeQuery = true)
    public Integer getVuelosFechaInicial(Long noPasaporte, Integer idVuelo);
    
    @Query("select vp from VueloPasajero vp where id_vuelo =?1 and estado_boleto <> 'ABORDADO'")
    public Iterable<VueloPasajero> getBoletosSinAbordar(Integer idVuelo);
                
    @Query(value="select p.nombre,vp.equipaje,vp.peso_equipaje as peso from vuelo_pasajero vp inner join pasajero p on vp.no_pasaporte = p.no_pasaporte where vp.id_vuelo =?1",nativeQuery = true)
    public Iterable<IRepoEquipaje> getDetalleVueloPasajero(Integer idVuelo);
}
