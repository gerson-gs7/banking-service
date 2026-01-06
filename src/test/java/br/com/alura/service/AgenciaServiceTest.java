package br.com.alura.service;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.alura.domain.Agencia;
import br.com.alura.domain.Endereco;
import br.com.alura.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import br.com.alura.repository.AgenciaRepository;
import br.com.alura.service.http.SituacaoCadastralHttpService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class AgenciaServiceTest {
    
    @InjectMock
    private AgenciaRepository agenciaRepository;

    @Inject
    @InjectMock
    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    @Inject
    private AgenciaService agenciaService;

    @Test
    public void deveNaoCadastrarQuandoClientRetornarNull() {
        
        Mockito.when(situacaoCadastralHttpService.buscarPorCnpj("123")).thenReturn(null);
        Assertions.assertThrows(AgenciaNaoAtivaOuNaoEncontradaException.class, () -> agenciaService.cadastrar(criarAgencia())) ;

        Mockito.verify(agenciaRepository, Mockito.never()).persist(criarAgencia());
    }
    private Agencia criarAgencia(){
            Endereco endereco = new Endereco(1, "", "", "", null);
            return new Agencia(1, "","","",endereco);
        }
}
