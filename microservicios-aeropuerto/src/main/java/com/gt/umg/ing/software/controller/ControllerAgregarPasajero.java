/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.controller;

import com.gt.umg.ing.software.dto.request.AeropuertoFechasSalidaLLegadaDto;
import com.gt.umg.ing.software.dto.request.AgregarVueloPasajeroDto;
import com.gt.umg.ing.software.dto.request.FechasDto;
import com.gt.umg.ing.software.dto.request.VuelosResumenDto;
import com.gt.umg.ing.software.models.entity.Aeropuerto;
import com.gt.umg.ing.software.models.entity.Vuelo;
import com.gt.umg.ing.software.models.entity.VueloPasajero;
import com.gt.umg.ing.software.models.entity.VueloPasajeroId;
import com.gt.umg.ing.software.service.AeropuertoService;
import com.gt.umg.ing.software.service.SillonService;
import com.gt.umg.ing.software.service.VueloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/agregar-pasajero")
public class ControllerAgregarPasajero {
    
    @Autowired
    private AeropuertoService aeropuertoService;

    @Autowired
    private VueloService vueloService;

    @Autowired
    private SillonService sillonService;

    @Secured("ROLE_CLIENTE")
    @GetMapping("obtenerAeropuertos")
    @ApiOperation(value = "Obtiene los aeropuertos")
    public ResponseEntity<?> obtenerAeropuertos() {
        List<Aeropuerto> aeropuertos = (List<Aeropuerto>) aeropuertoService.findAll();

        if (aeropuertos.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron aeropuertos autorizados para la aerolínea.");
        }

        return ResponseEntity.ok().body(aeropuertos);
    }

    @Secured("ROLE_CLIENTE")
    @PostMapping("obtenerVuelosByAeropuertosFechas")
    @ApiOperation(value = "Obtiene los vuelos segun aeropuertos y fechas de salida y llegada")
    public ResponseEntity<?> obtenerVuelosByAeropuertosFechas(@RequestBody AeropuertoFechasSalidaLLegadaDto dto) {
        List<Vuelo> vuelosBd = (List<Vuelo>) vueloService.getVuelosFechaInicial(dto.getFechaHoraSalida());
        List<List<Vuelo>> vuelosRespuesta = new ArrayList<>();
        List<Vuelo> vuelosEscala = new ArrayList<>();
        for (Vuelo vuelo : vuelosBd) {
            if (vuelo.getAeropuertoSalida() == dto.getAeropuertoSalida() && vuelo.getAeropuertoLlegada() == dto.getAeropuertoLlegada()) {
                vuelosEscala.add(vuelo);
                vuelosRespuesta.add(vuelosEscala);
            }
        }
        vuelosBd = vueloService.limpiarLista(vuelosBd, vuelosEscala);
        int contadorDoWhile = vuelosBd.size();
        do {
            contadorDoWhile--;

            List<Vuelo> vueloTemp = vueloService.armarEscalas(vuelosBd, dto.getAeropuertoSalida(), dto.getAeropuertoLlegada());

            if (vueloTemp.size() > 1) {
                vuelosRespuesta.add(vueloTemp);
            }
            vuelosBd = vueloService.limpiarLista(vuelosBd, vueloTemp);

        } while (contadorDoWhile > 0);

        List<VuelosResumenDto> respuestaList = vueloService.crearRespuestaVuelosEscala(vuelosRespuesta);

        return ResponseEntity.ok().body(respuestaList);
    }

    @Secured("ROLE_CLIENTE")
    @PostMapping("/obtenerDetalleVuelos")
    public ResponseEntity<?> obtenerDetalleVuelos(@RequestBody List<Integer> vuelos) {
        vuelos = vuelos.stream().distinct().collect(Collectors.toList());
        List<Vuelo> respuesta = new ArrayList<>();

        for (Integer vuelo : vuelos) {
            Optional<Vuelo> vueloBD = vueloService.findById(vuelo);
            if (vueloBD.isPresent()) {
                respuesta.add(vueloBD.get());
            }
        }

        return ResponseEntity.ok().body(respuesta);
    }

    @Secured("ROLE_CLIENTE")
    @GetMapping("obtenerSillonesDiponiblesByVuelo/{idVuelo}")
    public ResponseEntity<?> findSillonesDisponiblesByVuelo(@PathVariable Integer idVuelo) {
        return ResponseEntity.ok().body(sillonService.findSillonesDisponiblesByVuelo(idVuelo));
    }

    @Secured("ROLE_CLIENTE")
    @PostMapping("validarVuelosUsuario/{idUsuario}")
    public ResponseEntity<?> getTieneVuelosUsuario(@PathVariable Long idUsuario, @RequestBody FechasDto fechas) {
        int cantidadVuelos = this.vueloService.getTieneVuelosUsuario(fechas.getFechaHoraSalida(), fechas.getFechaHoraLlegada(), idUsuario);

        if (cantidadVuelos == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se puede seleccionar el vuelo porque ya tiene vuelos asignados.");
        } else {
            return ResponseEntity.ok().body(true);
        }
    }

    @Secured("ROLE_CLIENTE")
    @PostMapping("agregarPasajeroVuelo/{pasaporte}")
    public ResponseEntity<?> agregarPasajeroVuelo(@RequestBody AgregarVueloPasajeroDto dto, @PathVariable Long pasaporte) {
        try {
            Vuelo vuelo = dto.getVuelo();
            Vuelo vueloBD = vueloService.save(vuelo);

            VueloPasajero vp = dto.getVueloPasajero();
            vp.setId(new VueloPasajeroId(vueloBD.getIdvuelo(), pasaporte));
        } catch (Exception e) {
            return ResponseEntity.ok().body(false);
        }

        return ResponseEntity.ok().body(true);
    }
}
