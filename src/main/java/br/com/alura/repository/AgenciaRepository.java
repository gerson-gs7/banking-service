package br.com.alura.repository;

import br.com.alura.domain.Agencia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped //para o quarkus poder injetar recursos entre classes 
public class AgenciaRepository implements PanacheRepository<Agencia> {

}
