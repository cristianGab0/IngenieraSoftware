package com.gt.umg.ing.software.models.entity;
// Generated 17/03/2022 09:17:45 PM by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Avion generated by hbm2java
 */
@Entity
@Table(name = "avion",
         schema = "public"
)
public class Avion implements java.io.Serializable {

    private AvionId id;
    private Aerolinea aerolinea;
    private CatalogoAvion catalogoAvion;
    private String anio;
    private Character estado;
    private List<Vuelo> vuelos;

    public Avion() {
    }

    public Avion(AvionId id, Aerolinea aerolinea, CatalogoAvion catalogoAvion) {
        this.id = id;
        this.aerolinea = aerolinea;
        this.catalogoAvion = catalogoAvion;
    }

    public Avion(AvionId id, Aerolinea aerolinea, CatalogoAvion catalogoAvion, String anio, Character estado,List<Vuelo> vuelos) {
        this.id = id;
        this.aerolinea = aerolinea;
        this.catalogoAvion = catalogoAvion;
        this.anio = anio;
        this.estado = estado;
        this.vuelos = vuelos;
    }

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "idAvion", column = @Column(name = "id_avion", nullable = false)),
        @AttributeOverride(name = "idAerolinea", column = @Column(name = "id_aerolinea", nullable = false))})
    public AvionId getId() {
        return this.id;
    }

    public void setId(AvionId id) {
        this.id = id;
    }
    @JsonIgnore()
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aerolinea", nullable = false, insertable = false, updatable = false)
    public Aerolinea getAerolinea() {
        return this.aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }
    
    @JsonIgnore()
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_avion", nullable = false, insertable = false, updatable = false)
    public CatalogoAvion getCatalogoAvion() {
        return this.catalogoAvion;
    }

    public void setCatalogoAvion(CatalogoAvion catalogoAvion) {
        this.catalogoAvion = catalogoAvion;
    }

    @Column(name = "anio", length = 45)
    public String getAnio() {
        return this.anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    @Column(name = "estado", length = 1)
    public Character getEstado() {
        return this.estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }
    
    @JsonIgnore()
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "avion")
    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    
}
