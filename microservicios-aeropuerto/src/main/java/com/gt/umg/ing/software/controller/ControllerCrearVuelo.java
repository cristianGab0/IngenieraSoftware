package com.gt.umg.ing.software.controller;

import com.gt.umg.ing.software.dto.request.FechasDto;
import com.gt.umg.ing.software.dto.response.IAvionDto;
import com.gt.umg.ing.software.models.entity.Aerolinea;
import com.gt.umg.ing.software.models.entity.Aeropuerto;
import com.gt.umg.ing.software.models.entity.Tripulante;
import com.gt.umg.ing.software.models.entity.Vuelo;
import com.gt.umg.ing.software.service.AerolineaService;
import com.gt.umg.ing.software.service.AeropuertoService;
import com.gt.umg.ing.software.service.AvionService;
import com.gt.umg.ing.software.service.TripulanteService;
import com.gt.umg.ing.software.service.VueloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
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
//@RequestMapping("/crear-vuelo")
public class ControllerCrearVuelo {
    
    @Autowired
    private AvionService avionService;

    @Autowired
    private VueloService vueloService;

    @Autowired
    private AeropuertoService aeropuertoService;

    @Autowired
    private TripulanteService tripulanteService;

    @Autowired
    private AerolineaService aerolineaService;

//    @Secured("ROLE_ADMIN_AEROLINEA")
    @GetMapping("obtenerAerolineas")
    public ResponseEntity<?> getAerolineas() {
        List<Aerolinea> aerolinea =(List<Aerolinea>) aerolineaService.findAll();

        aerolinea = aerolinea.stream().peek(a->{
            a.setAeropuertos(new ArrayList());
            a.setAvions(new ArrayList());
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(aerolinea);
    }

//    @Secured("ROLE_ADMIN_AEROLINEA")
    @GetMapping("hayAvionesDisponiblesByAerolinea/{idAerolinea}")
    @ApiOperation(value = "Valida los aviones y aeropuertos disponibles por aerolinea")
    public ResponseEntity<?> hayAvionesDisponibles(@PathVariable int idAerolinea) {
        List<IAvionDto> aviones = (List<IAvionDto>) avionService.findAvionesByNombreAerolinea(idAerolinea);
        if (aviones.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede crear un vuelo porque la aerolínea no cuenta con aviones disponibles.");
        }
        List<Aeropuerto> aeropuertos = (List<Aeropuerto>) aeropuertoService.getAeropuertoByAerolinea(aviones.get(0).getIdAerolinea());

        aeropuertos = aeropuertos.stream().peek(a -> a.setAerolineas(new ArrayList<>())).collect(Collectors.toList());

        if (aeropuertos.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron aeropuertos autorizados para la aerolínea.");
        }
        return ResponseEntity.ok().body(aeropuertos);
    }

//    @Secured("ROLE_ADMIN_AEROLINEA")
    @PostMapping("obtenerAvionesPorFechaHora/{idAerolinea}")
    @ApiOperation(value = "Obtiene los aviones disponibles de una aerolinea segun horarios de vuelo")
    public ResponseEntity<?> obtenerAvionesDisponiblesPorFechaHora(
            @RequestBody FechasDto fechas, @PathVariable Long idAerolinea
    ) {
        List<IAvionDto> aviones = (List<IAvionDto>) avionService.findAvionesByAerolineaFechaHora(fechas, idAerolinea);
        if (aviones.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay aviones disponibles para el vuelo seleccionado.");
        }
        return ResponseEntity.ok().body(aviones);
    }

//    @Secured("ROLE_ADMIN_AEROLINEA")
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
                    case "Tripulante de cabina":
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

//    @Secured("ROLE_ADMIN_AEROLINEA")
    @PostMapping("crearVuelo")
    @ApiOperation(value = "Crea Nuevo Vuelo")
    public ResponseEntity<?> crearVuelo(@RequestBody Vuelo dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vueloService.save(dto));
    }
}
