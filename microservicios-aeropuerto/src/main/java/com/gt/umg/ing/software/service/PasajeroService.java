/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.service;

import com.gt.umg.ing.software.dto.request.UsuarioDto;
import com.gt.umg.ing.software.models.entity.Pasajero;
import com.gt.umg.ing.software.models.entity.Rol;
import com.gt.umg.ing.software.models.entity.Usuario;
import com.gt.umg.ing.software.models.repository.PasajeroRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cristian
 */
@Service
public class PasajeroService extends CommonService<Pasajero, Long, PasajeroRepository> {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = false, rollbackFor = Throwable.class)
    public ResponseEntity<?> crearUsuarioPasajero(UsuarioDto usuario) {
        try {
            Usuario userDb = usuario.usuarioDtoToUsuario();
            Pasajero pasajeroDb = usuario.usuarioDtoToPasajero();

            userDb.setPassword(passwordEncoder.encode(userDb.getPassword()));
            List<Rol> roles = new ArrayList<>();
            roles.add(new Rol(2));
            userDb.setRoles(roles);
            
            
            usuarioService.save(userDb);
            this.repository.save(pasajeroDb);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error al crear el usuario");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
