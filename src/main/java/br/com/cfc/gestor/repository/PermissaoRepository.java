package br.com.cfc.gestor.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Permissao;

@Repository
public interface PermissaoRepository extends CrudRepository<Permissao, Long>{

	@Query("select ur.permissao.role from UsuarioPermissao ur where ur.user.id = ?1")
	public Collection<String> getRoleNames(Long id);
}
