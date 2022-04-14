/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.dto.response;

import com.gt.umg.ing.software.models.entity.Pasajero;
import com.gt.umg.ing.software.models.entity.VueloPasajero;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Cristian
 */
public class PasajeroVueloDto {

    private Long no_pasaporte;
    private String nombre;
    private String edad;
    private String pais_nacimiento;
    private String correo_electronico;
    private Integer numero_telefono;

    public PasajeroVueloDto(Long no_pasaporte, String nombre, String edad, String pais_nacimiento, String correo_electronico, Integer numero_telefono) {
        this.no_pasaporte = no_pasaporte;
        this.nombre = nombre;
        this.edad = edad;
        this.pais_nacimiento = pais_nacimiento;
        this.correo_electronico = correo_electronico;
        this.numero_telefono = numero_telefono;
    }

    public PasajeroVueloDto(Pasajero p) {
        this.no_pasaporte = p.getNoPasaporte();
        this.nombre = p.getNombre();
        this.edad = obtenerEdad(p.getFechaNacimiento());
        this.pais_nacimiento = p.getPaisNacimiento();
        this.correo_electronico = p.getCorreoElectronico();
        this.numero_telefono = p.getNumeroTelefono();
    }

    public String obtenerEdad(Date fechaNacimiento) {
        LocalDate lDate= convertToLocalDateViaDate(fechaNacimiento);
        Period edad = Period.between(lDate, LocalDate.now());
        return String.format("%d años",edad.getYears());
    }

    public LocalDate convertToLocalDateViaDate(Date fecha){
        return new java.sql.Date(fecha.getTime()).toLocalDate();
    }
    
    public Long getNo_pasaporte() {
        return no_pasaporte;
    }

    public void setNo_pasaporte(Long no_pasaporte) {
        this.no_pasaporte = no_pasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPais_nacimiento() {
        return pais_nacimiento;
    }

    public void setPais_nacimiento(String pais_nacimiento) {
        this.pais_nacimiento = pais_nacimiento;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public Integer getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(Integer numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

}
