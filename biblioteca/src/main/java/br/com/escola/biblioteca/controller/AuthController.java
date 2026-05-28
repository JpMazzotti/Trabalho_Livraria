package br.com.escola.biblioteca.controller;

import br.com.escola.biblioteca.dto.AutenticacaoDTO;
import br.com.escola.biblioteca.dto.TokenResponseDTO;
import br.com.escola.biblioteca.entity.Usuario;
import br.com.escola.biblioteca.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity fazerLogin(@RequestBody @Valid AutenticacaoDTO dados) {

        // Empacota
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        
        // Chama o manager
        var authentication = manager.authenticate(authenticationToken);

        // Se a senha bater, gera o JWT
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        // Empacota e devolve o Token num dto
        return ResponseEntity.ok(new TokenResponseDTO(tokenJWT));
    }
    
}







