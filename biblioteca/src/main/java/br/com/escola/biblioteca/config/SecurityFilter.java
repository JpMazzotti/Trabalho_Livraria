package br.com.escola.biblioteca.config;

import br.com.escola.biblioteca.repository.UsuarioRepository;
import br.com.escola.biblioteca.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        String tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            
            String subject = tokenService.getSubject(tokenJWT);
            
            UserDetails usuario = usuarioRepository.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    // Método para extrair o token do cabeçalho http. Padrão -> Authorization: Bearer <e aqui fica o token>
     
    private String recuperarToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        
        if (authorizationHeader != null) {

            // Remover o que não é token
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        
        return null;
    }
}


