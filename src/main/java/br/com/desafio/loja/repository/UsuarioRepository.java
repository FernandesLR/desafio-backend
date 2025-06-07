package br.com.desafio.loja.repository;

import br.com.desafio.loja.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    private final List<Usuario> usuarios = new ArrayList<>();

    public void cadastrar(Usuario usuario) {
        usuarios.add(usuario);
    }


}
