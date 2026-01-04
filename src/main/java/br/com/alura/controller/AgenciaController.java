package br.com.alura.controller;

import org.jboss.resteasy.reactive.RestResponse;

import br.com.alura.domain.Agencia;
import br.com.alura.service.http.AgenciaService;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;

@Path("/agencias")
public class AgenciaController {

    private AgenciaService agenciaService;

    AgenciaController (AgenciaService agenciaService) {
        this.agenciaService = agenciaService;
    }

    public RestResponse<Void> cadastrar(Agencia agencia, @Context UriInfo uriInfo){
        agenciaService.cadastrar(agencia);
        return RestResponse.created(uriInfo.getAbsolutePath());
    }
}
