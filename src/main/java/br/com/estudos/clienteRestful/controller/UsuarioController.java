package br.com.estudos.clienteRestful.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.clienteRestful.entity.Usuario;
import br.com.estudos.clienteRestful.repository.UsuarioRepository;

@RestController
@RequestMapping({"/api/usuario"})
public class UsuarioController {
	
	private final UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
		String username = usuario.getUsername();
		
		
		boolean login = usuarioRepository.existsByUsername(username);
		
		if(login) {
			return new ResponseEntity("Login já existe",HttpStatus.OK);
			
		} else {
			
			Usuario salvo = usuarioRepository.save(usuario);
			
			return new ResponseEntity<Usuario>(salvo,HttpStatus.OK);
		}	
		
	}
	
	

}
