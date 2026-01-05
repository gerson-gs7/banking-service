package br.com.alura.service.http;
import br.com.alura.domain.Agencia;
import br.com.alura.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import br.com.alura.repository.AgenciaRepository;
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
    public void alterar(Agencia agencia){
        agenciaRepository.update("nome =?1, razaoSocial = ?2, cnpj = ?3 where id = ?4", agencia.getNome(), agencia.getRazaoSocial(), agencia.getCnpj(), agencia.getId());
    }

}
