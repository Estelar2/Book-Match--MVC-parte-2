package service;

import dao.LivroDAO;
import model.Livro;
import java.util.List;

public class LivroService {
    private final LivroDAO dao = new LivroDAO();

    public void cadastrar(Livro livro) {
        validar(livro);
        dao.cadastrar(livro);
    }

    public List<Livro> listar() { return dao.listar(); }
    public Livro buscarPorId(int id) { return dao.buscarPorId(id); }
    public List<Livro> recomendarPorGenero(String genero) { return dao.recomendarPorGenero(genero); }

    public void atualizar(Livro livro) {
        validar(livro);
        dao.atualizar(livro);
    }

    public void excluir(int id) { dao.excluir(id); }

    private void validar(Livro livro) {
        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) throw new IllegalArgumentException("O título é obrigatório.");
        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty()) throw new IllegalArgumentException("O autor é obrigatório.");
        if (livro.getGenero() == null || livro.getGenero().trim().isEmpty()) throw new IllegalArgumentException("O gênero é obrigatório.");
        if (livro.getAvaliacao() < 0 || livro.getAvaliacao() > 10) throw new IllegalArgumentException("A avaliação deve estar entre 0 e 10.");
        if (livro.getDescricao() == null || livro.getDescricao().trim().isEmpty()) throw new IllegalArgumentException("A descrição é obrigatória.");
    }
}
