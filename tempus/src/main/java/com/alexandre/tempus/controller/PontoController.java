package com.alexandre.tempus.controller;

import com.alexandre.tempus.model.RegistroPonto;
import com.alexandre.tempus.model.Usuario;
import com.alexandre.tempus.repository.RegistroPontoRepository;
import com.alexandre.tempus.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PontoController {

    private final RegistroPontoRepository registroPontoRepository;
    private final UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/ponto";
    }

    @GetMapping("/ponto")
    public String listarPontos(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<RegistroPonto> registros = registroPontoRepository.findByUsuario(usuario);

        model.addAttribute("usuario", usuario);
        model.addAttribute("registros", registros);

        return "ponto";
    }

    @PostMapping("/ponto")
    public String registrarPonto(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        RegistroPonto ponto = RegistroPonto.builder()
                .usuario(usuario)
                .horario(LocalDateTime.now())
                .build();

        registroPontoRepository.save(ponto);

        return "redirect:/ponto";
    }
}
