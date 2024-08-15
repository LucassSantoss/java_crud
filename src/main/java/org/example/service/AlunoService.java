package org.example.service;

import org.example.persistence.AlunoDAO;
import org.example.model.Aluno;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AlunoService {
    private final AlunoDAO dao;

    public AlunoService(AlunoDAO dao) {
        this.dao = dao;
    }

    public String getAlunosComStatus() {
        List<Aluno> alunos = dao.buscarTodos();
        return alunos.stream()
                .map(aluno -> STR.
                        """
                Nome: \{aluno.getNome()}
                Email: \{aluno.getEmail()}
                RA: \{aluno.getRa()}
                Notas: \{STR."\{aluno.getNota1()} - \{aluno.getNota2()} - \{aluno.getNota3()}"}
                Media: \{aluno.calcularMedia()}
                Situação: \{aluno.getSituacao()}
                """
                )
                .collect(Collectors.joining("\n"));
    }
}
