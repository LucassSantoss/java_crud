package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.Aluno;

public class AlunoDao_hibernate implements AlunoDao {
    private EntityManager em;

    public AlunoDao_hibernate(EntityManager em) {
        this.em = em;
    }

    @Override
    public void cadastrar(Aluno aluno) {
        em.persist(aluno);
    }

    // outros métodos...

}
