package com.alexandre.tempus.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroPontoDTO {

    private String horarioFormatado;

    public RegistroPontoDTO(LocalDateTime horario) {
        this.horarioFormatado = horario.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public String getHorarioFormatado() {
        return horarioFormatado;
    }
}
