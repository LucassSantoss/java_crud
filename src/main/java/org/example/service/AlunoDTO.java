package org.example.service;

import java.math.BigDecimal;

public record AlunoDTO(
        String nome,
        String ra,
        String email,
        BigDecimal nota1,
        BigDecimal nota2,
        BigDecimal nota3
) { }
