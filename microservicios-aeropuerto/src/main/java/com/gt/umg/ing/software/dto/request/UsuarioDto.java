package com.gt.umg.ing.software.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gt.umg.ing.software.models.entity.Pasajero;
import com.gt.umg.ing.software.models.entity.Usuario;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristian
 */
public class UsuarioDto {

    @Min(value = 1000000000000l, message = "El número de pasaporte debe de ser de 13 a 15 digitos")
    @Max(value = 999999999999999l, message = "El número de pasaporte debe de ser de 13 a 15 digitos")
    private Long noPasaporte;

    @Size(min = 20, max = 45, message = "El nombre debe ser de 20 a 45 letras")
    private String nombreCompleto;

    private Date fechaNacimiento;

    @NotEmpty(message = "ID debe ser un número de 20 dígitos")
    private String paisNacimiento;

    @NotEmpty(message = "ID debe ser un número de 20 dígitos")
    @Email(message = "El correo debe ser válido")
    private String correoElectronico;

    @Min(value = 1, message = "El número de télefono debe de ser de 8 digitos")
    @Max(value = 999, message = "El número de télefono debe de ser de 8 digitos")
    private int codigoArea;

    @Min(value = 10000000, message = "El número de télefono debe de ser de 8 digitos")
    @Max(value = 99999999, message = "El número de télefono debe de ser de 8 digitos")
    private int numeroTelefono;

    @Min(value = 10000000, message = "El número de télefono de emergencias  debe de ser de 8 digitos")
    @Max(value = 99999999, message = "El número de télefono de emergencias  debe de ser de 8 digitos")
    private int numeroEmergencias;

    @NotEmpty(message = "ID debe ser un número de 20 dígitos")
    private String direccion;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$",message= "El formato de la contraseña debe incluir al menos una letra mayúscula, un carácter especial y un número")
    private String password;

    public UsuarioDto() {
    }

    public UsuarioDto(Long noPasaporte, String nombreCompleto, Date fechaNacimiento, String paisNacimiento, String correoElectronico, Integer codigoArea, int numeroTelefono, int numeroEmergencias, String direccion, String password) {
        this.noPasaporte = noPasaporte;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.paisNacimiento = paisNacimiento;
        this.correoElectronico = correoElectronico;
        this.codigoArea = codigoArea;
        this.numeroTelefono = numeroTelefono;
        this.numeroEmergencias = numeroEmergencias;
        this.direccion = direccion;
        this.password = password;
    }

    public Usuario usuarioDtoToUsuario() {
        Usuario user = new Usuario();
        user.setEmail(this.getCorreoElectronico());
        user.setNombre(this.getNombreCompleto());
        user.setPassword(this.getPassword());
        user.setUsuario(this.getDireccion());
        return user;
    }
    
    public Pasajero usuarioDtoToPasajero(){
        Pasajero pasajero = new Pasajero();
        pasajero.setCodigoAera(this.getCodigoArea());
        pasajero.setCorreoElectronico(this.getCorreoElectronico());
        pasajero.setDireccion(this.getDireccion());
        pasajero.setFechaNacimiento(this.getFechaNacimiento());
        pasajero.setNoPasaporte(this.getNoPasaporte());
        pasajero.setNombre(this.getNombreCompleto());
        pasajero.setNumeroEmergencia(this.getNumeroEmergencias());
        pasajero.setNumeroTelefono(this.getNumeroTelefono());
        pasajero.setPaisNacimiento(this.getPaisNacimiento());
        return pasajero;
    }

    public Long getNoPasaporte() {
        return noPasaporte;
    }

    public void setNoPasaporte(Long noPasaporte) {
        this.noPasaporte = noPasaporte;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Integer getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(Integer codigoArea) {
        this.codigoArea = codigoArea;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public int getNumeroEmergencias() {
        return numeroEmergencias;
    }

    public void setNumeroEmergencias(int numeroEmergencias) {
        this.numeroEmergencias = numeroEmergencias;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
