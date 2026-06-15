package controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import security.JwtUtil;
import service.UsuarioService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/login")
public class LoginController extends HttpServlet {
    private final UsuarioService service = new UsuarioService();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        configurarResposta(response);

        try {
            LoginRequest login = gson.fromJson(request.getReader(), LoginRequest.class);

            Usuario usuario = service.autenticar(login.email, login.senha);
            String token = JwtUtil.gerarToken(usuario.getEmail());

            Map<String, Object> resposta = new HashMap<>();
            resposta.put("mensagem", "Login realizado com sucesso.");
            resposta.put("token", token);
            resposta.put("usuario", usuario.getNome());

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(gson.toJson(resposta));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            Map<String, String> erro = new HashMap<>();
            erro.put("erro", e.getMessage());

            response.getWriter().write(gson.toJson(erro));
        }
    }

    private void configurarResposta(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
    }

    private static class LoginRequest {
        String email;
        String senha;
    }
}
