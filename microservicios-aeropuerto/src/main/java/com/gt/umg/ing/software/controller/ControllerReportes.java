/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.controller;

import com.gt.umg.ing.software.dto.request.FechasDto;
import com.gt.umg.ing.software.dto.response.IAerolineaAeropuerto;
import com.gt.umg.ing.software.dto.response.IRepoAvionesAerolinea;
import com.gt.umg.ing.software.dto.response.IRepoEquipaje;
import com.gt.umg.ing.software.dto.response.IRepoListadoVuelos;
import com.gt.umg.ing.software.dto.response.PasajeroVueloDto;
import com.gt.umg.ing.software.models.entity.Aerolinea;
import com.gt.umg.ing.software.models.entity.Aeropuerto;
import com.gt.umg.ing.software.models.entity.Pasajero;
import com.gt.umg.ing.software.models.entity.Vuelo;
import com.gt.umg.ing.software.models.entity.VueloPasajero;
import com.gt.umg.ing.software.service.AerolineaService;
import com.gt.umg.ing.software.service.AeropuertoService;
import com.gt.umg.ing.software.service.AvionService;
import com.gt.umg.ing.software.service.PasajeroService;
import com.gt.umg.ing.software.service.VueloPasajeroService;
import com.gt.umg.ing.software.service.VueloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cristian
 */
@Api
@RestController()
@RequestMapping("/reporte")
public class ControllerReportes {

    @Autowired
    private VueloService vueloService;

    @Autowired
    private AeropuertoService aeropuertoService;

    @Autowired
    private AerolineaService aerolineaService;

    @Autowired
    private AvionService avionService;

    @Autowired
    private PasajeroService pasajeroService;
    @Autowired
    private VueloPasajeroService vueloPasajeroService;

    @PostMapping("vuelosPorFechaHora")
    @ApiOperation(value = "Obtiene el detalle de los vuelos segun parametros ingresados")
    public ResponseEntity<?> getVuelosByFechaHoraSalidaLlegada(@RequestBody FechasDto fechas) throws ParseException {
        List<IRepoListadoVuelos> vuelos = (List<IRepoListadoVuelos>) vueloService.getVuelosByFechaHoraSalidaLlegada(fechas.getFechaHoraSalida(), fechas.getFechaHoraLlegada());
        if (vuelos.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(vuelos);
    }

    @GetMapping("detalleVuelo/{idVuelo}")
    @ApiOperation(value = "Obtiene el detalle de un vuelo")
    public ResponseEntity<?> getDetalleVuelo(@PathVariable int idVuelo) {
        Optional<IRepoListadoVuelos> vuelo = vueloService.getDetalleVuelo(idVuelo);

        if (vuelo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El número de vuelo ingresado no se encontró");
        }
        return ResponseEntity.ok().body(vuelo.get());
    }

    @GetMapping("obtenerAeropuertos")
    @ApiOperation(value = "Obtiene los aeropuertos")
    public ResponseEntity<?> getAeropuertos() {
        List<Aeropuerto> aeropuertos = (List<Aeropuerto>) aeropuertoService.findAll();
        aeropuertos = aeropuertos.stream().peek(a -> a.setAerolineas(new ArrayList<>())).collect(Collectors.toList());
        return ResponseEntity.ok().body(aeropuertos);
    }

    @GetMapping("obtenerAerolineaByAeropuertoAutorizado/{idAeropuerto}")
    public ResponseEntity<?> getAerolineasAutorizadasByAeropuerto(@PathVariable int idAeropuerto) {
        List<IAerolineaAeropuerto> detalle = this.aerolineaService.getAerolineasAutorizadasByAeropuerto(idAeropuerto);
        if (detalle.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El aeropuerto consultado no tiene aerolíneas.");
        }
        return ResponseEntity.ok().body(detalle);
    }

    @GetMapping("obtenerAerolineas")
    @ApiOperation(value = "Obtiene las aerolineas")
    public ResponseEntity<?> getAerolineas() {
        List<Aerolinea> aerolineas = (List<Aerolinea>) aerolineaService.findAll();
        aerolineas = aerolineas
                .stream()
                .peek(a -> {
                    a.setAeropuertos(new ArrayList<>());
                    a.setAvions(new ArrayList<>());
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(aerolineas);
    }

    @GetMapping("obtenerDetalleAvionesPorAerolinea/{idAerolinea}")
    public ResponseEntity<?> findAvionesByAerolinea(@PathVariable int idAerolinea) {
        List<IRepoAvionesAerolinea> aviones = avionService.findAvionesByAerolinea(idAerolinea);

        if (aviones.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La aerolínea consultada no tiene aviones.");
        }
        return ResponseEntity.ok().body(aviones);
    }

//    @Secured("ROLE_CONSULTA_AEROLINEA")
    @GetMapping("obtenerPasajerosPorVuelo/{idVuelo}")
    public ResponseEntity<?> obtenerPasajerosByVuelo(@PathVariable int idVuelo) {
        Optional<Vuelo> vueloDb = vueloService.findById(idVuelo);

        if (vueloDb.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El número de vuelo ingresado no existe.");
        }

        List<Pasajero> pasajerosDb = (List<Pasajero>) pasajeroService.obtenerPasajerosByVuelo(idVuelo);

        List<PasajeroVueloDto> respuesta = pasajerosDb.stream().map(p
                -> new PasajeroVueloDto(p)
        ).collect(Collectors.toList());

        return ResponseEntity.ok().body(respuesta);
    }

    @GetMapping("obtenerAeropuertosAutorizadosPorAerolinea/{idAerolinea}")
    public ResponseEntity<?> obtenerAeropuertosPorAerolinea(@PathVariable int idAerolinea) {
        List<Aeropuerto> aeropuertosDb = (List<Aeropuerto>) aeropuertoService.getAeropuertoByAerolinea(idAerolinea);
        if (aeropuertosDb.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La aerolínea consultada no tiene destinos autorizados.");
        }
        aeropuertosDb = aeropuertosDb.stream().peek(a -> {
            a.setAerolineas(new ArrayList<>());
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(aeropuertosDb);
    }

    @GetMapping("obtenerDetalleEquipajeVuelo/{idVuelo}")
    public ResponseEntity<?> obtenerDetalleEquipajeVuelo(@PathVariable int idVuelo) {
        Optional<Vuelo> vueloDb = vueloService.findById(idVuelo);

        if (vueloDb.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El número de vuelo ingresado no existe.");
        }
        List<IRepoEquipaje> detalle = (List<IRepoEquipaje>) vueloPasajeroService.getDetalleVueloPasajero(idVuelo);
        return ResponseEntity.ok().body(detalle);
    }
}
