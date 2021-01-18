package br.com.estudos.clienteRestful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estudos.clienteRestful.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
