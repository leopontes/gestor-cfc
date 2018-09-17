package br.com.cfc.gestor.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

	@Query("select ur.role.roleName from UserRole ur where ur.user.id = ?1")
	public Collection<String> getRoleNames(Long id);
}
