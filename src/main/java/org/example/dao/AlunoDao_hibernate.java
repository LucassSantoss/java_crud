package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.model.Aluno;

import java.util.List;
import java.util.NoSuchElementException;

public class AlunoDao_hibernate implements AlunoDao {
    private EntityManager em;

    public AlunoDao_hibernate(EntityManager em) {
        this.em = em;
    }

    @Override
    public void cadastrar(Aluno aluno) {
        em.persist(aluno);
    }


    @Override
    public Aluno buscarPorNome(String nome) {
        try {
            String jpql = "select a from Aluno a where a.nome = :nome";
            return em.createQuery(jpql, Aluno.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.err.println("O aluno " + nome + " não existe");
        }
        return null;
    }

    public void remover(String nome) {
        Aluno aluno = buscarPorNome(nome);
        if (aluno == null) return; // já tratado no buscarAluno()

        em.getTransaction().begin();
        em.remove(aluno);
        em.getTransaction().commit();
    }

    @Override
    public void editar(String nomeAntigoAluno, Aluno novoAluno) {
        Aluno antigoAluno = buscarPorNome(nomeAntigoAluno);
        if (nomeAntigoAluno == null) return; // já tratado no buscarAluno()

        em.getTransaction().begin();

        antigoAluno.setNome(novoAluno.getNome());
        antigoAluno.setRa(novoAluno.getRa());
        antigoAluno.setEmail(novoAluno.getEmail());
        antigoAluno.setNota1(novoAluno.getNota1());
        antigoAluno.setNota2(novoAluno.getNota2());
        antigoAluno.setNota3(novoAluno.getNota3());

        em.getTransaction().commit();
    }

    public List<Aluno> buscarTodos() {
        String jpql = "select a from Aluno a";
        return em.createQuery(jpql, Aluno.class)
                .getResultList();
    }

}
