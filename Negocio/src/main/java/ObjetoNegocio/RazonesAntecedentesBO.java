/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjetoNegocio;

import DTOS.SolicitudAdopcionDTO;

/**
 *
 * @author Josel
 */
public class RazonesAntecedentesBO implements IRazonesAntecedentesBO {

    @Override
    public boolean generaCartaCompromiso(SolicitudAdopcionDTO solicitud) {
        // Lógica: Generar documento legal si el usuario aceptó condiciones
        return solicitud.getRazones() != null && solicitud.getRazones().isAceptaSeguimiento();
    }

    @Override
    public String obtieneRazones(SolicitudAdopcionDTO solicitud) {
        if (solicitud != null && solicitud.getRazones() != null) {
            return solicitud.getRazones().getMotivoAdopcion();
        }
        return "";
    }

    @Override
    public String obtieneAntecedentes(SolicitudAdopcionDTO solicitud) {
        if (solicitud != null && solicitud.getRazones() != null) {
            return solicitud.getRazones().getAntecedentesMascotas();
        }
        return "";
    }
}
