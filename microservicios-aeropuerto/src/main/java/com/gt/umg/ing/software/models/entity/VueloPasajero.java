package com.gt.umg.ing.software.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * VueloPasajero generated by hbm2java
 */
@Entity
@Table(name = "vuelo_pasajero",
        schema = "public"
)
public class VueloPasajero implements java.io.Serializable {

    private VueloPasajeroId id;
    private Pasajero pasajero;
//     private long pasajero;
    private Vuelo vuelo;
    private String equipaje;
    private BigDecimal pesoEquipaje;
    private int sillon;
    private String estadoBoleto;
    private String claseVuelo;
    private BigDecimal pagoExtra;
    private Date fechaHoraCreacion;
    private String usuarioAgrego;
    private String ipUsuario;

    public VueloPasajero() {
    }

    public VueloPasajero(VueloPasajeroId id, Pasajero pasajero, Vuelo vuelo) {
        this.id = id;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
    }

    public VueloPasajero(VueloPasajeroId id, Pasajero pasajero, Vuelo vuelo, String equipaje, BigDecimal pesoEquipaje, int sillon, String estadoBoleto, String claseVuelo, BigDecimal pagoExtra, Date fechaHoraCreacion, String usuarioAgrego, String ipUsuario) {
        this.id = id;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.equipaje = equipaje;
        this.pesoEquipaje = pesoEquipaje;
        this.sillon = sillon;
        this.estadoBoleto = estadoBoleto;
        this.claseVuelo = claseVuelo;
        this.pagoExtra = pagoExtra;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.usuarioAgrego = usuarioAgrego;
        this.ipUsuario = ipUsuario;
    }

    @PrePersist
    public void prePrersis() {
        this.fechaHoraCreacion = new Date();
    }
    
    @EmbeddedId

    @AttributeOverrides({
        @AttributeOverride(name = "idVuelo", column = @Column(name = "id_vuelo", nullable = false)),
        @AttributeOverride(name = "noPasaporte", column = @Column(name = "no_pasaporte", nullable = false))})
    public VueloPasajeroId getId() {
        return this.id;
    }

    public void setId(VueloPasajeroId id) {
        this.id = id;
    }

    @ManyToOne()
    @JoinColumn(name = "no_pasaporte", nullable = false, insertable = false, updatable = false)
    public Pasajero getPasajero() {
        return this.pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    @JsonIgnore()
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vuelo", nullable = false, insertable = false, updatable = false)
    public Vuelo getVuelo() {
        return this.vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    @Column(name = "equipaje", length = 45)
    public String getEquipaje() {
        return this.equipaje;
    }

    public void setEquipaje(String equipaje) {
        this.equipaje = equipaje;
    }

    @Column(name = "peso_equipaje", precision = 6)
    public BigDecimal getPesoEquipaje() {
        return this.pesoEquipaje;
    }

    public void setPesoEquipaje(BigDecimal pesoEquipaje) {
        this.pesoEquipaje = pesoEquipaje;
    }

//    @ManyToOne(fetch=FetchType.LAZY)
    @Column(name = "sillon")
    public int getSillon() {
        return this.sillon;
    }

    public void setSillon(int sillon) {
        this.sillon = sillon;
    }

    @Column(name = "estado_boleto", length = 45)
    public String getEstadoBoleto() {
        return this.estadoBoleto;
    }

    public void setEstadoBoleto(String estadoBoleto) {
        this.estadoBoleto = estadoBoleto;
    }

    @Column(name = "clase_vuelo", length = 15)
    public String getClaseVuelo() {
        return this.claseVuelo;
    }

    public void setClaseVuelo(String claseVuelo) {
        this.claseVuelo = claseVuelo;
    }

    @Column(name = "pago_extra", precision = 7)
    public BigDecimal getPagoExtra() {
        return pagoExtra;
    }

    public void setPagoExtra(BigDecimal pagoExtra) {
        this.pagoExtra = pagoExtra;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_creacion", length = 29)
    public Date getFechaHoraCreacion() {
        return this.fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Date fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    @Column(name = "usuario_agrego")
    public String getUsuarioAgrego() {
        return this.usuarioAgrego;
    }

    public void setUsuarioAgrego(String usuarioAgrego) {
        this.usuarioAgrego = usuarioAgrego;
    }

    @Column(name = "ip_usuario")
    public String getIpUsuario() {
        return this.ipUsuario;
    }

    public void setIpUsuario(String ipUsuario) {
        this.ipUsuario = ipUsuario;
    }

}
