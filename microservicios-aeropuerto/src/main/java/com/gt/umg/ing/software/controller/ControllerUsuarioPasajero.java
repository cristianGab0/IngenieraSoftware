/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.controller;

import com.gt.umg.ing.software.dto.request.UsuarioDto;
import com.gt.umg.ing.software.models.entity.Pasajero;
import com.gt.umg.ing.software.service.PasajeroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/usuario")
public class ControllerUsuarioPasajero {
//
    @Autowired
    private PasajeroService pasajeroService;
    
    @GetMapping("/existePasaporte/{noPasaporte}")
    @ApiOperation(value = "Retorna si existe el pasaporte")
    public ResponseEntity<Boolean> existePasaporte(@PathVariable Long noPasaporte) {
        Optional<Pasajero> o = pasajeroService.findById(noPasaporte);

        return ResponseEntity.ok(o.isPresent());
    }

    @PostMapping("crearPasajero")
    @ApiOperation(value = "Crea el usuario pasajero")
    public ResponseEntity<?> crearUsuarioPasajero(@Valid @RequestBody UsuarioDto user, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        Boolean existePasaporte = existePasaporte(user.getNoPasaporte()).getBody();
        if (existePasaporte) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("El número de pasaporte ya existe.");
        } else {
            return pasajeroService.crearUsuarioPasajero(user);
        }
    }

    public ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errores);
    }
}
