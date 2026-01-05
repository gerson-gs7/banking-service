package br.com.alura.service.http;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.alura.domain.Agencia;
import br.com.alura.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AgenciaService {
    
    @Inject
    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    private List<Agencia> agencias = new ArrayList<>();

    public void cadastrar(Agencia agencia) {
        AgenciaHttp agenciaHttp =
        situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());
        if(agenciaHttp != null && agenciaHttp.getSituacaoCadastral().equals(SituacaoCadastralEnum.ATIVO)){
            agencias.add(agencia);
        }else {
            throw new AgenciaNaoAtivaOuNaoEncontradaException();
        }
    }

    public Agencia buscarPorId(Integer id) {
        return agencias.stream().filter(agencia -> agencia.getId().equals(id)).toList().getFirst();
    }
    public void deletar(Integer id) {
        agencias.removeIf(agencia -> agencia.getId().equals(id));
    }
    public void alterar(Agencia agencia){
        deletar(agencia.getId());
        cadastrar(agencia);
    }

}
