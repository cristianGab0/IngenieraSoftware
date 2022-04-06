/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.controller;

import com.gt.umg.ing.software.dto.request.AbordarPasajeroDto;
import com.gt.umg.ing.software.dto.response.VuelosAbordarDto;
import com.gt.umg.ing.software.models.entity.Aerolinea;
import com.gt.umg.ing.software.models.entity.Aeropuerto;
import com.gt.umg.ing.software.models.entity.Vuelo;
import com.gt.umg.ing.software.models.entity.VueloPasajero;
import com.gt.umg.ing.software.models.entity.VueloPasajeroId;
import com.gt.umg.ing.software.service.AerolineaService;
import com.gt.umg.ing.software.service.AeropuertoService;
import com.gt.umg.ing.software.service.VueloPasajeroService;
import com.gt.umg.ing.software.service.VueloService;
import io.swagger.annotations.Api;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cristian
 */
@Api
@RestController
public class ControllerAbordar {

    @Autowired
    private VueloService vueloService;

    @Autowired
    private AeropuertoService aeropuertoService;

    @Autowired
    private AerolineaService aerolineaService;

    @Autowired
    private VueloPasajeroService vueloPasajeroService;

    @GetMapping("obtenerVuelosAbordar/{usuario}")
    public ResponseEntity<?> getVuelosAbordar(@PathVariable String usuario) {
        Optional<Aerolinea> aerolineaBd = aerolineaService.getAerolineaPorUsuario(usuario);

        if (aerolineaBd.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tiene una aerolínea asignada.");
        }
        List<Vuelo> vuelos = (List<Vuelo>) vueloService.getVuelosAbordar(aerolineaBd.get().getIdAerolinea());

        if (vuelos.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay vuelos disponibles.");
        }

        List<VuelosAbordarDto> vuelosResp = new ArrayList<>();
        for (Vuelo v : vuelos) {
            VuelosAbordarDto vuelo = new VuelosAbordarDto();

            vuelo.setIdVuelo(v.getIdvuelo());

            Optional<Aeropuerto> aeropuertoSaBd = aeropuertoService.findById(v.getAeropuertoSalida());
            if (aeropuertoSaBd.isPresent()) {
                vuelo.setAeropuertoSalida(aeropuertoSaBd.get().getNombre());
            }

            Optional<Aeropuerto> aeropuertoLleBd = aeropuertoService.findById(v.getAeropuertoLlegada());
            if (aeropuertoLleBd.isPresent()) {
                vuelo.setAeropuertoLlegada(aeropuertoLleBd.get().getNombre());
            }

            vuelo.setFechaHoraSalida(v.getFecha_hora_salida());
            vuelosResp.add(vuelo);
        };

        return ResponseEntity.ok().body(vuelosResp);
    }

    @GetMapping("validarPasaporteVuelo/{noPasaporte}/{idVuelo}")
    public ResponseEntity<?> validarPasaporteVuelo(@PathVariable Long noPasaporte, @PathVariable Integer idVuelo) {
        boolean perteneceAlVuelo = vueloPasajeroService.validarPasaporteVuelo(noPasaporte, idVuelo);

        return ResponseEntity.ok().body(perteneceAlVuelo);
    }

    @PutMapping("abordarPasajero/{noPasaporte}/{idVuelo}")
    public ResponseEntity<?> abordarPasajero(@PathVariable Long noPasaporte, @PathVariable Integer idVuelo, @RequestParam AbordarPasajeroDto dto) {
        Optional<VueloPasajero> vp = vueloPasajeroService.findById(new VueloPasajeroId(idVuelo, noPasaporte));
        VueloPasajero respuesta = new VueloPasajero();
        if (vp.isPresent()) {
            VueloPasajero vueloPasajero = vp.get();
            vueloPasajero.setEquipaje(dto.getEquipaje());
            vueloPasajero.setPesoEquipaje(dto.getPesoEquipaje());
            vueloPasajero.setEstadoBoleto("ABORDADO");
            vueloPasajero.setPagoExtra(dto.getPagoExtra());

            respuesta = vueloPasajeroService.save(vueloPasajero);
        }

        return ResponseEntity.ok().body(respuesta);
    }

    @PutMapping("finalizarAbordaje/{idVuelo}")
    public ResponseEntity<?> finalizarAbordaje(@PathVariable Integer idVuelo) {
        List<VueloPasajero> vpBD = (List<VueloPasajero>) vueloPasajeroService.getBoletosSinAbordar(idVuelo);

        vpBD = vpBD.stream().map(v -> {
            v.setEstadoBoleto("CANCELADO");
            return v;
        }).collect(Collectors.toList());

        vueloPasajeroService.saveAll(vpBD);

        Optional<Vuelo> v = vueloService.findById(idVuelo);

        if (v.isPresent()) {
            Vuelo vuelo = v.get();
            vuelo.setEstado("ABORDADO");

            vueloService.save(vuelo);
        }

        return null;
    }
}
