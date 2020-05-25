package info.pablogiraldo.imgup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import info.pablogiraldo.imgup.entity.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {

}
