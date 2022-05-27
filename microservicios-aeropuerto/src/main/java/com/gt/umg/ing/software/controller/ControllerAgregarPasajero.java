/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.controller;

import com.gt.umg.ing.software.dto.request.AeropuertoFechasSalidaLLegadaDto;
import com.gt.umg.ing.software.dto.request.FechasDto;
import com.gt.umg.ing.software.dto.request.VuelosResumenDto;
import com.gt.umg.ing.software.dto.response.IDetalleVueloPasajero;
import com.gt.umg.ing.software.dto.response.ISillonesVuelo;
import com.gt.umg.ing.software.models.entity.Aeropuerto;
import com.gt.umg.ing.software.models.entity.Pasajero;
import com.gt.umg.ing.software.models.entity.Vuelo;
import com.gt.umg.ing.software.models.entity.VueloPasajero;
import com.gt.umg.ing.software.service.AeropuertoService;
import com.gt.umg.ing.software.service.AvionService;
import com.gt.umg.ing.software.service.PasajeroService;
import com.gt.umg.ing.software.service.SillonService;
import com.gt.umg.ing.software.service.VueloPasajeroService;
import com.gt.umg.ing.software.service.VueloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cristian
 */
@Api
@RestController()
//@RequestMapping("/agregar-pasajero")
public class ControllerAgregarPasajero {
    
    @Autowired
    private AeropuertoService aeropuertoService;

    @Autowired
    private VueloService vueloService;

    @Autowired
    private SillonService sillonService;

    @Autowired
    private VueloPasajeroService vueloPasajeroService;

    @Autowired
    private AvionService avionService;

    @Autowired
    private PasajeroService pasajeroService;
    
    @Autowired 
    private HttpServletRequest request;
    
//    @Secured("ROLE_CLIENTE")
    @GetMapping("obtenerAeropuertos")
    @ApiOperation(value = "Obtiene los aeropuertos")
    public ResponseEntity<?> obtenerAeropuertos(@RequestHeader(value="x-forwarded-for",required=false)String xforwardedfor) {
        List<Aeropuerto> aeropuertos = (List<Aeropuerto>) aeropuertoService.findAll();
   
        
        if (aeropuertos.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron aeropuertos autorizados para la aerolínea.");
        }

        return ResponseEntity.ok().body(aeropuertos);
    }

//    @Secured("ROLE_CLIENTE")
    @PostMapping("obtenerVuelosByAeropuertosFechas")
    @ApiOperation(value = "Obtiene los vuelos segun aeropuertos y fechas de salida y llegada")
    public ResponseEntity<?> obtenerVuelosByAeropuertosFechas(@RequestBody AeropuertoFechasSalidaLLegadaDto dto) {
        List<Vuelo> vuelosBd = (List<Vuelo>) vueloService.getVuelosFechaInicial(dto.getFechaHoraSalida());
        List<List<Vuelo>> vuelosRespuesta = new ArrayList<>();
        List<Vuelo> vuelosEscala = new ArrayList<>();
        for (Vuelo vuelo : vuelosBd) {
            if (vuelo.getAeropuertoSalida() == dto.getAeropuertoSalida() && vuelo.getAeropuertoLlegada() == dto.getAeropuertoLlegada()) {
//                vuelosEscala.add(vuelo);
                vuelosRespuesta.add(Arrays.asList(vuelo));
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

//    @Secured("ROLE_CLIENTE")
    @PostMapping("/obtenerDetalleVuelos")
    public ResponseEntity<?> obtenerDetalleVuelos(@RequestBody List<Integer> vuelos) {
        vuelos = vuelos.stream().distinct().collect(Collectors.toList());
        List<IDetalleVueloPasajero> respuesta = new ArrayList<>();
        
        for (Integer vuelo : vuelos) {
            respuesta.add(vueloService.obtenerDetalleVuelo(vuelo));
        }
        
        return ResponseEntity.ok().body(respuesta);
    }

//    @Secured("ROLE_CLIENTE")
    @GetMapping("obtenerSillonesDiponiblesByVuelo/{idVuelo}")
    public ResponseEntity<?> findSillonesDisponiblesByVuelo(@PathVariable Integer idVuelo) {
        List<ISillonesVuelo> sillones = (List<ISillonesVuelo>) sillonService.findSillonesDisponiblesByVuelo(idVuelo);
        
        String tamanio = avionService.validarTamanioAvion(idVuelo);
        
        Map<String,Object> respuesta =  new HashMap<>();
        respuesta.put("tamanio", tamanio);
        respuesta.put("sillones", sillones);
        return ResponseEntity.ok().body(respuesta);
    }

//    @Secured("ROLE_CLIENTE")
    @PostMapping("validarVuelosUsuario/{idUsuario}")
    public ResponseEntity<?> getTieneVuelosUsuario(@PathVariable Long idUsuario, @RequestBody FechasDto fechas) {
        int cantidadVuelos = this.vueloService.getTieneVuelosUsuario(fechas.getFechaHoraSalida(), fechas.getFechaHoraLlegada(), idUsuario);

        if (cantidadVuelos == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se puede seleccionar el vuelo porque ya tiene vuelos asignados.");
        } else {
            return ResponseEntity.ok().body(true);
        }
    }

//    @Secured("ROLE_CLIENTE")
    @PostMapping("agregarPasajeroVuelo")
    public ResponseEntity<?> agregarPasajeroVuelo(@RequestBody List<VueloPasajero> dto) {
        dto.stream().peek(vp->{
            vp.setUsuarioAgrego(String.valueOf(vp.getId().getNoPasaporte()));
            vp.setIpUsuario(request.getRemoteHost());
        }).collect(Collectors.toList());
        vueloPasajeroService.saveAll(dto);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }
    
    @GetMapping("obtenerNombrePasajero/{idUsuario}")
    public ResponseEntity<?> obtenerNombrePasajero(@PathVariable long idUsuario) {
        Optional<Pasajero> pasajero = pasajeroService.findById(idUsuario);
        if(pasajero.isPresent()){
        return ResponseEntity.ok().body(pasajero.get().getNombre());
        }
        return ResponseEntity.ok().body("");
    }
    
    @GetMapping("obtenerIp")
    @ApiOperation(value = "Obtiene los aeropuertos")
    public String obtenerIp(@RequestHeader(value="x-forwarded-for",required=false)String xforwardedfor) {
        if(xforwardedfor!=null){
            return xforwardedfor;
        }else{
            return "No se obtuvo la IP";
        }
    }
}
