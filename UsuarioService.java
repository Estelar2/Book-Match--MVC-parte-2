package dao;

import model.Livro;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    public void cadastrar(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, genero, avaliacao, descricao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getGenero());
            stmt.setDouble(4, livro.getAvaliacao());
            stmt.setString(5, livro.getDescricao());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar livro: " + e.getMessage(), e);
        }
    }

    public List<Livro> listar() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros ORDER BY avaliacao DESC, titulo ASC";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) livros.add(mapear(rs));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar livros: " + e.getMessage(), e);
        }
        return livros;
    }

    public Livro buscarPorId(int id) {
        String sql = "SELECT * FROM livros WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livro: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Livro> recomendarPorGenero(String genero) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros WHERE genero = ? ORDER BY avaliacao DESC, titulo ASC";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, genero);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) livros.add(mapear(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao recomendar livros: " + e.getMessage(), e);
        }
        return livros;
    }

    public void atualizar(Livro livro) {
        String sql = "UPDATE livros SET titulo=?, autor=?, genero=?, avaliacao=?, descricao=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getGenero());
            stmt.setDouble(4, livro.getAvaliacao());
            stmt.setString(5, livro.getDescricao());
            stmt.setInt(6, livro.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar livro: " + e.getMessage(), e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM livros WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir livro: " + e.getMessage(), e);
        }
    }

    private Livro mapear(ResultSet rs) throws Exception {
        Livro livro = new Livro();
        livro.setId(rs.getInt("id"));
        livro.setTitulo(rs.getString("titulo"));
        livro.setAutor(rs.getString("autor"));
        livro.setGenero(rs.getString("genero"));
        livro.setAvaliacao(rs.getDouble("avaliacao"));
        livro.setDescricao(rs.getString("descricao"));
        return livro;
    }
}
