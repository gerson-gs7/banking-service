package br.com.alura.service;
import br.com.alura.domain.Agencia;
import br.com.alura.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import br.com.alura.repository.AgenciaRepository;
import br.com.alura.service.http.AgenciaHttp;
import br.com.alura.service.http.SituacaoCadastralEnum;
import br.com.alura.service.http.SituacaoCadastralHttpService;
import io.quarkus.logging.Log;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AgenciaService {
    
    @Inject
    @RestClient // pra injetar servicos http usamos o anotation RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    private final AgenciaRepository agenciaRepository;

    AgenciaService(AgenciaRepository agenciaRepository){
        this.agenciaRepository = agenciaRepository;
    }

    public void cadastrar(Agencia agencia) {
        AgenciaHttp agenciaHttp =
        situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());
        if(agenciaHttp != null && agenciaHttp.getSituacaoCadastral().equals(SituacaoCadastralEnum.ATIVO)){
            agenciaRepository.persist(agencia);
            Log.info("A agencia com o CNPJ "+ agencia.getCnpj() + "foi cadastrada");
        }else {
            throw new AgenciaNaoAtivaOuNaoEncontradaException();
        }
    }

    public Agencia buscarPorId(Long id) {
        return agenciaRepository.findById(id);
    }
    public void deletar(Long id) {
        agenciaRepository.deleteById(id);
        Log.info("A agencia com o ID "+ id + " foi deletada");
    }
    public void alterar(Agencia agencia) {
    Agencia entidadeExistente = agenciaRepository.findById(agencia.getId().longValue());
    
    if (entidadeExistente != null) {
        entidadeExistente.setNome(agencia.getNome());
        entidadeExistente.setRazaoSocial(agencia.getRazaoSocial());
        entidadeExistente.setCnpj(agencia.getCnpj());
        entidadeExistente.setEndereco(agencia.getEndereco());
        Log.info("A agencia de ID "+ agencia.getId() + " foi alterada");
    } else {
        throw new IllegalStateException("Agência com ID " + agencia.getId() + " não encontrada");
    }
}

}
