package com.shortmarket.system.repository;

import com.shortmarket.system.entities.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    List<Produto> findByName(String name);

    List<Produto> findByUnidade(String unidade);
    List<Produto> findByNcm(int ncm);
    List<Produto> findByValor(long valor);

}
