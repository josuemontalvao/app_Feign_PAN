package com.josue.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.josue.app.model.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {

}
