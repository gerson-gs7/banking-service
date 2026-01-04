package br.com.alura.service.http;

public class AgenciaHttp {
    private String nome;
    private String cnpj;
    private String razaoSocial;
    private SituacaoCadastralEnum situacaoCastral;

    public String getNome() {
        return nome;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public String getCnpj() {
        return cnpj;
    }
    public SituacaoCadastralEnum getSituacaoCastral() {
        return situacaoCastral;
    }
}
