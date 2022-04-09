package com.gt.umg.ing.software.models.repository;

import com.gt.umg.ing.software.dto.response.IAvionDto;
import com.gt.umg.ing.software.models.entity.Avion;
import com.gt.umg.ing.software.models.entity.AvionId;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cristian
 */
public interface AvionRepository extends JpaRepository<Avion, AvionId>{


    @Query(value = "select a.id_avion as idAvion,a.id_aerolinea AS idAerolinea, ca.modelo AS modelo,ca.marca AS Marca,a.anio ,a.estado from catalogo_avion ca inner join avion a on a.id_avion = ca.id_avion inner join aerolinea a2 on a2.id_aerolinea = a.id_aerolinea where a2.id_aerolinea =?1", nativeQuery = true)
    public Iterable<IAvionDto> findAvionesByNombreAerolinea(int idAerolinea);
    
    
    @Query(value =  "select a.id_avion as idAvion,a.anio,a.id_aerolinea AS idAerolinea,a.estado,ca.modelo AS modelo,ca.marca AS Marca from vuelo v right join avion a on a.id_avion= v.id_avion  and v.id_aerolinea =a.id_aerolinea inner join catalogo_avion ca on ca.id_avion =a.id_avion where a.id_aerolinea=?1 and a.estado ='A' and (v.fecha_hora_salida is null or CAST (?2 AS TIMESTAMP) not between v.fecha_hora_salida and v.fecha_hora_llegada) and (v.fecha_hora_llegada is null or CAST (?3 AS TIMESTAMP) not between v.fecha_hora_salida and v.fecha_hora_llegada)", nativeQuery = true)
    public Iterable<IAvionDto> findAvionesByAerolineaFechaHora(Long idAerolinea,Date fechaHoraSalida,Date fechaHoraLlegada);
}
