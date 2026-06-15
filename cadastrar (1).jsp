package service;

import dao.UsuarioDAO;
import model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioService {
    private final UsuarioDAO dao = new UsuarioDAO();

    public void cadastrar(Usuario usuario) {
        validarCadastro(usuario);

        String senhaCriptografada = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
        usuario.setSenha(senhaCriptografada);

        dao.cadastrar(usuario);
    }

    public Usuario autenticar(String email, String senha) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail é obrigatório.");
        }

        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }

        Usuario usuario = dao.buscarPorEmail(email);

        if (usuario == null || !BCrypt.checkpw(senha, usuario.getSenha())) {
            throw new IllegalArgumentException("E-mail ou senha inválidos.");
        }

        return usuario;
    }

    private void validarCadastro(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail é obrigatório.");
        }

        if (!usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("Informe um e-mail válido.");
        }

        if (usuario.getSenha() == null || usuario.getSenha().trim().length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
        }
    }
}
