package com.gt.umg.ing.software.controller;

import com.gt.umg.ing.software.dto.request.FechasDto;
import com.gt.umg.ing.software.models.entity.Aeropuerto;
import com.gt.umg.ing.software.models.entity.Avion;
import com.gt.umg.ing.software.models.entity.Tripulante;
import com.gt.umg.ing.software.models.entity.Vuelo;
import com.gt.umg.ing.software.service.AerolineaService;
import com.gt.umg.ing.software.service.AeropuertoService;
import com.gt.umg.ing.software.service.AvionService;
import com.gt.umg.ing.software.service.TripulanteService;
import com.gt.umg.ing.software.service.VueloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cristian
 */
@Api
@RestController()
public class ControllerCrearVuelo {

    @Autowired
    private AvionService avionService;
    
    @Autowired
    private VueloService vueloService;

    @Autowired
    private AeropuertoService aeropuertoService;

    @Autowired
    private TripulanteService tripulanteService;
    
    @GetMapping("hayAvionesDisponiblesByAerolinea/{nombreAerolinea}")
    @ApiOperation(value = "Valida los aviones y aeropuertos disponibles por aerolinea")
    public ResponseEntity<?> hayAvionesDisponibles(@PathVariable String nombreAerolinea) {
        List<Avion> aviones = (List<Avion>) avionService.findAvionesByNombreAerolinea(nombreAerolinea);
        if (aviones.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede crear un vuelo porque la aerolínea no cuenta con aviones disponibles.");
        }
        List<Aeropuerto> aeropuertos = (List<Aeropuerto>) aeropuertoService.getAeropuertoByAerolinea(aviones.get(0).getAerolinea().getIdAerolinea());
        if (aeropuertos.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron aeropuertos autorizados para la aerolínea.");
        }
        return ResponseEntity.ok().body(aeropuertos);
    }

    @PostMapping("obtenerAvionsPorFechaHora/{idAerolinea}")
    @ApiOperation(value = "Obtiene los aviones disponibles de una aerolinea segun horarios de vuelo")
    public ResponseEntity<?> obtenerAvionesDisponiblesPorFechaHora(
            @RequestBody FechasDto fechas, @PathVariable Long idAerolinea
    ) {
        List<Avion> aviones = (List<Avion>) avionService.findAvionesByAerolineaFechaHora(fechas, idAerolinea);
        if (aviones.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay aviones disponibles para el vuelo seleccionado.");
        }
        return ResponseEntity.ok().body(aviones);
    }

    @PostMapping("obtenerTripulantesPorFechaHora")
    @ApiOperation(value = "Obtiene los aviones disponibles de una aerolinea segun horarios de vuelo")
    public ResponseEntity<?> findTripulantesByFechaHora(@RequestBody FechasDto fechas) {
        List<Tripulante> tripulantes = (List<Tripulante>) tripulanteService.findTripulantesByFechaHora(fechas);
        int contadorCapitan = 0;
        int contadorCopiloto = 0;
        int contadorIng = 0;
        int contadorTripulante = 0;
        if (tripulantes.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay tripulantes disponibles para el vuelo seleccionado.");
        } else {
            for (Tripulante tripulante : tripulantes) {
                switch (tripulante.getPuesto()) {
                    case "Capitan":
                        contadorCapitan++;
                        break;
                    case "Copiloto":
                        contadorCopiloto++;
                        break;
                    case "Ingeniero de vuelo":
                        contadorIng++;
                        break;
                    case "Tripulante de Cabina":
                        contadorTripulante++;
                        break;
                }
            }
            if (contadorCapitan == 0 || contadorCopiloto == 0
                    || contadorIng == 0 || contadorTripulante == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay tripulantes disponibles para el vuelo seleccionado.");
            }
        }
        return ResponseEntity.ok().body(tripulantes);
    }
    
    @PostMapping("crearVuelo")
    @ApiOperation(value = "Crea Nuevo Vuelo")
    public ResponseEntity<?> crearVuelo(@RequestBody Vuelo dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(vueloService.save(dto));
    }
}