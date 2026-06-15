package controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Livro;
import service.LivroService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/livros/*")
public class LivroApiController extends HttpServlet {
    private final LivroService service = new LivroService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        configurarResposta(response);

        try {
            String pathInfo = request.getPathInfo();
            String genero = request.getParameter("genero");

            if (genero != null && !genero.trim().isEmpty()) {
                List<Livro> recomendacoes = service.recomendarPorGenero(genero);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(gson.toJson(recomendacoes));
                return;
            }

            if (pathInfo == null || "/".equals(pathInfo)) {
                List<Livro> livros = service.listar();
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(gson.toJson(livros));
                return;
            }

            int id = extrairId(pathInfo);
            Livro livro = service.buscarPorId(id);

            if (livro == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"erro\":\"Livro não encontrado.\"}");
                return;
            }

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(gson.toJson(livro));

        } catch (Exception e) {
            enviarErro(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        configurarResposta(response);

        try {
            Livro livro = gson.fromJson(request.getReader(), Livro.class);
            service.cadastrar(livro);

            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", "Livro cadastrado com sucesso.");

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(gson.toJson(resposta));

        } catch (Exception e) {
            enviarErro(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        configurarResposta(response);

        try {
            int id = extrairId(request.getPathInfo());

            Livro livro = gson.fromJson(request.getReader(), Livro.class);
            livro.setId(id);

            service.atualizar(livro);

            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", "Livro atualizado com sucesso.");

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(gson.toJson(resposta));

        } catch (Exception e) {
            enviarErro(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        configurarResposta(response);

        try {
            int id = extrairId(request.getPathInfo());

            service.excluir(id);

            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", "Livro excluído com sucesso.");

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(gson.toJson(resposta));

        } catch (Exception e) {
            enviarErro(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private int extrairId(String pathInfo) {
        if (pathInfo == null || "/".equals(pathInfo)) {
            throw new IllegalArgumentException("Informe o ID do livro na URL.");
        }

        String idTexto = pathInfo.replace("/", "");

        try {
            return Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        }
    }

    private void configurarResposta(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
    }

    private void enviarErro(HttpServletResponse response, int status, String mensagem) throws IOException {
        response.setStatus(status);

        Map<String, String> erro = new HashMap<>();
        erro.put("erro", mensagem);

        response.getWriter().write(gson.toJson(erro));
    }
}
