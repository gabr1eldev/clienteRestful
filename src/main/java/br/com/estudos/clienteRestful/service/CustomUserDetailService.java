package br.com.estudos.clienteRestful.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.estudos.clienteRestful.entity.Usuario;
import br.com.estudos.clienteRestful.repository.UsuarioRepository;

@Component
public class CustomUserDetailService implements UserDetailsService{
	
	private final UsuarioRepository usuarioRepository;
	
	public CustomUserDetailService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = Optional.ofNullable(usuarioRepository.findByUsername(username))
		.orElseThrow(() -> new UsernameNotFoundException("Login não encontrado"))  ;
		
		List<GrantedAuthority> authorityListAdmin	=	AuthorityUtils.createAuthorityList("ROLE_USER","ROLE_ADMIN");
		List<GrantedAuthority> authorityListUser	=	AuthorityUtils.createAuthorityList("ROLE_USER");
		
		return new org.springframework.security.core.userdetails.User(
				usuario.getUsername(),
				usuario.getUsername(),
				usuario.isAdmin() ? authorityListAdmin : authorityListUser);
	}

}
