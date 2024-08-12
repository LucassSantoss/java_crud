package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.Aluno;

public interface AlunoDao {
    public void cadastrar(Aluno aluno);

    // outros métodos...
}
