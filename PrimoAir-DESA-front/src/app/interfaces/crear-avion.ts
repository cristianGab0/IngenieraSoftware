export module CrearVuelo {

    export interface Id {
        idAerolinea?: number;
        idAvion?: number;
    }

    export interface Avion {
        anio?: string;
        estado?: string;
        id?: Id;
    }

    export interface Tripulante {
        estado?: string;
        idTripulante?: number;
        nombre?: string;
        puesto?: string;
    }

    export interface Id2 {
        idVuelo?: number;
        noPasaporte?: number;
    }

    export interface Pasajero {
        codigoAera?: number;
        correoElectronico?: string;
        direccion?: string;
        fechaNacimiento?: Date;
        noPasaporte?: number;
        nombre?: string;
        numeroEmergencia?: number;
        numeroTelefono?: number;
        paisNacimiento?: string;
    }

    export interface VueloPasajero {
        claseVuelo?: string;
        equipaje?: string;
        estadoBoleto?: string;
        id: Id2;
        pagoExtra?: number;
        pasajero?: Pasajero;
        pesoEquipaje?: number;
        sillon?: number;
    }

    export interface CrearVueloData {
        usuarioAgrego?: string;
        aeropuertoLlegada?: number;
        aeropuertoSalida?: number;
        avion?: Avion;
        estado?: string;
        fecha_hora_llegada?: string;
        fecha_hora_salida?: string;
        idvuelo?: number;
        precioEconomica?: number;
        precioEjecutiva?: number;
        tripulantes?: Tripulante[];
        vueloPasajeros?: VueloPasajero[];
    }

}

