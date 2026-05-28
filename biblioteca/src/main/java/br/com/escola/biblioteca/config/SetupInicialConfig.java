package br.com.escola.biblioteca.config;

import br.com.escola.biblioteca.entity.Usuario;
import br.com.escola.biblioteca.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SetupInicialConfig implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public SetupInicialConfig(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String loginAdmin = "admin@biblioteca.com";

        // Verifica se a tabela está vazia. Se estiver, injetamos o utilizador mestre!
        if (usuarioRepository.findByLogin(loginAdmin) == null) {
            Usuario admin = new Usuario();
            admin.setLogin(loginAdmin);
            
            // O próprio Spring gera a criptografia compatível com o seu ambiente
            admin.setSenha(passwordEncoder.encode("123456"));

            usuarioRepository.save(admin);
            System.out.println(">>> USUÁRIO ADMIN CRIADO VIA AUTOMAÇÃO DE INFRAESTRUTURA <<<");
        }
    }
}