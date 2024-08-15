package org.example.service;

import org.example.model.Aluno;

public class AlunoConverterService {
    private AlunoConverterService() {};

    public static Aluno fromDTO(AlunoDTO dto) {
        return new Aluno(
            dto.nome(),
            dto.ra(),
            dto.email(),
            dto.nota1(),
            dto.nota2(),
            dto.nota3()
        );
    }

    public static AlunoDTO toDTO(Aluno aluno) {
        return new AlunoDTO(
            aluno.getNome(),
            aluno.getEmail(),
            aluno.getRa(),
            aluno.getNota1(),
            aluno.getNota2(),
            aluno.getNota3()
        );
    }
}
