package org.example;

import jakarta.persistence.EntityManager;
import org.example.dao.AlunoDao;
import org.example.dao.AlunoDao_hibernate;
import org.example.model.Aluno;
import org.example.service.JPAUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        //carregarAlunos();

        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao dao = new AlunoDao_hibernate(em);

        //Aluno aluno;
        //aluno = dao.buscarPorNome("Lucas");

        //System.out.println(aluno);

        //dao.remover("JOAO");
        //dao.remover("Tiago");
        //dao.remover("Lucas");

        //Aluno novoAluno = new Aluno("JOAO", "111", "@hotmail", BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3));
        //dao.editar("Lucas", novoAluno);

        List<Aluno> alunos = dao.buscarTodos();
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }

        em.close();
    }

    public static void carregarAlunos(){
        Aluno aluno = new Aluno("Lucas", "213", "@gmail", BigDecimal.valueOf(10), BigDecimal.valueOf(8), BigDecimal.valueOf(9));
        Aluno aluno2 = new Aluno("Tiago", "511", "@gmail", BigDecimal.valueOf(10), BigDecimal.valueOf(8), BigDecimal.valueOf(9));

        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao dao = new AlunoDao_hibernate(em);

        em.getTransaction().begin();

        dao.cadastrar(aluno);
        dao.cadastrar(aluno2);

        em.getTransaction().commit();
    }
}