package br.com.alura.service;
import br.com.alura.domain.Agencia;
import br.com.alura.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import br.com.alura.repository.AgenciaRepository;
import br.com.alura.service.http.AgenciaHttp;
import br.com.alura.service.http.SituacaoCadastralEnum;
import br.com.alura.service.http.SituacaoCadastralHttpService;

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
        }else {
            throw new AgenciaNaoAtivaOuNaoEncontradaException();
        }
    }

    public Agencia buscarPorId(Long id) {
        return agenciaRepository.findById(id);
    }
    public void deletar(Long id) {
        agenciaRepository.deleteById(id);
    }
    // public void alterar(Agencia agencia){
    //     agenciaRepository.update("nome =?1, razaoSocial = ?2, cnpj = ?3 where id = ?4", agencia.getNome(), agencia.getRazaoSocial(), agencia.getCnpj(), agencia.getId());
    // }
    public void alterar(Agencia agencia) {
    // Busca a entidade pelo ID
    Agencia entidadeExistente = agenciaRepository.findById(agencia.getId().longValue());
    
    if (entidadeExistente != null) {
        // Atualiza os atributos desejados
        entidadeExistente.setNome(agencia.getNome());
        entidadeExistente.setRazaoSocial(agencia.getRazaoSocial());
        entidadeExistente.setCnpj(agencia.getCnpj());
        entidadeExistente.setEndereco(agencia.getEndereco());
    } else {
        throw new IllegalStateException("Agência com ID " + agencia.getId() + " não encontrada");
    }
}

}
