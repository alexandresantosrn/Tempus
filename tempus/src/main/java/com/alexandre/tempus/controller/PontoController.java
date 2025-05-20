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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PontoController {

    private final RegistroPontoRepository registroPontoRepository;
    private final UsuarioRepository usuarioRepository;

    @GetMapping("/ponto")
    public String telaPonto(@AuthenticationPrincipal Usuario usuario, Model model) {
        List<RegistroPonto> registrosDoDia = registroPontoRepository
                .findByUsuarioAndHorarioBetween(
                        usuario,
                        LocalDate.now().atStartOfDay(),
                        LocalDate.now().atTime(23, 59, 59)
                );

        String dataFormatada = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        model.addAttribute("usuario", usuario);
        model.addAttribute("registros", registrosDoDia);
        model.addAttribute("dataHoje", dataFormatada);

        return "ponto";
    }

    @PostMapping("/ponto")
    public String registrarPonto(@AuthenticationPrincipal Usuario usuario) {

        RegistroPonto ponto = RegistroPonto.builder()
                .usuario(usuario)
                .horario(LocalDateTime.now())
                .build();

        registroPontoRepository.save(ponto);
        return "redirect:/ponto";
    }
}
