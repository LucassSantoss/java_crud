package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.Aluno;

import java.util.List;

public interface AlunoDao {
    public void cadastrar(Aluno aluno);

    public Aluno buscarPorNome(String nome);

    public void remover(String nome);

    public void editar(String nomeAntigoAluno, Aluno novoAluno);

    public List<Aluno> buscarTodos();
}
