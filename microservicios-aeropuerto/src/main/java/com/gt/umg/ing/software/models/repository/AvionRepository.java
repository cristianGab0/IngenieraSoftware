package com.gt.umg.ing.software.models.repository;

import com.gt.umg.ing.software.models.entity.Avion;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cristian
 */
public interface AvionRepository extends JpaRepository<Avion, Integer>{


    @Query(value = "select a.id_avion,a.modelo,a.marca ,a.anio,a.id_aerolinea,a.estado  from Avion a inner join Aerolinea a2 on a.id_aerolinea = a2.id_aerolinea where a.estado='A' and a2.nombre=?1", nativeQuery = true)
    public Iterable<Avion> findAvionesByNombreAerolinea(String nombre);
    
    
    @Query(value =  "select a.id_avion, a.modelo,a.marca ,a.anio,a.id_aerolinea, a.estado  from vuelo v right join avion a on a.id_avion= v.id_avion where a.id_aerolinea=?1 and a.estado ='A' and (v.fecha_hora_salida is null or CAST (?2 AS TIMESTAMP) not between v.fecha_hora_salida and v.fecha_hora_llegada) and (v.fecha_hora_llegada is null or CAST (?3 AS TIMESTAMP) not between v.fecha_hora_salida and v.fecha_hora_llegada)", nativeQuery = true)
    public Iterable<Avion> findAvionesByAerolineaFechaHora(Long idAerolinea,Date fechaHoraSalida,Date fechaHoraLlegada);
}
