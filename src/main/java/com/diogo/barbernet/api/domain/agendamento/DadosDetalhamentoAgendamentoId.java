package com.diogo.barbernet.api.domain.agendamento;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public record DadosDetalhamentoAgendamentoId(
        Long id,
        Integer cabeleireiro,
        Integer cliente,
        String nomeCabeleireiro,
        String nomeCliente,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate criacao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime data,
        BigDecimal precoEstimado,
        StatusAgendamento statusAgendamento,
        MetodoPagamento metodoPagamento,
        String observacao
) {

    public DadosDetalhamentoAgendamentoId(Agendamento agendamento){
        this(
                agendamento.getId(),
                Math.toIntExact(agendamento.getCabeleireiro().getId()),
                Math.toIntExact(agendamento.getCliente().getId()),
                agendamento.getCabeleireiro().getNome(),
                agendamento.getCliente().getNome(),
                agendamento.getDataCriacao(),
                agendamento.getDataHora(),
                agendamento.getPrecoEstimado(),
                agendamento.getStatus(),
                agendamento.getMetodoPagamento(),
                agendamento.getObservacao()
        );
    }
}
