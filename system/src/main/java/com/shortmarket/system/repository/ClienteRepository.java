package com.shortmarket.system.repository;

import com.shortmarket.system.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    List<Cliente> findByNome(String nome);

    List<Cliente> findByDoc(String doc);

    List<Cliente> findByEndereco(String endereco);

    List<Cliente> findByTelefone(String telefone);
}
