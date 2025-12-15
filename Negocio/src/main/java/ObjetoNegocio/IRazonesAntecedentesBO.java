/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ObjetoNegocio;

import DTOS.RazonesAntecedentesDTO;
import DTOS.SolicitudAdopcionDTO;

/**
 *
 * @author Josel
 */
public interface IRazonesAntecedentesBO {

    boolean generaCartaCompromiso(SolicitudAdopcionDTO solicitud);

    String obtieneRazones(SolicitudAdopcionDTO solicitud);

    String obtieneAntecedentes(SolicitudAdopcionDTO solicitud);
}
