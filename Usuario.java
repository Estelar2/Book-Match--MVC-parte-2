package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Livro;
import service.LivroService;
import java.io.IOException;

@WebServlet("/livro")
public class LivroController extends HttpServlet {
    private final LivroService service = new LivroService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");
        try {
            if ("cadastrar".equals(acao)) {
                service.cadastrar(montarLivro(request));
                response.sendRedirect("listar.jsp?msg=cadastrado");
                return;
            }
            if ("atualizar".equals(acao)) {
                Livro livro = montarLivro(request);
                livro.setId(Integer.parseInt(request.getParameter("id")));
                service.atualizar(livro);
                response.sendRedirect("listar.jsp?msg=atualizado");
                return;
            }
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        try {
            if ("excluir".equals(acao)) {
                service.excluir(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("listar.jsp?msg=excluido");
                return;
            }
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    private Livro montarLivro(HttpServletRequest request) {
        Livro livro = new Livro();
        livro.setTitulo(request.getParameter("titulo"));
        livro.setAutor(request.getParameter("autor"));
        livro.setGenero(request.getParameter("genero"));
        livro.setAvaliacao(Double.parseDouble(request.getParameter("avaliacao")));
        livro.setDescricao(request.getParameter("descricao"));
        return livro;
    }
}
