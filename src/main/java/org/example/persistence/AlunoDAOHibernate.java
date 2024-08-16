package org.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.model.Aluno;

import java.util.List;
import java.util.Optional;

public class AlunoDAOHibernate implements AlunoDAO {
    private EntityManager em;

    public AlunoDAOHibernate(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean cadastrar(Aluno aluno) {
        Optional<Aluno> alunoOpcional = buscarPorNome(aluno.getNome());
        if (alunoOpcional.isPresent()) return false;

        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
        return true;
    }


    @Override
    public Optional<Aluno> buscarPorNome(String nome) {
        try {
            String jpql = "select a from Aluno a where a.nome = :nome";
            return Optional.ofNullable(em.createQuery(jpql, Aluno.class)
                    .setParameter("nome", nome)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public boolean remover(String nome) {
        Optional<Aluno> aluno = buscarPorNome(nome);
        if (aluno.isEmpty()) return false;

        em.getTransaction().begin();
        em.remove(aluno.get());
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean editar(Aluno antigoAluno, Aluno novoAluno) {
        Optional<Aluno> novoAlunoOpcional = buscarPorNome(novoAluno.getNome());
        if (novoAlunoOpcional.isPresent()) {
            if (!novoAluno.getNome().equals(antigoAluno.getNome())) return false;
        }

        em.getTransaction().begin();

        antigoAluno.setNome(novoAluno.getNome());
        antigoAluno.setRa(novoAluno.getRa());
        antigoAluno.setEmail(novoAluno.getEmail());
        antigoAluno.setNota1(novoAluno.getNota1());
        antigoAluno.setNota2(novoAluno.getNota2());
        antigoAluno.setNota3(novoAluno.getNota3());

        em.getTransaction().commit();
        return true;
    }

    public List<Aluno> buscarTodos() {
        String jpql = "select a from Aluno a";
        return em.createQuery(jpql, Aluno.class)
                .getResultList();
    }

}
