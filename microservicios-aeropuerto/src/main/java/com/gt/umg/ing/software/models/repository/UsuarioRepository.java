package com.gt.umg.ing.software.models.repository;

import com.gt.umg.ing.software.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cristian
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    public Usuario findByUsername(String username);
    
}
