package controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import service.UsuarioService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/usuarios")
public class UsuarioController extends HttpServlet {
    private final UsuarioService service = new UsuarioService();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        configurarResposta(response);

        try {
            Usuario usuario = gson.fromJson(request.getReader(), Usuario.class);
            service.cadastrar(usuario);

            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", "Usuário cadastrado com sucesso.");

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(gson.toJson(resposta));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());

            response.getWriter().write(gson.toJson(erro));
        }
    }

    private void configurarResposta(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
    }
}
