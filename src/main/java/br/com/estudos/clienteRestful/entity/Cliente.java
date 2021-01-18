package br.com.estudos.clienteRestful.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String cpf;	
	private String nome;
	private String rg;
	private Integer idade;
	
	@OneToMany(mappedBy = "contatoCliente" , cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Contato> contatos;
	
	@OneToMany(mappedBy = "enderecoCliente" , cascade = CascadeType.ALL,  orphanRemoval = true)
	private List<Endereco> endereco;
	
	private LocalDate dataCadastro;
	
	@PrePersist
	void preDataCadastro() {
		setDataCadastro(LocalDate.now());
		
	}

}
