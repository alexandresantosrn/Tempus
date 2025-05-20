package com.alexandre.tempus.config;

import com.alexandre.tempus.model.Usuario;
import com.alexandre.tempus.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner loadData() {
        return args -> {
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario user = Usuario.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("123456"))
                        .nome("Administrador")
                        .ativo(true)
                        .build();

                usuarioRepository.save(user);
            }
        };
    }
}
