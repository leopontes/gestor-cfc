package br.com.cfc.gestor.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Usuario;
import br.com.cfc.gestor.repository.PermissaoRepository;
import br.com.cfc.gestor.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Resource
	private UsuarioRepository userRepository;
	
	@Resource
	private PermissaoRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Usuario[" + username + "] nao encontrado.");
		}
		
		Collection<String> roles = roleRepository.getRoleNames(user.getId());
		
		List<GrantedAuthority> grants = new ArrayList<GrantedAuthority>();
		
		if(roles != null) {
			for(String role : roles) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grants.add(authority);
			}
		}
		
		UserDetails userDetails = new User(user.getUsername(), user.getPassword(), grants);
		
		return userDetails;
	}
}
