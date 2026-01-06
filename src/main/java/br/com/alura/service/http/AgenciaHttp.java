package br.com.alura.service.http;

public class AgenciaHttp {

    private String nome;
    private String cnpj;
    private String razaoSocial;
    private SituacaoCadastralEnum situacaoCadastral;

    public AgenciaHttp(String nome, String cnpj, String razaoSocial, String situacaoCadastral ){
        this.nome = nome;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
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
