package org.example.persistence;

import org.example.model.Aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoDAO {
    public void cadastrar(Aluno aluno);

    public Optional<Aluno> buscarPorNome(String nome);

    public void remover(String nome);

    public void editar(String nomeAntigoAluno, Aluno novoAluno);

    public List<Aluno> buscarTodos();
}
