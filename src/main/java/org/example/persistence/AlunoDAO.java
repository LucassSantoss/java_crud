package org.example.persistence;

import org.example.model.Aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoDAO {
    public boolean cadastrar(Aluno aluno);

    public Optional<Aluno> buscarPorNome(String nome);

    public boolean remover(String nome);

    public boolean editar(Aluno antigoAluno, Aluno novoAluno);

    public List<Aluno> buscarTodos();
}
