/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.umg.ing.software.service;

import com.gt.umg.ing.software.dto.request.VuelosResumenDto;
import com.gt.umg.ing.software.dto.response.IDetalleVueloPasajero;
import com.gt.umg.ing.software.dto.response.IRepoListadoVuelos;
import com.gt.umg.ing.software.models.entity.Vuelo;
import com.gt.umg.ing.software.models.repository.VueloRepository;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cristian
 */
@Service
public class VueloService extends CommonService<Vuelo, Integer, VueloRepository> {
    
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Vuelo crearVuelo(Vuelo vuelo, String ipUsuario){
        vuelo.setIpUsuario(ipUsuario);
        return this.repository.save(vuelo);
    }

    public Iterable<Vuelo> getVuelosFechaInicial(Date fecha) {
        return this.repository.getVuelosFechaInicial(fecha.toGMTString());
    }

    public List<Vuelo> limpiarLista(List<Vuelo> vuelosInicial, List<Vuelo> vuelosFinales) {
        return vuelosInicial.stream().filter(v -> !vuelosFinales.contains(v)).collect(Collectors.toList());
    }

    public List<Vuelo> enlazarSiguienteVuelo(List<Vuelo> vuelos, int aeropuertoLlegada) {
        return vuelos.stream()
                .filter(v -> v.getAeropuertoSalida() == aeropuertoLlegada)
                .collect(Collectors.toList());
    }

    public List<VuelosResumenDto> crearRespuestaVuelosEscala(List<List<Vuelo>> vuelosRespuesta) {
        List<VuelosResumenDto> respuestaList = new ArrayList<>();

        for (List<Vuelo> list : vuelosRespuesta) {
            List<Integer> idsVuelos = list.stream().map(v -> v.getIdvuelo()).collect(Collectors.toList());
            Date fechaHoraSalida = list.get(0).getFecha_hora_salida();
            Date fechaHoraLlegada = list.get(list.size() - 1).getFecha_hora_llegada();
            List<BigDecimal> preciosEconomica = list.stream().map(v -> v.getPrecioEconomica()).collect(Collectors.toList());
            List<BigDecimal> preciosEjecutiva = list.stream().map(v -> v.getPrecioEjecutiva()).collect(Collectors.toList());
            double precioEconomica = 0;
            double precioEjecutiva = 0;
            for (BigDecimal eco : preciosEconomica) {
                precioEconomica += eco.doubleValue();
            }
            for (BigDecimal eje : preciosEjecutiva) {
                precioEjecutiva += eje.doubleValue();
            }
            switch (list.size()) {
                case 2://75%
                    precioEconomica = precioEconomica * 0.75;
                    precioEjecutiva = precioEjecutiva * 0.75;
                    break;
                case 3://50%
                    precioEconomica = precioEconomica * 0.50;
                    precioEjecutiva = precioEjecutiva * 0.50;
                    break;
            }
            String tiempoTotal = obtenerTiempoTotal(fechaHoraSalida, fechaHoraLlegada);
            VuelosResumenDto vuelo = new VuelosResumenDto(idsVuelos, fechaHoraSalida, fechaHoraLlegada, tiempoTotal, precioEconomica, precioEjecutiva);
            respuestaList.add(vuelo);
        }
        return respuestaList;
    }

    public IDetalleVueloPasajero obtenerDetalleVuelo(Integer idVuelo){
        return this.repository.obtenerDetalleVuelo(idVuelo);
    }
    
    public int getTieneVuelosUsuario(Date fechaSalida, Date fechaLlegada, Long idUsuario) {
        return this.repository.getTieneVuelosUsuario(idUsuario, fechaSalida.toGMTString(), fechaLlegada.toGMTString());
    }

    public Iterable<Vuelo> getVuelosAbordar() {
        return this.repository.getVuelosAbordar();
    }

    public Iterable<IRepoListadoVuelos> getVuelosByFechaHoraSalidaLlegada(Date fechaSalida, Date fechaLlegada) throws ParseException {
        return this.repository.getVuelosByFechaHoraSalidaLlegada(fechaSalida, fechaLlegada);
    }

    public Optional<IRepoListadoVuelos> getDetalleVuelo(int idVuelo){
        return this.repository.getDetalleVuelo(idVuelo);
    }
    
    public String obtenerTiempoTotal(Date fechaHoraSalida, Date fechaHoraLlegada) {
        long dif = fechaHoraLlegada.getTime() - fechaHoraSalida.getTime();
        long diffHoras = (dif / (1000 * 60 * 60)) % 24;
        long diffSegundos = (dif / 1000) % 60;
        long diffMinitos = (dif / (1000 * 60)) % 60;
        return diffHoras + " horas, " + diffMinitos + " minutos, " + diffSegundos + " segundos";
    }

    public List<Vuelo> armarEscalas(List<Vuelo> vuelosBd, Long aeropuertoSalida, Long aeropuertoLlegada) {
        List<Vuelo> vuelosSalida = vuelosBd.stream()
                .filter(v -> v.getAeropuertoSalida() == aeropuertoSalida)
                .collect(Collectors.toList());

        if (vuelosSalida.size() == 0) {
            return new ArrayList<>();
        }

        vuelosBd = limpiarLista(vuelosBd, vuelosSalida);

        List<Vuelo> escalas = new ArrayList<>();

        Vuelo salida = vuelosSalida.get(0);
        escalas.add(salida);

        List<Vuelo> vuelosSiguientes = enlazarSiguienteVuelo(vuelosBd, salida.getAeropuertoLlegada());
        for (Vuelo vueloSiguiente : vuelosSiguientes) {
            if (aeropuertoLlegada == vueloSiguiente.getAeropuertoLlegada()) {
                escalas.add(vueloSiguiente);
                return escalas;
            } else {
                List<Vuelo> ultimaEscala = enlazarSiguienteVuelo(vuelosBd, vueloSiguiente.getAeropuertoLlegada());
                for (Vuelo ultimo : ultimaEscala) {
                    if (ultimaEscala != null && aeropuertoLlegada == ultimo.getAeropuertoLlegada()) {
                        escalas.add(vueloSiguiente);
                        escalas.add(ultimo);
                        return escalas;
                    }
                }

            }
        }
        return escalas;
    }
}
