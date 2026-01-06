package br.com.alura.utils;

import br.com.alura.domain.Agencia;
import br.com.alura.domain.Endereco;
import br.com.alura.service.http.AgenciaHttp;

public class AgenciaFixture {

    public static AgenciaHttp criarAgenciaHttp(String status) {
        return new AgenciaHttp("Agencia Teste", "Razão social testes", "123", status);
    }

    public static Agencia criaAgencia() {
        Endereco endereco = new Endereco(1, "Rua Teste", "Logradouro Teste", "complemento teste", 1);
        return new Agencia(1, "Agencia Teste", "Razão social testes", "123", endereco );
    }

}
