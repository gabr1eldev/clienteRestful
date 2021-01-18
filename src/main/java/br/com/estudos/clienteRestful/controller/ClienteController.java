package br.com.estudos.clienteRestful.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.clienteRestful.entity.Cliente;
import br.com.estudos.clienteRestful.repository.ClienteRepository;

@RestController
@RequestMapping({"/api/cliente"})
public class ClienteController {
	
	private final ClienteRepository clienteRepository;

	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	
	@PostMapping
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
		for(int pos = 0; pos < cliente.getContatos().size(); pos++) {
			cliente.getContatos().get(pos).setContatoCliente(cliente);
		}
		
		for(int pos = 0; pos < cliente.getEndereco().size(); pos++) {
			cliente.getEndereco().get(pos).setEnderecoCliente(cliente);
		}
		
		Cliente salvo = clienteRepository.save(cliente);
		
		return new ResponseEntity<Cliente>(salvo,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> procurarTodos() {
		
		List<Cliente> cliente = clienteRepository.findAll();
		
		return new ResponseEntity<List<Cliente>>(cliente,HttpStatus.OK);
	}

}
