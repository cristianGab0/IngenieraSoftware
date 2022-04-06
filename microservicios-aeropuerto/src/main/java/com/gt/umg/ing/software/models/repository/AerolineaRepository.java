/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.models.repository;

import com.gt.umg.ing.software.models.entity.Aerolinea;
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
}
