package br.com.estudos.clienteRestful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estudos.clienteRestful.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByUsername(String username);
	boolean existsByUsernameAndPassword(String username, String password);
	boolean existsByUsername(String username);

}
