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
    public void cadastrar(Aluno aluno) {
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
    }


    @Override
    public Optional<Aluno> buscarPorNome(String nome) {
        try {
            String jpql = "select a from Aluno a where a.nome = :nome";
            return Optional.ofNullable(em.createQuery(jpql, Aluno.class)
                    .setParameter("nome", nome)
                    .getSingleResult());
        } catch (NoResultException e) {
            System.err.println("O aluno " + nome + " não existe");
        }
        return Optional.empty();
    }

    public void remover(String nome) {
        Optional<Aluno> aluno = buscarPorNome(nome);
        if (aluno.isEmpty()) return; // já tratado no buscarAluno()

        em.getTransaction().begin();
        em.remove(aluno.get());
        em.getTransaction().commit();
    }

    @Override
    public void editar(String nomeAntigoAluno, Aluno novoAluno) {
        Optional<Aluno> antigoAlunoOptional = buscarPorNome(nomeAntigoAluno);
        if (antigoAlunoOptional.isEmpty()) return; // já tratado no buscarAluno()
        Aluno antigoAluno = antigoAlunoOptional.get();
        em.getTransaction().begin();
        // Verificar se o nome já está cadastrado ????????????
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
