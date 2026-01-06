package br.com.alura.service.http;

public class AgenciaHttp {

    private String nome;
    private String razaoSocial;
    private String cnpj;
    private SituacaoCadastralEnum situacaoCadastral;

    public AgenciaHttp(String nome, String razaoSocial, String cnpj, String situacaoCadastral ){
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.situacaoCadastral = SituacaoCadastralEnum.valueOf(situacaoCadastral);
    }

    public String getNome() {
        return nome;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public String getCnpj() {
        return cnpj;
    }
    public SituacaoCadastralEnum getSituacaoCadastral() {
        return situacaoCadastral;
    }
}
