package br.com.desafio.loja.services;

import br.com.desafio.loja.model.Usuario;
import br.com.desafio.loja.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UsuarioServices {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServices(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean validarUsuario(Usuario usuario) {
        return validaEmail(usuario.getEmail()) && validaSenha(usuario.getSenha());
    }

    public void salvarUsuario(Usuario usuario) {
        if (validarUsuario(usuario)) {
            usuarioRepository.cadastrar(usuario);
        } else {
            throw new IllegalArgumentException("Usuário inválido");
        }
    }

    private boolean validaEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private boolean validaSenha(String senha) {
        return senha != null && senha.length() >= 5;
    }
}
